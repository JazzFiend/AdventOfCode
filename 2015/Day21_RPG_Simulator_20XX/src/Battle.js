function Battle(characterOne, characterTwo) {
  this.characterOne = characterOne;
  this.characterTwo = characterTwo;
  this.verbose = false;
}

Battle.prototype.constructor = Battle;

Battle.prototype.fight = function() {
  var battleComplete = false;
  var attacker = this.characterOne;
  var defender = this.characterTwo;
  var winner;

  while(!battleComplete) {
    var attackValue = attacker.getDamage();
    var defenseValue = defender.getArmor();
    if((attackValue - defenseValue) <= 1) {
      defender.dealDamage(1);
    } else {
      defender.dealDamage((attackValue - defenseValue));
    }

    if(this.verbose) {
      console.log(attacker.getName() + " deals " + (attackValue - defenseValue) + " damage; " +
                  defender.getName() + " goes down to " + defender.getHitPoints() + " hit points.");
    }

    if(defender.getHitPoints() <= 0) {
      battleComplete = true;
      winner = attacker;
    } else {
      var temp = attacker;
      attacker = defender;
      defender = temp;
    }
  }
  return winner;
};

Battle.prototype.setVerbose = function() {
  this.verbose = true;
};

module.exports = Battle;
