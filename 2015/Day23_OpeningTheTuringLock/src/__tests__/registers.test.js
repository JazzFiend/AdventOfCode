const Registers = require('../registers');

test('An invalid register should throw an error', () => {
  const reg = new Registers();
  expect(() => reg.getRegister('f')).toThrow('Incorrect register given');
});
