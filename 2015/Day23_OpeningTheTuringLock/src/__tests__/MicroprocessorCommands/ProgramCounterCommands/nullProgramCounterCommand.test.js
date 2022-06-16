const Microprocessor = require('../../../microprocessor');
const NullProgramCounterCommand = require(
  '../../../MicroprocessorCommands/ProgramCounterCommands/nullProgramCounterCommand',
);

test('Null PC command always outputs 1', () => {
  const microprocessor = new Microprocessor();
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const nul = new NullProgramCounterCommand(microprocessor);
  nul.execute();
  expect(spy).toHaveBeenCalledWith(1);
});
