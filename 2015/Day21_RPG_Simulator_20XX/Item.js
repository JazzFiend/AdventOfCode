// Constructor
function Item(type, name, cost, damageIncrease, armorIncrease) {
  this.type = type;
  this.name = name;
  this.cost = cost;
  this.damageIncrease = damageIncrease;
  this.armorIncrease = armorIncrease;
}

Item.prototype = {
  constructor: Item,

  getType: function() {
    return this.type;
  },

  getName: function() {
    return this.name;
  },

  getCost: function() {
    return this.cost;
  },

  getAttack: function() {
    return this.damageIncrease;
  },

  getDefense: function() {
    return this.armorIncrease;
  }
}

module.exports = Item;
