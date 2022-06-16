const RegisterCommand = require('./registerCommand');

module.exports = class IncrementCommand extends RegisterCommand {
  // TODO: Could probably fold this into base class.
  constructor(register, registers) {
    super();
    this.register = register;
    this.registers = registers;
  }

  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, value + 1);
  }
};
