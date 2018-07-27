const MoleculeAdvancer = require('./MoleculeAdvancer');

module.exports = class MoleculeRules {
  constructor(rules) {
    this.ruleList = {};
    rules.forEach((rule) => {
      this._parseRule(rule)
    });
  }

  displayRules() {
    var rules = this.ruleList;
    Object.keys(rules).forEach(function (startMolecule) {
      rules[startMolecule].forEach(function (endMolecule) {
        console.log(startMolecule + ' =>', endMolecule);
      });
    });
  }

  advanceFormula(moleculeArray) {
    let formulaSet = {};
    let moleculeAdvancer;
    let newMolecules;

    moleculeArray.forEach((molecule, index) => {
      moleculeAdvancer = new MoleculeAdvancer(moleculeArray, index);
      newMolecules = moleculeAdvancer.advanceMolecule(this.ruleList[molecule]);
      newMolecules.forEach((newMolecule) => {
        formulaSet[newMolecule] = true;
      });
    });
    return formulaSet;
  }

  _parseRule(rule) {
    var tokenizedRule = rule.split(' ');
    this._addRule(tokenizedRule[0], tokenizedRule[2]);
  }

  _addRule(start, finish) {
    if (this.ruleList[start] === undefined) {
      this.ruleList[start] = [];
    }
    this.ruleList[start].push(finish);
  }
};
