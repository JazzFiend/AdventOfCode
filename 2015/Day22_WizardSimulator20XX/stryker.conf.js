/**
 * @type {import('@stryker-mutator/api/core').StrykerOptions}
 */
module.exports = {
  mutator: 'javascript',
  packageManager: 'yarn',
  reporters: ['html', 'clear-text', 'progress'],
  testRunner: 'jest',
  transpilers: [],
  coverageAnalysis: 'off',
  mutate: [
    'src/**/*.js?(x)',
    '!src/**/*.test.js?(x)',
  ],
  maxConcurrentTestRunners: 1,
};
