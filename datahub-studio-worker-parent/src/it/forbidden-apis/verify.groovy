def log = new File(basedir, 'build.log').text
[
    'Forbidden method invocation: java.lang.System#exit(int)',
    'Forbidden method invocation: java.lang.Thread#start()',
    'Forbidden class/interface use: java.util.concurrent.Executors',
    'Forbidden class/interface use: java.util.concurrent.ForkJoinPool',
    'Forbidden class/interface use: java.util.Timer',
    'Forbidden method invocation: java.util.concurrent.CompletableFuture#supplyAsync(java.util.function.Supplier)',
    'Forbidden method invocation: java.util.Collection#parallelStream()',
    'Forbidden method invocation: java.util.stream.BaseStream#parallel()',
    'Forbidden field access: java.lang.System#out',
    'Forbidden method invocation: java.io.File#createTempFile(java.lang.String,java.lang.String)',
    'Forbidden method invocation: java.nio.file.Paths#get(java.lang.String,java.lang.String[])',
    'Forbidden method invocation: java.nio.file.Path#of(java.lang.String,java.lang.String[])',
    'Forbidden method invocation: java.io.File#<init>(java.lang.String)',
    'Forbidden method invocation: java.lang.System#getenv(java.lang.String)',
    'Forbidden method invocation: java.lang.System#getProperty(java.lang.String)',
    'Forbidden class/interface use: org.slf4j.LoggerFactory',
].each { assert log.contains(it) : "missing: $it" }
assert !log.contains('java.lang.Thread#sleep')
