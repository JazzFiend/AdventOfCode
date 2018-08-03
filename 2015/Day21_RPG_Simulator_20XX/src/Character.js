module.exports = class Character {
  constructor(name, armor, damage, hitPointMax) {
    this.name = name;
    this.armor = armor;
    this.damage = damage;
    this.hitPointMax = hitPointMax;
    this.hitPoints = hitPointMax;
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

  dealDamage(damage) {
    this.hitPoints -= damage;
  }
}
