const IncrementCommand = require('../../../MicroprocessorCommands/RegisterCommands/incrementCommand');
const Registers = require('../../../registers');

test('Increment command should increment by one', () => {
  const registers = new Registers();
  registers.setRegister('a', 7);
  const inc = new IncrementCommand('a', registers);
  inc.execute();
  expect(registers.getRegister('a')).toEqual(8);
});
