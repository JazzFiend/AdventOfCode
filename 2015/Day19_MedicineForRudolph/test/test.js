const DistinctMoleculeCounter = require('../src/DistinctMoleculeCounter');
const assert = require('assert');
const fs = require("fs");
const util = require('util');
const readFilePromisified = util.promisify(fs.readFile);

context('Part One', () => {
  describe('Tests', () => {
    it('Small Molecule', async () => {
      let calibrationValues = await _initialize();
      let distinctMoleculeCounter = new DistinctMoleculeCounter(calibrationValues);
      distinctMoleculeCounter.calibrate().then((numberOfMolecules) => {
        assert(numberOfMolecules, 4);
      });
    });
  });
});

async function _initialize() {
  let counterInput = await _getInputData('testInput.txt');
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