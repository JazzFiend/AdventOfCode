/* eslint-disable class-methods-use-this */
const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class NullProgramCounterCommand extends ProgramCounterCommand {
  execute() {
    this.programCounterUpdater.setProgramCounter(1);
  }
};
