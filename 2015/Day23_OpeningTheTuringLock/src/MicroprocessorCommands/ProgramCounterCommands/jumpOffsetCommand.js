const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpOffsetCommand extends ProgramCounterCommand {
  constructor(offset, programCounterUpdater) {
    super(programCounterUpdater);
    this.offset = offset;
  }

  execute() {
    this.programCounterUpdater.setProgramCounter(this.offset);
  }
};
