const InstructionDecoder = require('./instructionDecoder');
const Registers = require('./registers');

module.exports = class Microprocessor {
  constructor(regA = 0, regB = 0) {
    this.registers = new Registers();
    this.registers.setRegister('a', regA);
    this.registers.setRegister('b', regB);
    this.programCounter = 0;
  }

  runProgram(program) {
    this.programCounter = 0;

    while (this.programCounter >= 0 && this.programCounter < program.length) {
      const splitInstruction = program[this.programCounter].split(' ');
      const opcode = splitInstruction[0];

      const argumentList = [];
      argumentList.push(splitInstruction[1]);
      if (splitInstruction.length >= 3) {
        argumentList[0] = argumentList[0].slice(0, 1);
        argumentList.push(splitInstruction[2]);
      }

      const registerCommand = InstructionDecoder.decodeRegisterInstruction(opcode, argumentList, this.registers);
      const programCounterCommand = InstructionDecoder.decodeProgramCounterInstruction(
        opcode,
        argumentList,
        this.registers,
        this,
      );

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
