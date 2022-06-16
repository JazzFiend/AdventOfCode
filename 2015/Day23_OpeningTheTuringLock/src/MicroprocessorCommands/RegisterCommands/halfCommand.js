const RegisterCommand = require('./registerCommand');

module.exports = class HalfCommand extends RegisterCommand {
  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, Math.floor(value / 2));
  }
};
