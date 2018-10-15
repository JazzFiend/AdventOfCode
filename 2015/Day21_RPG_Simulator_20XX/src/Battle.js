module.exports = class Battle {
  constructor(characterOne, characterTwo) {
    this.characterOne = characterOne;
    this.characterTwo = characterTwo;
    this.verbose = false;
  }

  fight() {
    let attacker = this.characterOne;
    let defender = this.characterTwo;

    while(defender.getHitPoints() > 0) {
      defender.dealDamage(this._calculateDamage(attacker.getDamage(), defender.getArmor()));
      if (this.verbose) {
        console.log(this._constructVerboseMessage(attacker, defender));
      }
      if (defender.getHitPoints() > 0) {
        let temp = attacker;
        attacker = defender;
        defender = temp;
      }
    }
    return attacker;
  };

  _calculateDamage(attackValue, defenseValue) {
    if ((attackValue - defenseValue) <= 1) {
      return 1
    } else {
      return (attackValue - defenseValue);
    }
  }

  _constructVerboseMessage(attacker, defender) {
    return (`${attacker.getName()} deals ${(attacker.getDamage() - defender.getArmor())} damage\n
      ${defender.getName()} goes down to ${defender.getHitPoints()} hit points.`);
  }

  setVerbose() {
    this.verbose = true;
  };
}