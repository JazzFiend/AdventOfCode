const TemporaryEffect = require('./TemporaryEffect');

module.exports = class PoisonEffect extends TemporaryEffect {
  constructor() {
    super();
    this.name = 'Poison';
    this.poisonDamage = 3;
    this.timer = 6;
  }

  applyEffect(character, battleLogger) {
    character.dealDamage(this.poisonDamage);
    battleLogger.log(PoisonEffect.constructEffectLog(character.getName(), this.poisonDamage));
    this.decrementTimer();
  }

  static constructEffectLog(name, damage) {
    return `${name} is affected by poison and suffers ${damage} damage!`;
  }
};
