module.exports = class Effect {
  constructor() {
    this.armorEnhancement = 0;
  }

  getName() {
    return this.name;
  }

  getTimer() {
    return this.timer;
  }

  getArmorModifier() {
    return this.armorEnhancement;
  }

  static constructEffectLog() {
    throw new Error('Abstract Mehtod');
  }
};
