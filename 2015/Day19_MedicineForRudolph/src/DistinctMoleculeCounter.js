const fs = require("fs");
const _ = require('lodash');
const MoleculeRules = require('../src/moleculeRules');
const util = require('util');

module.exports = class MoleculeReplacer {
  constructor(calibrationValues) {
    this.moleculeRules = undefined;
    this.ruleStrings = calibrationValues.ruleStrings;
    this.calibrationMolecule = calibrationValues.calibrationMolecule;
  }

  async calibrate() {
    this.moleculeRules = new MoleculeRules(this.ruleStrings);
    let tokenizedCalibrationMolecule = this._tokenizeMolecule(this.calibrationMolecule);
    let generatedMolecules = this.moleculeRules.advanceFormula(tokenizedCalibrationMolecule);
    generatedMolecules = this.moleculeRules.removeDuplicates(generatedMolecules)
    return generatedMolecules.length;
  }

  _isUpperCase(char) {
    if (char.toUpperCase() === char) {
      return true;
    } else {
      return false;
    }
  }

  _tokenizeMolecule(formula) {
    let molecules = [];
    for (let i = 0; i < formula.length; i++) {
      if (i < formula.length - 1) {
        if (this._isUpperCase(formula[i + 1])) {
          molecules.push(formula[i]);
        } else {
          i++;
          molecules.push(formula[i] + formula[i + 1]);
        }
      }
    }
    return molecules;
  }
}
