const RegisterCommand = require('./registerCommand');

module.exports = class TripleCommand extends RegisterCommand {
  execute() {
    const value = this.registers.getRegister(this.register);
    this.registers.setRegister(this.register, value * 3);
  }
};
