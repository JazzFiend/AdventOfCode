const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpIfEvenCommand extends ProgramCounterCommand {
  constructor(registerName, offset, programCounterUpdater, registers) {
    super(programCounterUpdater);
    this.offset = offset;
    this.register = registerName;
    this.registers = registers;
  }

  execute() {
    if (this.registers.getRegister(this.register) % 2 === 0) {
      this.programCounterUpdater.setProgramCounter(this.offset);
    } else {
      this.programCounterUpdater.setProgramCounter(1);
    }
  }
};
