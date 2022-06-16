/* eslint-disable class-methods-use-this */
const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class NullProgramCounterCommand extends ProgramCounterCommand {
  constructor(programCounterUpdater) {
    super();
    // TODO: Fold this line into the base class
    this.programCounterUpdater = programCounterUpdater;
  }

  execute() {
    this.programCounterUpdater.setProgramCounter(1);
  }
};
