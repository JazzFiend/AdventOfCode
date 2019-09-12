const MoleculeRules = require('./MoleculeRules');
const NEW_INITIAL_CALIBRATION = {
  'molecule': 'e',
  'replacementCount': 0
};

module.exports = class MoleculeReplacer {
  constructor(ruleStrings) {
    this.moleculeRules = new MoleculeRules(ruleStrings);
  }

  calibrate(calibrationMolecule) {
    let tokenizedCalibrationMolecule = this._tokenizeMolecule(calibrationMolecule);
    let generatedMolecules = this.moleculeRules.advanceFormula(tokenizedCalibrationMolecule);
    return generatedMolecules.length;
  }

  calculateFabricationSteps(finalMolecule) {
    let replacementList = [];

    replacementList.push(NEW_INITIAL_CALIBRATION);
    while(replacementList.length > 0) {
      console.log("Current Size of Molecule List:", replacementList.length);
      let moleculeToAdvance = replacementList.splice(this._getNextMolecule(replacementList), 1)[0];
      let tokenizedMolecule = this._tokenizeMolecule(moleculeToAdvance.molecule);
      let formulasToAdd = this.moleculeRules.advanceFormula(tokenizedMolecule);
      for(let formulaToAdd of formulasToAdd) {
        if(formulaToAdd === finalMolecule) {
          return moleculeToAdvance.replacementCount + 1;
        }
        if(formulaToAdd.length <= finalMolecule.length) {
          replacementList.push({'molecule': formulaToAdd, 'replacementCount': moleculeToAdvance.replacementCount + 1});
        }
      }
    }
    throw "Molecule Not Found";
  }

  _getNextMolecule(replacementList) {
    let largest = -1;
    let index;
    for(let i = 0; i < replacementList.length; i++) {
      if(replacementList[i].molecule.length > largest) {
        largest = replacementList[i].molecule.length;
        index = i;
      }
    }
    return index;
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
