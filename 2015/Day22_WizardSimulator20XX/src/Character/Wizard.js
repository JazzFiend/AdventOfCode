const Character = require('./Character');

module.exports = class Wizard extends Character {
  constructor(name, hitPointsMax, manaMax) {
    super(name, 0, 0, hitPointsMax);
    this.manaMax = manaMax;
    this.mana = manaMax;
    this.totalSpentMana = 0;
  }

  getArmor() {
    let totalArmor = this.armor;
    this.effectList.forEach((effect) => {
      totalArmor += effect.getArmorModifier();
    });
    return totalArmor;
  }

  getMana() {
    return this.mana;
  }

  getSpentMana() {
    return this.totalSpentMana;
  }

  spendMana(manaToSpend) {
    if (this.mana < manaToSpend) {
      throw new Error('Out of Mana');
    }
    this.mana -= manaToSpend;
    this.totalSpentMana += manaToSpend;
  }

  chargeMana(manaGain) {
    this.mana += manaGain;
    if (this.mama > this.manaMax) {
      this.mana = this.manaMax;
    }
  }

  healDamage(heal) {
    this.hitPoints += heal;
    if (this.hitPoints > this.hitPointMax) {
      this.hitPoints = this.hitPointMax;
    }
  }

  constructStatusMessage() {
    let statusMesssage = `-- ${this.name} has ${this.hitPoints} HP, ${this.getArmor()} armor, and ${this.mana} mana`;
    statusMesssage += `--${this.constructEffectsMessage()}`;
    return statusMesssage;
  }
};
