const RegisterCommand = require('./registerCommand');

module.exports = class TripleCommand extends RegisterCommand {
  constructor(register, registers) {
    super();
    this.register = register;
    this.registers = registers;
  }

  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, value * 3);
  }
};
