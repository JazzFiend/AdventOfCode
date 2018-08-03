const Item = require('./Item');
const Player = require('./Player');

module.exports = class Shop {
  constructor() {
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

  getWeapons() {
    return this.weaponList;
  }

  getArmor() {
    return this.armorList;
  }

  getRings() {
    return this.ringList;
  }

  computeEquipmentCombinations() {
    var weaponListWithBlank = this.weaponList;
    var armorListWithBlank = this.armorList;
    var ringListWithBlank = this.ringList;
    var weaponGroupings;
    var armorGroupings;
    var ringGroupings;
    var toReturn = [];

    if (Player.getEquipmentSlots().minWeapons === 0) {
      weaponListWithBlank.push(new Item('weapon', 'none', 0, 0, 0));
    }
    if (Player.getEquipmentSlots().minArmor === 0) {
      armorListWithBlank.push(new Item('armor', 'none', 0, 0, 0));
    }
    if (Player.getEquipmentSlots().minRings === 0) {
      ringListWithBlank.push(new Item('ring', 'none', 0, 0, 0));
    }

    weaponGroupings = this._calculateGroupings(weaponListWithBlank, Player.getEquipmentSlots().maxWeapons);
    armorGroupings = this._calculateGroupings(armorListWithBlank, Player.getEquipmentSlots().maxArmor);
    ringGroupings = this._calculateGroupings(ringListWithBlank, Player.getEquipmentSlots().maxRings);

    var count = 0;
    weaponGroupings.forEach(function (weaponGroup) {
      armorGroupings.forEach(function (armorGroup) {
        ringGroupings.forEach(function (ringGroup) {
          var itemCollection = [];
          weaponGroup.forEach(function (weapon) {
            itemCollection.push(weapon);
          });
          armorGroup.forEach(function (armor) {
            itemCollection.push(armor);
          });
          ringGroup.forEach(function (ring) {
            itemCollection.push(ring);
          });
          toReturn.push(itemCollection);
        });
      });
    });
    return toReturn;
  }

  _calculateGroupings(list, groupSize) {
    var toReturn = [];
    list.forEach((element) => {
      var dummyList = list.slice();
      var index = dummyList.indexOf(element);
      var returnedGroupings;
      if (!this._checkDuplicateExceptions(element)) {
        dummyList.splice(0, index + 1);
      } else {
        dummyList.splice(0, index);
      }
      if (groupSize > 1) {
        returnedGroupings = this._calculateGroupings(dummyList, groupSize - 1);
        returnedGroupings.forEach(function (grouping) {
          grouping.push(element);
          toReturn.push(grouping);
        });
      } else {
        var tempArray = [];
        tempArray.push(element);
        toReturn.push(tempArray);
      }
    });
    return toReturn;
  }

  _checkDuplicateExceptions(item) {
    if (item.getName() === 'none') {
      return true;
    } else {
      return false;
    }
  }
}
