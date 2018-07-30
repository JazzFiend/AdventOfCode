module.exports = class MoleculeAdvancer {
  constructor(moleculeArray, pivot) {
    this.moleculeArray = moleculeArray;
    this.pivot = pivot;
  }

  advanceMolecule(moleculeRule) {
    let preamble = this._extractPreamble();
    let postamble = this._extractPostamble();
    return this._assembleNewMolecules(preamble, postamble, moleculeRule);
  }

  _assembleNewMolecules(preamble, postamble, moleculeRule) {
    let newMolecules = [];
    for(let moleculeSubstitute of moleculeRule) {
      newMolecules.push(preamble + moleculeSubstitute + postamble);
    }
    return newMolecules;
  }

  _extractPostamble() {
    let postamble = "";
    for (let i = this.pivot + 1; i < this.moleculeArray.length; i++) {
      postamble += this.moleculeArray[i];
    }
    return postamble;
  }

  _extractPreamble() {
    let preamble = "";
    for (let i = 0; i < this.pivot; i++) {
      preamble += this.moleculeArray[i];
    }
    return preamble;
  }
}