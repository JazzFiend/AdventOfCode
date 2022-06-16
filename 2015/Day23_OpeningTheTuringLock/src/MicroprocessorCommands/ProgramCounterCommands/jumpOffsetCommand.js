const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpOffsetCommand extends ProgramCounterCommand {
  constructor(offset, programCounterUpdater) {
    super();
    // TODO: Shouldn't have to parseInt here...
    this.offset = parseInt(offset, 10);
    this.programCounterUpdater = programCounterUpdater;
  }

  // TODO: Since this command always jumps, this is just a passthrough. Should we be modifying the PC here?
  execute() {
    this.programCounterUpdater.setProgramCounter(this.offset);
  }
};
