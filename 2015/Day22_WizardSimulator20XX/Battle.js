
function Battle(player, boss) {
  this.player = player;
  this.boss = boss;
  this.effects = [];
  this.turn = player.getName()
  this.playerName = this.turn;
  this.bossName = 'boss';
}

Battle.prototype.constructor = Battle;

Battle.prototype.advanceTurn = function() {
  if(this.turn === player.getName()) {
    turn === 'boss';
  } else if(this.turn === 'boss') {
    this.turn === player.getName();
  } else {
    throw new Error("Incorrect turn seen");
  }
};

Battle.prototype.getTurn = function() {
  return this.turn;
}

Battle.prototype.takeTurn = function(turnActionMap) {
  var effectTarget;
  var armorModification = 0;
  effects.forEach(function(effect, index) {
    if(effect.getCharacterAffected() === 'self') {
      effectTarget = this.player;
    } else {
      effectTarget = this.boss;
    }

    if(effect.getAction() === 'armor') {
      armorModification = effect.getValue();
    } else if(getAction === 'mana') {
      effectTarget.addMana(effect.getValue());
    } else if(getAction === 'damage') {
      effectTarget.dealDamage(effect.getValue());
    } else {
      throw new Error("Incorrect Action Seen");
    }

    effect.advanceTurn();
    if(effect.getTurnCount === 0) {
      // Remove effect from list
    }
  });


  //Act on effects
    //Remove effects as needed

  //Perform action
    //Create effect

  //Switch turn

}

module.exports = Battle;
