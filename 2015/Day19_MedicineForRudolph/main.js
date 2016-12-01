var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));
var _ = require('lodash');
var MoleculeRules = require('./moleculeRules.js');

var moleculeRules;

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var addRule = function(rule) {
  var tokenizedRule = rule.split(' ');
  moleculeRules.addRule(tokenizedRule[0], tokenizedRule[2]);
}

var isUpperCase = function(char) {
  if (char.toUpperCase() === char) {
    return true;
  } else {
    return false;
  }
}

var tokenizeFormula = function(formula) {
  var molecules = [];
  for (var i = 0; i < formula.length; i++) {
    // If one letter is upper case and one is lower case, this is one molecule.
    if (i === formula.length - 1 || !isUpperCase(formula[i]) || (isUpperCase(formula[i]) && isUpperCase(formula[i + 1]))) {
      molecules.push(formula[i]);
    } else {
      molecules.push(formula[i] + formula[i + 1]);
      i++;
    }
  }
  return molecules;
}

// Main
var lines = getInputData("input.txt").then(function(lines) {
  var final = false;
  var finalFormula;
  var count = 0;
  var foundFormula = false;
  var currentFormulas = ['e'];
  var finalFormulaLength;
  moleculeRules = new MoleculeRules();

  lines.forEach(function(line) {
    if(line === '') {
      // Final Molecule is prefaced with a blank line.
      final = true;
    } else if(!final) {
      addRule(line);
    } else if(final) {
      finalFormula = line;
    }
  });
  // var tokenized = tokenizeFormula(startingFormula);
  // var newFormula = moleculeRules.advanceFormula(tokenized);
  // console.log("Distinct Molecules: ", newFormula.length);3
});
