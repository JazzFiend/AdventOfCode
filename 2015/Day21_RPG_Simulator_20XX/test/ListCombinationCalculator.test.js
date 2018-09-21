const expect = require('chai').expect;
const ListCombinationCalculator = require('../src/ListCombinationCalculator');

describe('ListCombinationCalculator', () => {
  it('should return the original list when a list of one item is submitted', () => {
    let testList = [1];
    let groupSize = 1; 
    let expectedLists = [[1]]

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should return list of one element apiece when a groupsize of 1 is given', () => {
    let testList = [1, 2, 3];
    let groupSize = 1;
    let expectedLists = [[1], [2], [3]];

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should work correctly when a list of strings is supplied', () => {
    let testList = ['a', 'b', 'c'];
    let groupSize = 1;
    let expectedLists = [['a'], ['b'], ['c']];

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should return the correct combinations for a three item list and a grouping of two', () => {
    let testList = [1, 2, 3];
    let groupSize = 2;
    let expectedLists = [[2, 1], [3, 1], [3, 2]];

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize, []);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should return the correct combinations for a four item list and a grouping of three', () => {
    let testList = [1, 2, 3, 4];
    let groupSize = 3;
    let expectedLists = [[3, 2, 1], [4, 2, 1], [4, 3, 1], [4, 3, 2]];

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should allow doubles when a list of double items is given and a groupiung of two is given', () => {
    let testList = [1, 2, 3];
    let groupSize = 2;
    let allowedDoubles = [1];
    let expectedLists = [[1, 1], [2, 1], [3, 1], [3, 2]];

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize, allowedDoubles);
    expect(combinations).to.deep.equal(expectedLists);
  });

  it('should allow doubles when a list of double items is given and the grouping is three', () => {
    let testList = [1, 2, 3, 4];
    let groupSize = 3;
    let allowedDoubles = [1, 2];
    let expectedLists =  [
      [1, 1, 1],
      [2, 1, 1],
      [3, 1, 1],
      [4, 1, 1],
      [2, 2, 1],
      [3, 2, 1],
      [4, 2, 1],
      [4, 3, 1],
      [2, 2, 2],
      [3, 2, 2],
      [4, 2, 2],
      [4, 3, 2],
    ]

    let combinations = ListCombinationCalculator.computeCombinations(testList, groupSize, allowedDoubles);
    expect(combinations).to.deep.equal(expectedLists);
  });
});