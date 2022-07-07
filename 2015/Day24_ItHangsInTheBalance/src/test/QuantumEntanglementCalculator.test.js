const QuantumEntanglementCalculator = require('../main/QuantumEntanglementCalculator');

describe('QuantumEntanglementCalculator', () => {
  test('An empty list should return a QE of zero', () => {
    expect(QuantumEntanglementCalculator.calculateMinimum([], 3)).toEqual(0);
  });

  test('A list of weights that isn\'t divisible by 3 should return a QE of zero', () => {
    expect(QuantumEntanglementCalculator.calculateMinimum([1, 2, 3, 4], 3)).toEqual(0);
  });

  test('The smallest valid configuration is of size 1', () => {
    expect(QuantumEntanglementCalculator.calculateMinimum([1, 5, 2, 4, 6], 3)).toEqual(6);
  });

  test('The smallest valid configuration is of size 2', () => {
    expect(QuantumEntanglementCalculator.calculateMinimum([2, 2, 1, 1, 1, 1, 1, 1, 1, 1], 3)).toEqual(4);
  });

  test('Multiple smallest valid configurations should return the smallest QE', () => {
    expect(QuantumEntanglementCalculator.calculateMinimum([5, 6, 8, 1, 2, 7, 4], 3)).toEqual(28);
  });

  test('Acceptance Test', () => {
    const input = [1, 2, 3, 4, 5, 7, 8, 9, 10, 11];
    expect(QuantumEntanglementCalculator.calculateMinimum(input, 3)).toEqual(99);
  });

  test('Puzzle One', () => {
    const input = [1, 2, 3, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59,
      61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113];
    expect(QuantumEntanglementCalculator.calculateMinimum(input, 3)).toEqual(11846773891);
  });

  test('Acceptance Test - Four buckets', () => {
    const input = [1, 2, 3, 4, 5, 7, 8, 9, 10, 11];
    expect(QuantumEntanglementCalculator.calculateMinimum(input, 4)).toEqual(44);
  });

  test('Puzzle Two', () => {
    const input = [1, 2, 3, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59,
      61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113];
    expect(QuantumEntanglementCalculator.calculateMinimum(input, 4)).toEqual(80393059);
  });
});
