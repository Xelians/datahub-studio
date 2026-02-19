# Xelians DataHub Studio

[![license](https://img.shields.io/badge/license-Apache--2.0-green)](http://www.apache.org/licenses/LICENSE-2.0)  
[![maven](https://img.shields.io/badge/maven--central-v4.2.0-blue)](https://mvnrepository.com/artifact/fr.xelians/datahub-studio/4.2.0)

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

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>fr.xelians</groupId>
    <artifactId>datahub-studio</artifactId>
    <version>4.2.0</version>
</dependency>
```

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

Workers must be thread-safe if reused.

---

## Temporary Working Directory

Workers must use:

```java
TempWorkDir.dir();
```
Location:

```java
${java.io.tmpdir}/xdh
```

Used for:

- Archive extraction

- Intermediate file generation

- Temporary processing

### Important

- Workers MUST delete created files and directories.

- This directory is periodically purged by the application.

- It must NOT be used for persistent state.

---

## Collector

### Interface

```java
List<String> collect(
    Path targetDirectory,
    String webhook,
    XDHProcessLogger logger,
    ChannelStore channelStore,
    ChannelMetricsRepository metricsRepository
) throws Exception;
```

### Responsibilities

- Retrieve 1..n files

- Write files to targetDirectory

- Return filenames only (no absolute paths)

- Generate unique filenames

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
TransformResult transform(
        List<String> fileNames,
        Path sourceDirectory,
        Path targetDirectory,
        XDHProcessLogger logger,
        ChannelStore channelStore,
        ChannelMetricsRepository metricsRepository
) throws Exception;
```

### Responsibilities

- Transform n input files into 1 output file

- Write output to targetDirectory

- Generate unique filenames

- Remain stateless

### Functional Error

```java
throw new TransformException("Invalid format", "FORMAT_001");
```

---

## Sender

### Interface

```java
SenderResult send(
        String fileName,
        Path sourceDirectory,
        Path resultDirectory,
        XDHProcessLogger logger,
        ChannelStore channelStore,
        ChannelMetricsRepository metricsRepository
) throws Exception;
```

### Responsibilities

- Send the file to target system

- Optionally write acknowledgment files to resultDirectory

- Return SenderResult

- Be idempotent when possible

### Functional Error

```java
throw new SendException("Authentication failed", "AUTH_001");
```

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

The logger is provided as a parameter to all worker methods:

```java
logger.info("File processing started: {}", fileName);
logger.error("Failed to process file: {}", fileName, exception);
```

### Log Levels

- `logger.debug()` — detailed diagnostic information

- `logger.info()` — general informational messages

- `logger.warn()` — warning conditions

- `logger.error()` — error events

### Important Notes

- **Do NOT use standard logging frameworks** (e.g., SLF4J, Log4j) directly in workers

- Logs written with `XDHProcessLogger` are displayed in the supervision UI

- They provide essential traceability for production troubleshooting

---

## Worker Configuration

Each worker must define a configuration class:

```java
Class<? extends Worker> getWorkerClass();
Label.Translation getName();
Label.Translation description();
String id();
String version();
WorkerForm.Form getForm();
```

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

- Clean temporary files

- Use ChannelStore for state persistence

- Publish meaningful metrics

- Generate unique filenames

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