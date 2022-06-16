module.exports = class ProgramCounterCommand {
  constructor() {
    if (this.constructor === ProgramCounterCommand) {
      throw new Error('Cannot create abstract class');
    }
  }
};
