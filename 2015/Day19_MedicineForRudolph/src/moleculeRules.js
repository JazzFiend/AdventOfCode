const MoleculeAdvancer = require('./MoleculeAdvancer');
const _ = require('lodash');

module.exports = class MoleculeRules {
  constructor(rules) {
    this.ruleList = {};
    _(rules).forEach((rule) => {
      this._parseRule(rule)
    });
  }

  displayRules() {
    _(Object.keys(this.ruleList)).forEach(function (startMolecule) {
      _(this.ruleList[startMolecule]).forEach(function (endMolecule) {
        console.log(startMolecule + ' =>', endMolecule);
      });
    });
  }

  advanceFormula(moleculeArray) {
    let formulaSet = {};
    let moleculeAdvancer;
    let newMolecules;

    _(moleculeArray).forEach((molecule, index) => {
      if (this.ruleList[molecule]) {
        moleculeAdvancer = new MoleculeAdvancer(moleculeArray, index);
        newMolecules = moleculeAdvancer.advanceMolecule(this.ruleList[molecule]);
        _(newMolecules).forEach((newMolecule) => {
          formulaSet[newMolecule] = true;
        });
      }
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
