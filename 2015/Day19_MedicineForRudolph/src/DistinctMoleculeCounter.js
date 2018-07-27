const fs = require("fs");
const MoleculeRules = require('./MoleculeRules');
const util = require('util');

module.exports = class MoleculeReplacer {
  constructor(calibrationValues) {
    this.moleculeRules = new MoleculeRules(calibrationValues.ruleStrings);
    this.calibrationMolecule = calibrationValues.calibrationMolecule;
  }

  async calibrate() {
    let tokenizedCalibrationMolecule = this._tokenizeMolecule(this.calibrationMolecule);
    let generatedMolecules = this.moleculeRules.advanceFormula(tokenizedCalibrationMolecule);
    return Object.keys(generatedMolecules).length;
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
      if (i === formula.length - 1 || this._isUpperCase(formula[i + 1])) {
        molecules.push(formula[i]);
      } else {
        molecules.push(formula[i] + formula[i + 1]);
        i++;
      }
    }
    return molecules;
  }
}
