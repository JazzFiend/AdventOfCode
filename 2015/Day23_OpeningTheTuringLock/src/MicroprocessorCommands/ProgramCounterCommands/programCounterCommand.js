module.exports = class ProgramCounterCommand {
  constructor(programCounterUpdater) {
    if (this.constructor === ProgramCounterCommand) {
      throw new Error('Cannot create abstract class');
    }
    this.programCounterUpdater = programCounterUpdater;
  }
};
