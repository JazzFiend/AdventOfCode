const InstructionDecoder = require('./instructionDecoder');
const Registers = require('./registers');

module.exports = class Microprocessor {
  constructor() {
    this.registers = new Registers();
  }

  runProgram(program) {
    let programCounter = 0;

    while (programCounter >= 0 && programCounter < program.length) {
      const splitInstruction = program[programCounter].split(' ');
      const opcode = splitInstruction[0];
      const argument = splitInstruction[1];

      const registerCommand = InstructionDecoder.decodeRegisterInstruction(opcode, argument, this.registers);
      const programCounterCommand = InstructionDecoder.decodeProgramCounterInstruction(opcode, argument);

      registerCommand.execute();
      // TODO: We shouldn't modify the PC here. It should be delegated by the Commands to another class.
      programCounter += programCounterCommand.execute(argument);
    }
  }

  getRegA() {
    return this.registers.getRegister('a');
  }

  getRegB() {
    return this.registers.getRegister('b');
  }
};
