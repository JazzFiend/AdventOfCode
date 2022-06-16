const RegisterCommand = require('./registerCommand');

module.exports = class HalfCommand extends RegisterCommand {
  constructor(register, registers) {
    super();
    this.register = register;
    this.registers = registers;
  }

  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, Math.floor(value / 2));
  }
};
