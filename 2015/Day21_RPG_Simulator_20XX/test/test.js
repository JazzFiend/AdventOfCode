const expect = require('chai').expect;

const RpgSimulator = require('../src/main');

describe('Tests', () => {
  it('should simulate the correct battle', () => {
    let compare = {
      lowestCost: 8,
      highestCost: -1
    }
    let simulator = new RpgSimulator();
    let costs = simulator.main(2, 7, 12);
    expect(costs).to.deep.equal(compare);
  });
});
describe('Puzzle', () => {
  it('should simulate the correct battle', () => {
    let simulator = new RpgSimulator();
    let costs = simulator.main(2, 9, 103);
    console.log(costs);
    //121 and 201
  });
});
