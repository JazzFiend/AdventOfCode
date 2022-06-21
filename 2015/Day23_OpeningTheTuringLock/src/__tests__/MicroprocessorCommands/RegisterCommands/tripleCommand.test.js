const TripleCommand = require('../../../MicroprocessorCommands/RegisterCommands/tripleCommand');
const Registers = require('../../../registers');

test('Triple command should multiply by three', () => {
  const registers = new Registers();
  registers.setRegister('a', 5);
  const inc = new TripleCommand('a', registers);
  inc.execute();
  expect(registers.getRegister('a')).toEqual(15);
});
