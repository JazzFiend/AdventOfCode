const Microprocessor = require('../../../microprocessor');
const JumpIfOneCommand = require('../../../MicroprocessorCommands/ProgramCounterCommands/jumpIfOneCommand');
const Registers = require('../../../registers');

test('JumpIfEven command should pass input through if the register equal to one', () => {
  const microprocessor = new Microprocessor();
  const registers = new Registers();
  registers.setRegister('b', 1);
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const jmp = new JumpIfOneCommand('b', 22, microprocessor, registers);
  jmp.execute();
  expect(spy).toHaveBeenCalledWith(22);
});

test('JumpIfEven command should use 1 if the register does not equal one', () => {
  const microprocessor = new Microprocessor();
  const registers = new Registers();
  registers.setRegister('b', 0);
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const jmp = new JumpIfOneCommand('a', 22, microprocessor, registers);
  jmp.execute();
  expect(spy).toHaveBeenCalledWith(1);
});
