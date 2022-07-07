module.exports = {
  env: {
    node: true,
    es2021: true,
  },
  extends: [
    'airbnb',
    'eslint:recommended',
    'plugin:jest/recommended',
  ],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: [
    'jest',
  ],
  rules: {
    'jest/expect-expect': [
      'error',
      {
        assertFunctionNames: ['expect', 'validate*'],
        additionalTestBlockFunctions: [],
      },
    ],
    'max-len': ['error', { code: 120 }],
    'no-plusplus': ['error', { allowForLoopAfterthoughts: true }],
  },
};
