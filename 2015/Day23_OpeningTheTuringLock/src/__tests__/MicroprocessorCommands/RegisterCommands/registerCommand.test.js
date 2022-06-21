const RegisterCommand = require('../../../MicroprocessorCommands/RegisterCommands/registerCommand');

test('RegisterCommand should be abstract', () => {
  expect(() => new RegisterCommand()).toThrow('Cannot create abstract class');
});
