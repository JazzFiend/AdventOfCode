const HalfCommand = require('./MicroprocessorCommands/RegisterCommands/halfCommand');
const IncrementCommand = require('./MicroprocessorCommands/RegisterCommands/incrementCommand');
const JumpOffsetCommand = require('./MicroprocessorCommands/ProgramCounterCommands/jumpOffsetCommand');
const NullProgramCounterCommand = require('./MicroprocessorCommands/ProgramCounterCommands/nullProgramCounterCommand');
const NullRegisterCommand = require('./MicroprocessorCommands/RegisterCommands/nullRegisterCommand');
const TripleCommand = require('./MicroprocessorCommands/RegisterCommands/tripleCommand');

module.exports = class InstructionDecoder {
  static decodeRegisterInstruction(opcode, registerName, registers) {
    if (!InstructionDecoder.isValidInstruction(opcode)) { throw new Error('Unknown opcode seen'); }

    if (InstructionDecoder.isValidRegisterArgument(registerName, registers)
    && InstructionDecoder.isValidRegisterOpcode(opcode)) {
      if (opcode === 'inc') { return new IncrementCommand(registerName, registers); }
      if (opcode === 'tpl') { return new TripleCommand(registerName, registers); }
      return new HalfCommand(registerName, registers); // (opcode === 'hlf')
    }
    return new NullRegisterCommand();
  }

  static decodeProgramCounterInstruction(opcode, offset) {
    if (!this.isValidInstruction(opcode)) { throw new Error('Unknown opcode seen'); }

    if (Number.isFinite(Number.parseInt(offset, 10)) && InstructionDecoder.isValidProgramCounterOpcode(opcode)) {
      return new JumpOffsetCommand(offset); // (opcode === 'jmp')
    }
    return new NullProgramCounterCommand(offset);
  }

  static isValidInstruction(opcode) {
    return ['inc', 'tpl', 'hlf', 'jmp'].includes(opcode);
  }

  static isValidRegisterOpcode(opcode) {
    return ['inc', 'tpl', 'hlf'].includes(opcode);
  }

  static isValidProgramCounterOpcode(opcode) {
    return ['jmp'].includes(opcode);
  }

  // TODO: Does this belong in a seperate class?
  static isValidRegisterArgument(registerName, registers) {
    try {
      registers.getRegister(registerName);
    } catch {
      return false;
    }
    return true;
  }
};
