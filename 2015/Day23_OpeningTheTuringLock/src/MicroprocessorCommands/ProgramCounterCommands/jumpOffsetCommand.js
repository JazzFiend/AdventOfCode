const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpOffsetCommand extends ProgramCounterCommand {
  constructor(offset, programCounterUpdater) {
    super(programCounterUpdater);
    // TODO: Shouldn't have to parseInt here...
    this.offset = parseInt(offset, 10);
  }

  execute() {
    this.programCounterUpdater.setProgramCounter(this.offset);
  }
};
