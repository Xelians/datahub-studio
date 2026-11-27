def log = new File(basedir, 'build.log').text
assert log.contains('The SDK is provided by the platform (scope provided)')
assert log.contains('fr.xelians:datahub-studio')
