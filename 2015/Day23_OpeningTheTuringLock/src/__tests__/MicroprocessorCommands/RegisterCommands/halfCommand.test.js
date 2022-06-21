const HalfCommand = require('../../../MicroprocessorCommands/RegisterCommands/halfCommand');
const Registers = require('../../../registers');

test('Half command should divide a register in half', () => {
  const registers = new Registers();
  registers.setRegister('a', 15);
  const inc = new HalfCommand('a', registers);
  inc.execute();
  expect(registers.getRegister('a')).toEqual(7);
});
