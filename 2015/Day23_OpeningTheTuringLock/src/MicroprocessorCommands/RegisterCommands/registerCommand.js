module.exports = class RegisterCommand {
  constructor() {
    if (this.constructor === RegisterCommand) {
      throw new Error('Cannot create abstract class');
    }
  }
};
