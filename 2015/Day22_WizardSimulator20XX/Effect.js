function Effect(characterAffected, action, value, turnsActive) {
  this.characterAffected = characterAffected;
  this.action = action;
  this.value = value;
  this.turnCount = turnsActive;
}

Effect.prototype.constructor = Effect;

Effect.prototype.advanceTurn = function() {
  this.turnCount--;
}

Effect.prototype.getCharacterAffected = function() {
  return characterAffected;
}

Effect.prototype.getAction = function() {
  return action;
}

Effect.prototype.getValue = function() {
  return value;
}

Effect.prototype.getTurnCount = function() {
  return turnCount;
}

module.export = Effect;
