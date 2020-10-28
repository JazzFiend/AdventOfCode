module.exports = class Character {
  constructor(name, armor, damage, hitPointMax) {
    this.name = name;
    this.armor = armor;
    this.damage = damage;
    this.hitPointMax = hitPointMax;
    this.hitPoints = hitPointMax;
    this.effectList = [];
  }

  getDamage() {
    return this.damage;
  }

  getArmor() {
    return this.armor;
  }

  getHitPoints() {
    return this.hitPoints;
  }

  getName() {
    return this.name;
  }

  getEffects() {
    return this.effectList;
  }

  dealDamage(damage) {
    this.hitPoints -= damage;
  }

  isCharacterDefeated() {
    return this.hitPoints <= 0;
  }

  addEffect(effect) {
    this.effectList.push(effect);
  }

  removeEffect(effect) {
    const newEffectList = [];
    this.effectList.forEach((effectElement) => {
      if (effectElement.getName() !== effect) {
        newEffectList.push(effectElement);
      }
    });
    this.effectList = newEffectList;
  }

  hasEffect(effectName) {
    let hasEffect = false;
    this.effectList.forEach((effect) => {
      if (effect.getName() === effectName) {
        hasEffect = true;
      }
    });
    return hasEffect;
  }

  constructStatusMessage() {
    return `-- ${this.name} has ${this.hitPoints} HP --${this.constructEffectsMessage()}`;
  }

  constructEffectsMessage() {
    let effects = '';
    this.effectList.forEach((effect) => {
      effects += `\n- Affected by ${effect.getName()}. Timer: ${effect.getTimer()} -`;
    });
    return effects;
  }
};
