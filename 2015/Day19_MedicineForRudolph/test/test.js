const DistinctMoleculeCounter = require('../src/DistinctMoleculeCounter');
const expect = require('chai').expect;
const fs = require("fs");
const util = require('util');
const readFilePromisified = util.promisify(fs.readFile);

context('Part One', () => {
  describe('Tests', () => {
    it('Small Molecule', async () => {
      let calibrationValues = await _initialize('./test/SmallMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate();
      expect(numberOfMolecules).to.equal(4);
    });

    it('Larger Molecule', async() => {
      let calibrationValues = await _initialize('./test/LargerMolecule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate();
      expect(numberOfMolecules).to.equal(7);
    });

    it('Molecule with no rule', async() => {
      let calibrationValues = await _initialize('./test/MoleculeNoRule.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate();
      expect(numberOfMolecules).to.equal(7);
    });
  });
  describe('Puzzle', () => {
    it('Puzzle Input', async () => {
      let calibrationValues = await _initialize('./test/Puzzle.txt');
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues);
      let numberOfMolecules = await distinctMoleculeCounter.calibrate();
      console.log('Number of Replacement Molecules: ', numberOfMolecules);
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