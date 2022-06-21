const Microprocessor = require('../../../microprocessor');
const JumpOffsetCommand = require('../../../MicroprocessorCommands/ProgramCounterCommands/jumpOffsetCommand');

test('JumpOffset command should just output the input', () => {
  const microprocessor = new Microprocessor();
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const jmp = new JumpOffsetCommand(99, microprocessor);
  jmp.execute();
  expect(spy).toHaveBeenCalledWith(99);
});
