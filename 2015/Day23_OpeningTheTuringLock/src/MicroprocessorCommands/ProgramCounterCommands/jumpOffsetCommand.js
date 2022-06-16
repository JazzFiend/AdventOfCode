const ProgramCounterCommand = require('./programCounterCommand');

module.exports = class JumpOffsetCommand extends ProgramCounterCommand {
  constructor(arg) {
    super();
    // TODO: Shouldn't have to parseInt here...
    this.arg = parseInt(arg, 10);
  }

  // TODO: Since this command always jumps, this is just a passthrough. Should we be modifying the PC here?
  execute() {
    return this.arg;
  }
};
