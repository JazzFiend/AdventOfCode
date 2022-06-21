const HalfCommand = require('./MicroprocessorCommands/RegisterCommands/halfCommand');
const IncrementCommand = require('./MicroprocessorCommands/RegisterCommands/incrementCommand');
const JumpOffsetCommand = require('./MicroprocessorCommands/ProgramCounterCommands/jumpOffsetCommand');
const NullProgramCounterCommand = require('./MicroprocessorCommands/ProgramCounterCommands/nullProgramCounterCommand');
const NullRegisterCommand = require('./MicroprocessorCommands/RegisterCommands/nullRegisterCommand');
const TripleCommand = require('./MicroprocessorCommands/RegisterCommands/tripleCommand');
const JumpIfEvenCommand = require('./MicroprocessorCommands/ProgramCounterCommands/jumpIfEvenCommand');
const ArgumentParser = require('./argumentParser');

module.exports = class InstructionDecoder {
  static decodeRegisterInstruction(opcode, argumentList, registers) {
    if (!InstructionDecoder.isValidInstruction(opcode)) { throw new Error('Unknown opcode seen'); }

    const parsedArgs = ArgumentParser.parse(argumentList);
    if (registers.isValidRegister(parsedArgs.registerName) && InstructionDecoder.isValidRegisterOpcode(opcode)) {
      if (opcode === 'inc') { return new IncrementCommand(parsedArgs.registerName, registers); }
      if (opcode === 'tpl') { return new TripleCommand(parsedArgs.registerName, registers); }
      return new HalfCommand(parsedArgs.registerName, registers); // (opcode === 'hlf')
    }
    return new NullRegisterCommand(parsedArgs.registerName, registers);
  }

  static decodeProgramCounterInstruction(opcode, argumentList, registers, programCounterUpdater) {
    if (!this.isValidInstruction(opcode)) { throw new Error('Unknown opcode seen'); }

    const parsedArgs = ArgumentParser.parse(argumentList);
    if (InstructionDecoder.isValidProgramCounterOpcode(opcode)) {
      if (opcode === 'jie') {
        return new JumpIfEvenCommand(parsedArgs.registerName, parsedArgs.jumpOffset, programCounterUpdater, registers);
      }
      return new JumpOffsetCommand(parsedArgs.jumpOffset, programCounterUpdater); // (opcode === 'jmp')
    }
    return new NullProgramCounterCommand(programCounterUpdater);
  }

  static isValidInstruction(opcode) {
    return ['inc', 'tpl', 'hlf', 'jmp', 'jie'].includes(opcode);
  }

  static isValidRegisterOpcode(opcode) {
    return ['inc', 'tpl', 'hlf'].includes(opcode);
  }

  static isValidProgramCounterOpcode(opcode) {
    return ['jmp', 'jie'].includes(opcode);
  }
};
