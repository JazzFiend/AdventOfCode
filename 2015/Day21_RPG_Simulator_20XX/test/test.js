const expect = require('chai').expect;

const RpgSimulator = require('../src/RpgSimulator');

describe('Tests', () => {
  it('should simulate the correct battle', () => {
    let testLowestCost = 8;
    let testHighestCost = -1;
    let simulator = new RpgSimulator();
    let costs = simulator.calculateBattleCombinations(2, 7, 12);
    expect(simulator.getLowestCost()).to.deep.equal(testLowestCost);
    expect(simulator.getHighestCost()).to.deep.equal(testHighestCost);
  });
});
describe('Puzzle', () => {
  it('should simulate the correct battle', () => {
    let simulator = new RpgSimulator();
    simulator.calculateBattleCombinations(2, 9, 103);
    console.log(simulator.getLowestCost());
    console.log(simulator.getHighestCost());
    //121 and 201
  });
});
