const TemporaryEffect = require('./TemporaryEffect');

module.exports = class ShieldEffect extends TemporaryEffect {
  constructor() {
    super();
    this.name = 'Shield';
    this.armorEnhancement = 7;
    this.timer = 6;
  }

  applyEffect(character, battleLogger) {
    battleLogger.log(ShieldEffect.constructEffectLog(character.getName()));
    this.decrementTimer();
  }

  static constructEffectLog(name) {
    return `${name} is protected by a shield!`;
  }

  getArmorModifier() {
    return this.armorEnhancement;
  }
};
