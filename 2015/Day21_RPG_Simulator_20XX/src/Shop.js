var Item = require('./Item.js');

function Shop() {
  this.weaponList = [
    new Item('weapon', 'Dagger', 8, 4, 0),
    new Item('weapon', 'Shortsword', 10, 5, 0),
    new Item('weapon', 'Warhammer', 25, 6, 0),
    new Item('weapon', 'Longsword', 40, 7, 0),
    new Item('weapon', 'Greataxe', 74, 8, 0)
  ];
  this.armorList = [
    new Item('armor', 'Leather', 13, 0, 1),
    new Item('armor', 'Chainmail', 31, 0, 2),
    new Item('armor', 'Splintmail', 53, 0, 3),
    new Item('armor', 'Bandedmail', 75, 0, 4),
    new Item('armor', 'Platemail', 102, 0, 5)
  ];
  this.ringList = [
    new Item('ring', 'Damage +1', 25, 1, 0),
    new Item('ring', 'Damage +2', 50, 2, 0),
    new Item('ring', 'Damage +3', 100, 3, 0),
    new Item('ring', 'Defense +1', 20, 0, 1),
    new Item('ring', 'Defense +2', 40, 0, 2),
    new Item('ring', 'Defense +3', 80, 0, 3)
  ];
}

Shop.prototype = {
  construtor: Shop,

  getWeapons: function() {
    return this.weaponList;
  },

  getArmor: function() {
    return this.armorList;
  },

  getRings: function() {
    return this.ringList;
  }
}

module.exports = Shop;
