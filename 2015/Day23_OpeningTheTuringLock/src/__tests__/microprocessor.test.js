const Microprocessor = require('../microprocessor');

let microprocessor;
beforeEach(() => {
  microprocessor = new Microprocessor();
});

function validateRegisters(a, b) {
  expect(microprocessor.getRegA()).toEqual(a);
  expect(microprocessor.getRegB()).toEqual(b);
}

describe('Microprocessor Programs', () => {
  test('Empty instruction list should return registers at default values', () => {
    microprocessor.runProgram([]);
    validateRegisters(0, 0);
  });

  test('A simple program should be run', () => {
    microprocessor.runProgram(['inc a', 'inc b']);
    validateRegisters(1, 1);
  });

  test('A simple program with a jump should skip instructions', () => {
    microprocessor.runProgram(['inc a', 'jmp 3', 'inc b', 'inc b', 'inc a']);
    validateRegisters(2, 0);
  });

  test('A negative program counter should stop execution', () => {
    microprocessor.runProgram(['jmp -1', 'inc a', 'inc b']);
    validateRegisters(0, 0);
  });

  test('A command with two arguments should work correctly', () => {
    microprocessor.runProgram(['inc a', 'inc a', 'jie a, -1']);
    validateRegisters(3, 0);
  });
});
