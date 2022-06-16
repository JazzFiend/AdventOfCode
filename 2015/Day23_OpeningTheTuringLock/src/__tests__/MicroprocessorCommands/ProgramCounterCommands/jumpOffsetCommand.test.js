const JumpOffsetCommand = require('../../../MicroprocessorCommands/ProgramCounterCommands/jumpOffsetCommand');

test('JumpOffset command should just output the input', () => {
  const inc = new JumpOffsetCommand(99);
  expect(inc.execute()).toEqual(99);
});
