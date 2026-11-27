def log = new File(basedir, 'build.log').text
assert log.contains('it must not ship Spring, Logback or an AWS SDK')
assert log.contains('ch.qos.logback:logback-classic')
