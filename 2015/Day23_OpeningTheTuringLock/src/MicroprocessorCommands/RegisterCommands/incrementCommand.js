const RegisterCommand = require('./registerCommand');

module.exports = class IncrementCommand extends RegisterCommand {
  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, value + 1);
  }
};
