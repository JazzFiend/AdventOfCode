function MoleculeRules() {
  this.ruleList = {};
}

MoleculeRules.prototype = {
  constructor: MoleculeRules,

  addRule: function(start, finish) {
    if (this.ruleList[start] === undefined) {
      this.ruleList[start] = [];
    }
    this.ruleList[start].push(finish);
  },

  displayRules: function() {
    var rules = this.ruleList;
    Object.keys(rules).forEach(function(startMolecule) {
      rules[startMolecule].forEach(function(endMolecule) {
        console.log(startMolecule + ' =>', endMolecule);
      });
    });
  },

  removeDuplicates: function(array) {
    var hashMap = {};
    array.forEach(function(element) {
      hashMap[element] = 1;
    });
    return Object.keys(hashMap);
  },

  advanceFormula: function(moleculeArray) {
    var formulaList = [];
    var rules = this.ruleList;

    moleculeArray.forEach(function(molecule, index) {
      var preamble = "";
      var postamble = "";

      if(rules[molecule] === undefined) {
        return;
      }

      for(var i = 0; i < index; i++) {
        preamble += moleculeArray[i];
      }
      for(var i = index + 1; i < moleculeArray.length; i++) {
        postamble += moleculeArray[i];
      }

      rules[molecule].forEach(function(moleculeSubstitute) {
        formulaList.push(preamble + moleculeSubstitute + postamble);
      });
    });
    return formulaList;
  }
};

module.exports = MoleculeRules;
