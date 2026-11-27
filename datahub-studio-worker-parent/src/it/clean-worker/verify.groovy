def log = new File(basedir, 'build.log').text
assert log.contains('Scanned 1 class file(s) for forbidden API invocations')
assert !log.contains('Forbidden ')
