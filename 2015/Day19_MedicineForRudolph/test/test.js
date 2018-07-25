let DistinctMoleculeCounter = require('../src/DistinctMoleculeCounter');
let assert = require('assert');

context('Part One', () => {
  describe('Tests', () => {
    it('Dummy Test', () => {
        let distinctMoleculeCounter = new DistinctMoleculeCounter();
        distinctMoleculeCounter.calibrate().then((numberOfMolecules) => {
          assert(numberOfMolecules, 4);
        });
    });
  });
});