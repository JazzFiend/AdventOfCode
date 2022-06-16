const NullProgramCounterCommand = require(
  '../../../MicroprocessorCommands/ProgramCounterCommands/nullProgramCounterCommand',
);

test('Null PC command always outputs 1', () => {
  const inc = new NullProgramCounterCommand(43);
  expect(inc.execute()).toEqual(1);
});
