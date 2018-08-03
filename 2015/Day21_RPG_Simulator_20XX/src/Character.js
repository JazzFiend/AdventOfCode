function Character(name, armor, damage, hitPointMax) {
  this.name = name;
  this.armor = armor;
  this.damage = damage;
  this.hitPointMax = hitPointMax;
  this.hitPoints = hitPointMax;
}

Character.prototype = {
  constructor: Character,

  getDamage: function() {
    return this.damage;
  },

  getArmor: function() {
    return this.armor;
  },

  getHitPoints: function() {
    return this.hitPoints;
  },

  getName: function() {
    return this.name;
  },

  dealDamage: function(damage) {
    this.hitPoints -= damage;
  }
}

module.exports = Character;
