const Item = require('./Item');
const Player = require('./Player');
const ListCombinationCalculator = require('../src/ListCombinationCalculator');

const NO_RING = new Item('ring', 'none', 0, 0, 0);

module.exports = class Shop {
  constructor() {
    this._constructWeaponList();
    this._constructArmorList();
    this._constructRingList();
  }

  _constructWeaponList() {
    this.weaponList = [
      new Item('weapon', 'Dagger', 8, 4, 0),
      new Item('weapon', 'Shortsword', 10, 5, 0),
      new Item('weapon', 'Warhammer', 25, 6, 0),
      new Item('weapon', 'Longsword', 40, 7, 0),
      new Item('weapon', 'Greataxe', 74, 8, 0)
    ];
    if (Player.getEquipmentSlots().minWeapons === 0) {
      this.weaponList.push(new Item('weapon', 'none', 0, 0, 0));
    }
  }

  _constructArmorList() {
    this.armorList = [
      new Item('armor', 'Leather', 13, 0, 1),
      new Item('armor', 'Chainmail', 31, 0, 2),
      new Item('armor', 'Splintmail', 53, 0, 3),
      new Item('armor', 'Bandedmail', 75, 0, 4),
      new Item('armor', 'Platemail', 102, 0, 5)
    ];
    if (Player.getEquipmentSlots().minArmor === 0) {
      this.armorList.push(new Item('armor', 'none', 0, 0, 0));
    }
  }

  _constructRingList() {
    this.ringList = [
      new Item('ring', 'Damage +1', 25, 1, 0),
      new Item('ring', 'Damage +2', 50, 2, 0),
      new Item('ring', 'Damage +3', 100, 3, 0),
      new Item('ring', 'Defense +1', 20, 0, 1),
      new Item('ring', 'Defense +2', 40, 0, 2),
      new Item('ring', 'Defense +3', 80, 0, 3)
    ];
    if (Player.getEquipmentSlots().minRings === 0) {
      this.ringList.push(NO_RING);
    }
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
    let equipmentCombinations = [];
    let weaponGroupings = ListCombinationCalculator.computeCombinations(this.weaponList, Player.getEquipmentSlots().maxWeapons);
    let armorGroupings = ListCombinationCalculator.computeCombinations(this.armorList, Player.getEquipmentSlots().maxArmor);
    let ringGroupings = ListCombinationCalculator.computeCombinations(this.ringList, Player.getEquipmentSlots().maxRings, [NO_RING]);

    for(let weaponGroup of weaponGroupings) {
      for(let armorGroup of armorGroupings) {
        for(let ringGroup of ringGroupings) {
          let itemCollection = [];
          for(let weapon of weaponGroup) {
            itemCollection.push(weapon);
          }
          for(let armor of armorGroup) {
            itemCollection.push(armor);
          }
          for(let ring of ringGroup) {
            itemCollection.push(ring);
          }
          equipmentCombinations.push(itemCollection);
        }
      }
    }
    return equipmentCombinations;
  }
}
