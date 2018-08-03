var Character = require("./Character.js");

function Player(name, hitPointMax, equipmentList) {
  this.equipmentList = equipmentList;
  Character.call(this, name, this._computeArmor(), this._computeDamage(), hitPointMax)
}

Player.prototype = Object.create(Character.prototype);

Player.prototype.constructor = Player;

Player.prototype._computeArmor = function() {
  var total = 0;
  this.equipmentList.forEach(function(item) {
    total += item.getDefense();
  });
  return total;
};

Player.prototype._computeDamage = function() {
  var total = 0;
  this.equipmentList.forEach(function(item) {
    total += item.getAttack();
  });
  return total;
};

Player.prototype.getEquipmentCost = function() {
  var cost = 0;
  this.equipmentList.forEach(function(item) {
    cost += item.getCost();
  });
  return cost;
};

module.exports = Player;
