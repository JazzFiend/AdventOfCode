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
    let newMolecules = [];
    for(let i = 0; i < moleculeArray.length; i++) {
      let molecule = moleculeArray[i];
      if (this.ruleList[molecule]) {
        let advancedMolecules = this._advanceMolecule(moleculeArray, i, molecule);
        this._addToNewMolecules(advancedMolecules, newMolecules);
      }
    };
    return newMolecules;
  }

  _addToNewMolecules(advancedMolecules, newMolecules) {
    for (let advancedMolecule of advancedMolecules) {
      if (!newMolecules.includes(advancedMolecule)) {
        newMolecules.push(advancedMolecule);
      }
    }
  }

  _advanceMolecule(moleculeArray, i, molecule) {
    let moleculeAdvancer = new MoleculeAdvancer(moleculeArray, i);
    return moleculeAdvancer.advanceMolecule(this.ruleList[molecule]);
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
