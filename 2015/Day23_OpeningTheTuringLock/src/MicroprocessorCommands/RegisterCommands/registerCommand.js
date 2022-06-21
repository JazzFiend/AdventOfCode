module.exports = class RegisterCommand {
  constructor(register, registers) {
    if (this.constructor === RegisterCommand) {
      throw new Error('Cannot create abstract class');
    }

    this.register = register;
    this.registers = registers;
  }
};
