let fs = require("fs");
let _ = require('lodash');
let MoleculeRules = require('../src/moleculeRules');

module.exports = class MoleculeReplacer {
  constructor () {
    this.moleculeRules = undefined;
  }
  
  getInputData(fileName) {
    var lines;
    return new Promise((resolve, reject) => {
      fs.readFile(fileName, (err, data) => {
        if(err) reject(err);
        lines = data.toString().split('\n');
        resolve(lines);
      });
    });
  }
  
  addRule(rule) {
    var tokenizedRule = rule.split(' ');
    this.moleculeRules.addRule(tokenizedRule[0], tokenizedRule[2]);
  }
  
  isUpperCase(char) {
    if (char.toUpperCase() === char) {
      return true;
    } else {
      return false;
    }
  }
  
  tokenizeFormula(formula) {
    var molecules = [];
    for (var i = 0; i < formula.length; i++) {
      // If one letter is upper case and one is lower case, this is one molecule.
      if (i === formula.length - 1 || !this.isUpperCase(formula[i]) || (this.isUpperCase(formula[i]) && this.isUpperCase(formula[i + 1]))) {
        molecules.push(formula[i]);
      } else {
        molecules.push(formula[i] + formula[i + 1]);
        i++;
      }
    }
    return molecules;
  }
  
  calibrate() {
    return new Promise((resolve, reject) => {
      this.getInputData('testInput.txt').then((lines) => {
        var final = false;
        var finalFormula;
        var count = 0;
        var foundFormula = false;
        var currentFormulas = ['e'];
        var finalFormulaLength;
  
        this.moleculeRules = new MoleculeRules();
        ({ final, finalFormula } = this.populateRules(lines, final, finalFormula));
        var tokenized = this.tokenizeFormula(finalFormula);
        var newFormula = this.moleculeRules.advanceFormula(tokenized);
        newFormula = this.moleculeRules.removeDuplicates(newFormula)
        //console.log("newFormula", newFormula)
        //console.log("Distinct Molecules: ", );
        console.log('end of main', newFormula.length)
        resolve(newFormula.length);
      });
    });
  } 

  populateRules(lines, final, finalFormula) {
    lines.forEach((line) => {
      if (line === '') {
        // Final Molecule is prefaced with a blank line.
        final = true;
      }
      else if (!final) {
        console.log(this.moleculeRules);
        this.addRule(line);
      }
      else if (final) {
        finalFormula = line;
      }
    });
    return { final, finalFormula };
  }
}