basePath = '../';

files = [
  
  'test/lib/*.js',
  'app/lib/**/*.js',
  ANGULAR_SCENARIO,
  ANGULAR_SCENARIO_ADAPTER,
  'app/js/*.js',
  'test/e2e/**/*.js'
];

autoWatch = false;

browsers = ['Chrome'];

singleRun = false;

proxies = {
  '/': 'http://localhost:8182/'
};

junitReporter = {
  outputFile: 'test_out/e2e.xml',
  suite: 'e2e'
};
