const Action = require('./Action');

module.exports = class AttackWithWeaponAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    const damage = AttackWithWeaponAction.calculateDamage(attacker.getDamage(), defender.getArmor());
    defender.dealDamage(damage);
    battleLogger.log(AttackWithWeaponAction.constructActionMessage(attacker.getName(), damage));
  }

  static calculateDamage(attackValue, defenseValue) {
    const damage = (attackValue - defenseValue);
    if (damage <= 0) {
      return 1;
    }
    return (damage);
  }

  static constructActionMessage(name, damage) {
    return `${name} attacks and deals ${damage} damage!`;
  }
};
