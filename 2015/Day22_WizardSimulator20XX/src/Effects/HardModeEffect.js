const PermanentEffect = require('./PermanentEffect');

module.exports = class HardModeEffect extends PermanentEffect {
  constructor() {
    super();
    this.name = 'Hard Mode Damage';
    this.poisonDamage = 1;
  }

  applyEffect(character, battleLogger, isCharactersTurn) {
    if (isCharactersTurn) {
      character.dealDamage(this.poisonDamage);
      battleLogger.log(HardModeEffect.constructEffectLog(character.getName(), this.poisonDamage));
    }
  }

  static constructEffectLog(name, damage) {
    return `${name} is affected by poison and suffers ${damage} damage!`;
  }

  getArmorModifier() {
    return this.armorEnhancement;
  }
};
