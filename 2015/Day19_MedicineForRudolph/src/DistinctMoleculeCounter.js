const MoleculeRules = require('./MoleculeRules');
const INITIAL_CALIBRATION = { 'e': true };

module.exports = class MoleculeReplacer {
  constructor(ruleStrings) {
    this.moleculeRules = new MoleculeRules(ruleStrings);
  }

  calibrate(calibrationMolecule) {
    let tokenizedCalibrationMolecule = this._tokenizeMolecule(calibrationMolecule);
    let generatedMolecules = this.moleculeRules.advanceFormula(tokenizedCalibrationMolecule);
    return Object.keys(generatedMolecules).length;
  }

  calculateFabricationSteps(finalMolecule) {
    let formulaSet = INITIAL_CALIBRATION;
    let counter = 0;
    while (!formulaSet[finalMolecule]) {
      let newFormulaSet = this._advanceAllMolecules(formulaSet, finalMolecule.length);
      formulaSet = newFormulaSet;
      counter++;
      console.log("Current Count: ", counter);
      console.log("Formula Count: ", Object.keys(formulaSet).length);
    }
    return counter;
  }

  _advanceAllMolecules(formulaSet, maxLength) {
    let newFormulaSet = {};
    for(let formula of Object.keys(formulaSet)) {
      let tokenizedCalibrationMolecule = this._tokenizeMolecule(formula);
      let formulasToAdd = this.moleculeRules.advanceFormula(tokenizedCalibrationMolecule);
      for(let nextFormula of Object.keys(formulasToAdd)) {
        if (nextFormula.length <= maxLength) {
          newFormulaSet[nextFormula] = true;
        }
      }
    }
    return newFormulaSet;
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
