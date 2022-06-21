const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpIfOneCommand extends ProgramCounterCommand {
  constructor(registerName, offset, programCounterUpdater, registers) {
    super(programCounterUpdater);
    this.offset = offset;
    this.register = registerName;
    this.registers = registers;
  }

  execute() {
    if (this.registers.getRegister(this.register) === 1) {
      this.programCounterUpdater.setProgramCounter(this.offset);
    } else {
      this.programCounterUpdater.setProgramCounter(1);
    }
  }
};
