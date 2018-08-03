var Shop = require('./Shop.js');
var Item = require('./Item.js');
var Player = require('./Player.js');
var Battle = require('./Battle.js');
var Character = require('./Character.js');

var MIN_WEAPONS = 1;
var MAX_WEAPONS = 1;
var MIN_ARMOR = 0;
var MAX_ARMOR = 1;
var MIN_RINGS = 0;
var MAX_RINGS = 2

var DEFAULT_HIT_POINTS = 100;

module.exports = class Main {
  checkDuplicateExceptions(item) {
    if (item.getName() === 'none') {
      return true;
    } else {
      return false;
    }
  }

  calculateGroupings(list, groupSize) {
    var toReturn = [];
    list.forEach((element) => {
      var dummyList = list.slice();
      var index = dummyList.indexOf(element);
      var returnedGroupings;
      if (!this.checkDuplicateExceptions(element)) {
        dummyList.splice(0, index + 1);
      } else {
        dummyList.splice(0, index);
      }
      if (groupSize > 1) {
        returnedGroupings = this.calculateGroupings(dummyList, groupSize - 1);
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

  computeEquipmentCombinations(weaponList, armorList, ringList) {
    var updatedWeaponList = weaponList;
    var updatedArmorList = armorList;
    var updatedRingList = ringList;
    var weaponGroupings;
    var armorGroupings;
    var ringGroupings;
    var toReturn = [];

    if (MIN_WEAPONS === 0) {
      updatedWeaponList.push(new Item('weapon', 'none', 0, 0, 0));
    }
    if (MIN_ARMOR === 0) {
      updatedArmorList.push(new Item('armor', 'none', 0, 0, 0));
    }
    if (MIN_RINGS === 0) {
      updatedRingList.push(new Item('ring', 'none', 0, 0, 0));
    }

    weaponGroupings = this.calculateGroupings(updatedWeaponList, MAX_WEAPONS);
    armorGroupings = this.calculateGroupings(updatedArmorList, MAX_ARMOR);
    ringGroupings = this.calculateGroupings(updatedRingList, MAX_RINGS);

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

  equipPlayers(equipmentCombinations) {
    let toReturn = [];
    equipmentCombinations.forEach((equipmentCombination, index) => {
      toReturn.push(new Player("Player " + index, DEFAULT_HIT_POINTS, equipmentCombination));
    });
    return toReturn;
  }

  main(armor, damage, hitPointMax) {
    // main
    var shop = new Shop();
    var equipmentCombinations;
    var characterCollection = [];
    var battle;
    var lowestCost = 1000000;
    var highestCost = -1;

    equipmentCombinations = this.computeEquipmentCombinations(shop.getWeapons(), shop.getArmor(), shop.getRings());
    characterCollection = this.equipPlayers(equipmentCombinations);

    characterCollection.forEach(function (character) {
      var boss = new Character('boss', armor, damage, hitPointMax);
      var winner;

      battle = new Battle(character, boss);
      //battle.setVerbose();
      winner = battle.fight();
      if (winner.getName() !== 'boss') {
        if (winner.getEquipmentCost() < lowestCost) {
          lowestCost = winner.getEquipmentCost();
        }
      }

      if (winner.getName() === 'boss') {
        if (character.getEquipmentCost() > highestCost) {
          highestCost = character.getEquipmentCost();
        }
      }
    });

    let toReturn = {
      lowestCost: lowestCost,
      highestCost: highestCost
    };
    return toReturn;
  }
}


