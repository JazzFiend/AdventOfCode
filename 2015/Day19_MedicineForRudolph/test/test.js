const DistinctMoleculeCounter = require('../src/DistinctMoleculeCounter');
const expect = require('chai').expect;
const fs = require("fs");
const util = require('util');
const readFilePromisified = util.promisify(fs.readFile);

context('Part One', () => {
  describe('Tests', () => {
    it('should calibrate correctly with a small molecule', async () => {
      let calibrationValues = await _initialize('./test/SmallMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate(calibrationValues.calibrationMolecule);
      expect(numberOfMolecules).to.equal(4);
    });

    it('should calibrate correctly with a larger molecule', async() => {
      let calibrationValues = await _initialize('./test/LargerMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate(calibrationValues.calibrationMolecule);
      expect(numberOfMolecules).to.equal(7);
    });

    it('should calibrate when the starter contains a molecule with no rule', async() => {
      let calibrationValues = await _initialize('./test/MoleculeNoRule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate(calibrationValues.calibrationMolecule);
      expect(numberOfMolecules).to.equal(7);
    });
  });
  describe('Puzzle', () => {
    it('Puzzle Input', async () => {
      let calibrationValues = await _initialize('./test/Puzzle.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate(calibrationValues.calibrationMolecule);
      console.log('Number of Replacement Molecules: ', numberOfMolecules);
    });
  });
});

context('Part Two', () => {
  describe('Tests', () => {
    it('should fabricate a small molecule', async () => {
      let calibrationValues = await _initialize('./test/SmallMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let fabricationSteps = await distinctMoleculeCounter.calculateFabricationSteps(calibrationValues.calibrationMolecule);
      expect(fabricationSteps).to.equal(3);
    });
    it('should fabricate a larger molecule', async () => {
      let calibrationValues = await _initialize('./test/LargerMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let fabricationSteps = await distinctMoleculeCounter.calculateFabricationSteps(calibrationValues.calibrationMolecule);
      expect(fabricationSteps).to.equal(6);
    });
  });
  // This solution should work, but it takes too long to arrive at an answer. Oh well...
  describe.skip('Puzzle', () => {
    it('Puzzle Input', async () => {
      let calibrationValues = await _initialize('./test/Puzzle.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues.ruleStrings);
      let fabricationSteps = await distinctMoleculeCounter.calculateFabricationSteps(calibrationValues.calibrationMolecule);
      console.log('Fabrication Steps: ', fabricationSteps);
    });
  });
});

async function _initialize(fileName) {
  let counterInput = await _getInputData(fileName);
  let ruleStrings = _extractRuleStrings(counterInput);
  let calibrationMolecule = _extractCalibrationMolecule(counterInput);
  return { ruleStrings, calibrationMolecule };
}

let _getInputData = async function (fileName) {
  let data = await readFilePromisified(fileName);
  return data.toString().split('\n');
}

let _extractRuleStrings = function (counterInput) {
  let ruleStrings = [];
  let lastLine = false;
  (counterInput).forEach((rule) => {
    if(!lastLine) {
      if(rule === '') {
        lastLine = true;
      } else {
        ruleStrings.push(rule);
      }
    }
  });
  return ruleStrings;
}

let _extractCalibrationMolecule = function (counterInput) {
  let finalFormula;
  let lastLine = false;
  (counterInput).forEach((rule) => {
    if (rule === '') {
      lastLine = true;
    } else if (lastLine) {
      finalFormula = rule;
    }
  });
  return finalFormula;
}