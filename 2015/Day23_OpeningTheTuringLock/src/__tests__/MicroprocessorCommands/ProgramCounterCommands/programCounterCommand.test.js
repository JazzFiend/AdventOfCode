const ProgramCounterCommand = require('../../../MicroprocessorCommands/ProgramCounterCommands/programCounterCommand');

test('ProgramCounterCommand should be abstract', () => {
  expect(() => new ProgramCounterCommand()).toThrow('Cannot create abstract class');
});
