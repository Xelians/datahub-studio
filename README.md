# Xelians DataHub Studio

[![license](https://img.shields.io/badge/license-Apache--2.0-green)](http://www.apache.org/licenses/LICENSE-2.0)  
[![maven](https://img.shields.io/badge/maven--central-v4.3.0-blue)](https://mvnrepository.com/artifact/fr.xelians/datahub-studio/4.3.0)

---

## Overview

**Xelians DataHub Studio** is the official extension framework for building external integration workers for the Xelians DataHub platform.

It allows developers to implement custom:

- **Collectors** — retrieve data from external systems  
- **Transformers** — process and consolidate data  
- **Senders** — deliver processed data to target systems  

The framework provides:

- Controlled execution lifecycle
- Shared and scalable thread pools
- Retry management  
- Persistent channel state  
- Metrics publication  
- Structured configuration model  

---

## Installation

### Requirements

- **Java Version:** 17 (minimum) to 25 (maximum)

### Maven

Inherit the worker parent and declare the SDK, without version nor scope (the parent provides
both: the platform ships the SDK, so it is `provided`):

```xml
<parent>
    <groupId>fr.xelians</groupId>
    <artifactId>datahub-studio-worker-parent</artifactId>
    <version>4.3.0</version>
    <relativePath/>
</parent>

<dependencies>
    <dependency>
        <groupId>fr.xelians</groupId>
        <artifactId>datahub-studio</artifactId>
    </dependency>
</dependencies>
```

With the parent, `mvn verify` audits the worker (see [Auditing a worker](#auditing-a-worker)).
A worker that cannot inherit it may still declare the SDK alone, in `provided` scope, but it is
not audited.

### Auditing a worker

`datahub-studio-worker-parent` checks, on every build, the rules a worker follows inside the
platform:

| Check | Phase | Fails when |
| --- | --- | --- |
| Maven Enforcer | `validate` | the SDK is not `provided`, or older than 4.3.0; a dependency ships Spring, Logback or an AWS SDK |
| forbiddenapis | `process-classes` | the worker calls an API it must not use (below) |

The forbidden APIs are listed in the SDK jar
(`fr/xelians/xdh/studio/audit/worker-forbidden-apis.txt`), each with the reason given in the
build log:

- stopping the JVM (`System.exit`, `Runtime.halt`);
- creating threads or pools (`Thread#start`, `Executors`, `ForkJoinPool`, `Timer`, asynchronous
  `CompletableFuture`, parallel streams): a worker runs in the thread the platform gives it, see
  [Execution Model](#execution-model). `Thread.sleep` stays allowed;
- temporary files outside `WorkerContext.tempDir()` (`File.createTempFile` without a directory);
- logging outside `XDHProcessLogger` (`System.out`, SLF4J, Logback, `java.util.logging`);
- building a path from a string or a URI (`Paths.get`, `Path.of`, `new File(String)`…);
- reading machine settings (`System.getenv`, `System.getProperty`).

These rules see the code of the worker only. What a library does at run time (a thread it starts,
a file it writes elsewhere) is the job of the conformance kit, to come in
`datahub-studio-worker-test`.

`-Dxdh.audit.skip=true` runs a build without the audit, for local trials only.

---

## Architecture

### Channel Model

A **Channel** represents a complete data transfer pipeline.

Each channel contains:

- 1 Collector

- 1 Transformer

- 1 Sender

Workers are instantiated at channel startup using parameters configured through the DataHub UI.

---

### Atomicity Principle

Every worker performs **exactly one atomic operation**. This is a hard design rule, not a guideline:

- A **Collector** only collects.
- A **Transformer** only transforms.
- A **Sender** only transfers.

**Do NOT** build a worker that combines several steps. In particular:

- Do not build a Collector that also transforms or sends.
- Do not build a Sender that transfers to several destinations in a single execution.
- Do not embed extra processing inside a worker "to save a step".

When you need more than one operation, **keep each worker atomic and compose them through
channel chaining** (see below). Never fatten a worker.

---

### Channel Chaining

To perform additional operations after a channel completes, **chain channels** instead of
adding responsibilities to a worker.

**Mechanism:** a channel's **Sender** publishes its result files where the next channel's
**Chained Collector** picks them up. Chaining channels this way forms a pipeline:

```
collect → transform → send → collect → transform → send → …
```

**Chaining several transformations:** combine the **No-Op Sender** (a pass-through sender that
writes its input through unchanged, with no real destination) with the **Chained Collector**.
This lets a channel collect + transform and leave its output for a downstream channel to apply
the next transformation. A **No-Op Transformer** (pass-through transformer) exists for the
symmetric case.

**Platform-provided workers** (available in the DataHub UI):

- **Chained collector** (*Connecteur de collecte chaîné*) — collects the transfer result of
  another channel.
- **No-Op Sender** — pass-through sender enabling collect + transform without a real target.
- **No-Op Transformer** — pass-through transformer.

> These chaining workers are built into the DataHub platform. They are not classes provided by
> the SDK jar — you select them in the UI when configuring channels, you do not implement them.

---

## Execution Model

Scheduler -> Collector Pool -> Transformer Pool -> Sender Pool

### Thread Pools

Channels are assigned to a set of shared thread pools:

- A Collect Pool

- A Transform Pool

- A Send Pool

Multiple channels may share the same pool instances.

### Guarantees

- Single collect execution per channel at a time

- Transform and Send tasks are queued

- Execution model: at-least-once

- Senders may be retried

Workers must be thread-safe: the same instance may be called concurrently for different executions.
Keep the state of an execution in local variables, never in instance or static fields.

Workers must not start threads nor pools of their own: the platform sizes the pools, applies the
task timeout to the thread it gives, and attaches the logs and `tempDir()` of the execution to it.
The worker parent rejects such code at build time.

---

## Files and Storage

Since version 4.3, workers no longer receive directories. They read their input from an
`InputBin` and publish what they produce to an `OutputBin`. The platform decides where the files
are stored (local disk or object storage); a worker written against the bins runs unchanged on
both.

### InputBin

The files to process in one execution, read-only.

```java
List<StoredFile> files();
StoredFile get(String name);
StoredFile single();      // the only file of the bin
boolean isEmpty();
```

A `StoredFile` exposes `name()`, `size()`, `lastModified()` and `open()`. For libraries that
require a `Path`, `materialize()` returns the file as a read-only local path.

### OutputBin

Where a worker publishes its files.

```java
OutputStream create(String name);        // published when the stream is closed
void put(String name, Path localFile);   // publishes a file produced locally
void put(StoredFile file);               // publishes an input file unchanged
String uniqueName(String desired);
List<String> published();
```

Publication is atomic: a file becomes visible to the platform and to the next step once it is
complete, never before. If the worker fails, what was not published is discarded.

### WorkerContext

The services of one execution, passed to every worker method.

```java
XDHProcessLogger logger();
ChannelStore store();
ChannelMetricsRepository metrics();
Path tempDir();
String channelId();
String collectId();   // null during a collect
String webhook();     // body of the webhook that triggered the collect, or null
```

### Temporary Directory

`context.tempDir()` is the only place where a worker may write on the local disk: archive
extraction, intermediate files, files to publish with `OutputBin.put(name, path)`.

- It is specific to the execution: two executions never share it.
- The platform creates it before the call and deletes it afterwards, on success and on failure.
- It must not be used for persistent state; use `ChannelStore` instead.

---

## Collector

### Interface

```java
StepResult collect(OutputBin collected, WorkerContext context) throws Exception;
```

### Example

```java
@Override
public StepResult collect(OutputBin collected, WorkerContext context) throws Exception {
    for (RemoteFile remote : client.listNewFiles()) {
        try (InputStream in = client.download(remote);
             OutputStream out = collected.create(collected.uniqueName(remote.name()))) {
            in.transferTo(out);
        }
    }
    return new StepResult(collected.published().size() + " file(s) collected");
}
```

### Responsibilities

- Retrieve 1..n files and publish them to `collected`

- Publish nothing when there is nothing to collect: no collect is started

- Keep execution bounded

- Avoid long blocking operations

### Functional Error

```java
throw new CollectException("Authentication failed", "AUTH_001");
```

---

## Transformer

### Interface

```java
StepResult transform(InputBin collected, OutputBin transformed, WorkerContext context) throws Exception;
```

### Example

```java
@Override
public StepResult transform(InputBin collected, OutputBin transformed, WorkerContext context) throws Exception {
    Path archive = context.tempDir().resolve("archive.zip");
    archiveBuilder.build(collected.files(), archive);
    transformed.put(transformed.uniqueName("archive.zip"), archive);
    return new StepResult("Archive built");
}
```

### Responsibilities

- Transform the files of `collected` and publish the result to `transformed`

- Each published file is sent separately; publishing nothing ends the collect without any send

- Remain stateless

### Functional Error

```java
throw new TransformException("Invalid format", "FORMAT_001");
```

---

## Sender

### Interface

```java
SenderResult send(InputBin toSend, OutputBin results, WorkerContext context) throws Exception;
```

### Example

```java
@Override
public SenderResult send(InputBin toSend, OutputBin results, WorkerContext context) throws Exception {
    StoredFile file = toSend.single();
    try (InputStream in = file.open();
         OutputStream receipt = results.create("receipt.xml")) {
        client.upload(file.name(), in, receipt);
    }
    return new SenderResult("File delivered");
}
```

### Responsibilities

- Send the single file of `toSend` to the target system

- Optionally publish result files (acknowledgments, responses) to `results`; a chained
  channel can collect them

- Return SenderResult

- Be idempotent when possible

### Functional Error

```java
throw new SendException("Authentication failed", "AUTH_001");
```

---

## Migrating from 4.2

Version 4.3 is binary compatible with 4.2: a worker built against 4.2 runs without being
recompiled. The directory-based methods are deprecated and will be removed in version 5.0.

| 4.2 | 4.3 |
| --- | --- |
| `collect(Path targetDirectory, String webhook, XDHProcessLogger logger, ...)` | `collect(OutputBin collected, WorkerContext context)` |
| `transform(List<String> fileNames, Path sourceDirectory, Path targetDirectory, ...)` | `transform(InputBin collected, OutputBin transformed, WorkerContext context)` |
| `send(String fileName, Path sourceDirectory, Path resultDirectory, ...)` | `send(InputBin toSend, OutputBin results, WorkerContext context)` |
| returned file names, `TransformResult.getFileName()` | files published to the `OutputBin` |
| `logger`, `channelStore`, `metricsRepository` parameters | `context.logger()`, `context.store()`, `context.metrics()` |
| `TempWorkDir.dir()` | `context.tempDir()` |

Until 5.0, the directory-based method with the shortest signature remains abstract. A worker
migrated to the bins still has to declare it:

```java
@Override
public List<String> collect(Path targetDirectory, String webhook, XDHProcessLogger logger) {
    throw new UnsupportedOperationException();
}
```

A worker that is not migrated keeps working: the platform exposes its bins as directories.
On object storage this implies local copies, which a migrated worker avoids.

---

## Retry & Delay Mechanism

To retry a send operation, throw:
```java
throw new DelayExecutionException(60);
```

### Behavior

- The send task will be retried after delay seconds

- Can be combined with locking mechanisms to serialize transfers

- Useful for:

  - Rate limiting

  - Remote throttling

  - Temporary external failures

---

## ChannelStore

Persistent key-value storage scoped to the channel.

Use cases:

- Pagination cursors

- Incremental synchronization

- Execution flags

- Retry state

---

## ChannelMetricsRepository

Provides atomic metric increments.

Example:

```java
metricsRepository.increment("files.sent", 1);
```

Metrics are:

- Atomic

- Persisted

- Exposed via supervision APIs

---

## XDHProcessLogger

**IMPORTANT:** The `XDHProcessLogger` must be used imperatively for all logging operations within workers.

This logger allows to record processing information and events that are:

- **Structured** — associated with the channel execution context

- **Persisted** — stored in the DataHub workspace directory

- **Accessible** — viewable through the supervision interface

### Usage

The logger is provided by the worker context:

```java
XDHProcessLogger logger = context.logger();
logger.info("File processing started: {}", fileName);
logger.warn("File skipped: {}", fileName);
```

### Log Levels

- `logger.trace()` — fine-grained diagnostic information

- `logger.debug()` — detailed diagnostic information

- `logger.info()` — general informational messages

- `logger.warn()` — warning conditions

Errors are not logged by the worker: throw `CollectException`, `TransformException`,
`SendException` or any other exception, and the platform records the failure.

### Important Notes

- **Do NOT use standard logging frameworks** (e.g., SLF4J, Log4j) or `System.out` directly in workers

- Logs written with `XDHProcessLogger` are displayed in the supervision UI

- They provide essential traceability for production troubleshooting

---

## Worker Configuration

Each worker must define a configuration class:

```java
Label.Translation name();
Label.Translation description();
String id();
String version();
WorkerForm.Form form();
```

The configuration implements `CollectorConfiguration`, `TransformerConfiguration` or
`SenderConfiguration`, which adds the worker class: `getCollectorClass()`,
`getTransformerClass()` or `getSenderClass()`.

Requirements:

- Unique ID

- Versioning recommended

- Constructor aligned with form definition

---

## Worker Parameters

Parameters are injected via constructor.

Supported types:

- Integer / int

- Boolean / boolean

- Double / double

- Float / float

- Long / long

- String

- Path

- List < Map < String, Object > > 

- List < Integer | Boolean | Double | Float | Long | String >

Parameter names must strictly match the form.

---

## Form Definition

Use:

```java
WorkerForm.builder()
```

Constraints:

- Parameter name consistency

- Type consistency

- Constructor signature alignment

Misconfiguration prevents worker loading.

---

## Concurrency & Guarantees

- Channels may share thread pools.

- Execution model is at-least-once.

- Sender may be retried.

- Workers should be idempotent.

- Avoid static mutable state.

---

## Best Practices

- Keep collectors bounded and fast

- Avoid long blocking operations

- Write local files only in the temporary directory of the execution

- Use ChannelStore for state persistence

- Publish meaningful metrics

- Generate unique filenames with `OutputBin.uniqueName`

- Make senders retry-safe

- Implement idempotency for external calls

---

## Deployment

1. Package your project as a JAR

2. Place it in the /lib directory at application root

3. Restart DataHub

Workers are automatically discovered and loaded at startup.

---

## Conclusion

Xelians DataHub Studio provides a robust and scalable framework for building production-grade integration workflows that are:

- Observable

- Retry-safe

- Thread-safe

- Scalable

- Maintainable

By respecting the contracts and execution model described in this document, workers integrate seamlessly into the DataHub platform.