var Shop = require('./Shop.js');
var Item = require('./Item.js');
var Player = require('./Player.js');
var Battle = require('./Battle.js');
var Character = require('./Character.js');

var DEFAULT_HIT_POINTS = 100;

module.exports = class RpgSimulator {
  calculateBattleCombinations(armor, damage, hitPointMax) {
    var battle;
    var lowestCost = 1000000;
    var highestCost = -1;
    var characterCollection = this._getAllPossiblePlayers();

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

  _getAllPossiblePlayers() {
    const shop = new Shop();
    let equipmentCombinations = shop.computeEquipmentCombinations()
    let allPossiblePlayers = this._computeCharacterEquipmentCombinations(equipmentCombinations);
    return allPossiblePlayers;
  }

  _computeCharacterEquipmentCombinations(equipmentCombinations) {
    let playerEquipmentCombos = [];
    let playerNumber = 0;
    for(let equipmentCombination of equipmentCombinations) {
      let currentPlayer = new Player("Player " + playerNumber, Player.computeArmor(equipmentCombination), Player.computeDamage(equipmentCombination), DEFAULT_HIT_POINTS, equipmentCombination);
      playerEquipmentCombos.push(new Player("Player " + playerNumber, Player.computeArmor(equipmentCombination), Player.computeDamage(equipmentCombination), DEFAULT_HIT_POINTS, equipmentCombination));
      playerNumber++;
    }
    return playerEquipmentCombos;
  }

  _checkDuplicateExceptions(item) {
    if (item.getName() === 'none') {
      return true;
    } else {
      return false;
    }
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

  _computeEquipmentCombinations(weaponList, armorList, ringList) {
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

    weaponGroupings = this._calculateGroupings(updatedWeaponList, MAX_WEAPONS);
    armorGroupings = this._calculateGroupings(updatedArmorList, MAX_ARMOR);
    ringGroupings = this._calculateGroupings(updatedRingList, MAX_RINGS);

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
}


