const Character = require("./Character.js");

const EQUIPMENT_SLOTS = {
  minWeapons: 1,
  maxWeapons: 1,
  minArmor: 0,
  maxArmor: 1,
  minRings: 0,
  maxRings: 2
};

module.exports = class Player extends Character {
  constructor(name, armor, damage, hitPointMax, equipmentList) {
    super(name, armor, damage, hitPointMax)
    this.equipmentList = equipmentList;
  }

  static computeArmor(equipmentList) {
    let total = 0;
    equipmentList.forEach(function (item) {
      total += item.getDefense();
    });
    return total;
  }

  static computeDamage(equipmentList) {
    let total = 0;
    equipmentList.forEach(function (item) {
      total += item.getAttack();
    });
    return total;
  }

  getEquipmentCost() {
    let cost = 0;
    this.equipmentList.forEach(function (item) {
      cost += item.getCost();
    });
    return cost;
  }

  static getEquipmentSlots() {
    return EQUIPMENT_SLOTS;
  }
}
