const fs = require('fs');
const util = require('util');
const Microprocessor = require('../microprocessor');

const readFilePromisified = util.promisify(fs.readFile);

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

  test('The default values of the registers should be specified', () => {
    microprocessor = new Microprocessor(1, 2);
    microprocessor.runProgram(['inc a', 'inc b']);
    validateRegisters(2, 3);
  });

  test('Example Program', () => {
    microprocessor.runProgram(['inc a', 'jio a, +2', 'tpl a', 'inc a']);
    validateRegisters(2, 0);
  });

  test('Puzzle Part 1', async () => {
    const data = await readFilePromisified('./src/__tests__/PuzzleInput/input.txt');
    const instructions = data.toString().split('\n');
    microprocessor.runProgram(instructions);
    validateRegisters(1, 307);
  });

  test('Puzzle Part 2', async () => {
    const data = await readFilePromisified('./src/__tests__/PuzzleInput/input.txt');
    const instructions = data.toString().split('\n');
    microprocessor = new Microprocessor(1, 0);
    microprocessor.runProgram(instructions);
    validateRegisters(1, 160);
  });
});
