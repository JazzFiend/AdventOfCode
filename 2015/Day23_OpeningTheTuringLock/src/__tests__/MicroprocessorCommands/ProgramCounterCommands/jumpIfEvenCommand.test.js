const Microprocessor = require('../../../microprocessor');
const JumpIfEvenCommand = require('../../../MicroprocessorCommands/ProgramCounterCommands/jumpIfEvenCommand');
const Registers = require('../../../registers');

test('JumpIfEven command should pass input through if the register is even', () => {
  const microprocessor = new Microprocessor();
  const registers = new Registers();
  registers.setRegister('a', 8);
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const jmp = new JumpIfEvenCommand('a', -3, microprocessor, registers);
  jmp.execute();
  expect(spy).toHaveBeenCalledWith(-3);
});

test('JumpIfEven command should use 1 if the register is odd', () => {
  const microprocessor = new Microprocessor();
  const registers = new Registers();
  registers.setRegister('a', 3);
  const spy = jest.spyOn(microprocessor, 'setProgramCounter');
  const jmp = new JumpIfEvenCommand('a', -3, microprocessor, registers);
  jmp.execute();
  expect(spy).toHaveBeenCalledWith(1);
});
