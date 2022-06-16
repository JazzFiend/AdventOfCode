const InstructionDecoder = require('../instructionDecoder');
const HalfCommand = require('../MicroprocessorCommands/RegisterCommands/halfCommand');
const IncrementCommand = require('../MicroprocessorCommands/RegisterCommands/incrementCommand');
const JumpOffsetCommand = require('../MicroprocessorCommands/ProgramCounterCommands/jumpOffsetCommand');
const TripleCommand = require('../MicroprocessorCommands/RegisterCommands/tripleCommand');
const Registers = require('../registers');
const NullRegisterCommand = require('../MicroprocessorCommands/RegisterCommands/nullRegisterCommand');
const NullProgramCounterCommand = require('../MicroprocessorCommands/ProgramCounterCommands/nullProgramCounterCommand');

describe('Instruction Decoder Tests', () => {
  describe('Decode Register Commands', () => {
    test('Increment Command Type', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('inc', 'a', new Registers());
      expect(command).toBeInstanceOf(IncrementCommand);
    });

    test('Triple Command Type', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('tpl', 'a', new Registers());
      expect(command).toBeInstanceOf(TripleCommand);
    });

    test('Half Command Type', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('hlf', 'a', new Registers());
      expect(command).toBeInstanceOf(HalfCommand);
    });

    test('A PC command should result in a Null Register command', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('jmp', 15, new Registers());
      expect(command).toBeInstanceOf(NullRegisterCommand);
    });
  });

  describe('Decode Program Counter Commands', () => {
    test('Jump Offset Command Type', () => {
      const command = InstructionDecoder.decodeProgramCounterInstruction('jmp', 4);
      expect(command).toBeInstanceOf(JumpOffsetCommand);
    });

    test('A Register command should result in a Null PC command', () => {
      const command = InstructionDecoder.decodeProgramCounterInstruction('inc', 'b', new Registers());
      expect(command).toBeInstanceOf(NullProgramCounterCommand);
    });
  });

  describe('Negative test cases', () => {
    test('decodeRegisterInstruction should throw on a bad opcode', () => {
      expect(
        () => InstructionDecoder.decodeRegisterInstruction('err', 'a', new Registers()),
      ).toThrow('Unknown opcode seen');
    });

    test('decodeProgramCounterInstruction should throw on a bad opcode', () => {
      expect(
        () => InstructionDecoder.decodeProgramCounterInstruction('ree', 10, new Registers()),
      ).toThrow('Unknown opcode seen');
    });

    test('Treating a register command as a PC command should result in a Null PC Command', () => {
      const command = InstructionDecoder.decodeProgramCounterInstruction('hlf', 7, new Registers());
      expect(command).toBeInstanceOf(NullProgramCounterCommand);
    });

    test('Giving a bad register to a Register instruction should result in a NullRegisterCommand', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('inc', 'g', new Registers());
      expect(command).toBeInstanceOf(NullRegisterCommand);
    });

    test('Treating a PC command as a register command should result in a Null Register Command', () => {
      const command = InstructionDecoder.decodeRegisterInstruction('jmp', 'b', new Registers());
      expect(command).toBeInstanceOf(NullRegisterCommand);
    });
  });
});
