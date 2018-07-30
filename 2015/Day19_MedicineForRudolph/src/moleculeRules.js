const MoleculeAdvancer = require('./MoleculeAdvancer');
const _ = require('lodash');

module.exports = class MoleculeRules {
  constructor(rules) {
    this.ruleList = {};
    for (let rule of rules) {
      this._parseRule(rule)
    }
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

    for(let i = 0; i < moleculeArray.length; i++) {
      let molecule = moleculeArray[i];
      if (this.ruleList[molecule]) {
        moleculeAdvancer = new MoleculeAdvancer(moleculeArray, i);
        newMolecules = moleculeAdvancer.advanceMolecule(this.ruleList[molecule]);
        for(let newMolecule of newMolecules) {
          formulaSet[newMolecule] = true;
        }
      }
    };
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
