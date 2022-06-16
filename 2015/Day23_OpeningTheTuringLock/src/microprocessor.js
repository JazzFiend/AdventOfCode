const InstructionDecoder = require('./instructionDecoder');
const Registers = require('./registers');

module.exports = class Microprocessor {
  constructor() {
    this.registers = new Registers();
    this.programCounter = 0;
  }

  runProgram(program) {
    this.programCounter = 0;

    while (this.programCounter >= 0 && this.programCounter < program.length) {
      const splitInstruction = program[this.programCounter].split(' ');
      const opcode = splitInstruction[0];
      const argument = splitInstruction[1];

      const registerCommand = InstructionDecoder.decodeRegisterInstruction(opcode, argument, this.registers);
      const programCounterCommand = InstructionDecoder.decodeProgramCounterInstruction(opcode, argument, this);

      registerCommand.execute();
      programCounterCommand.execute();
    }
  }

  getRegA() {
    return this.registers.getRegister('a');
  }

  getRegB() {
    return this.registers.getRegister('b');
  }

  setProgramCounter(offset) {
    this.programCounter += offset;
  }
};
