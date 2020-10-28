const Action = require('./Action');

const MAGIC_MISSILE_MANA_COST = 53;
const MAGIC_MISSILE_DAMAGE = 4;

module.exports = class MagicMissileAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    attacker.spendMana(MAGIC_MISSILE_MANA_COST);
    battleLogger.log(MagicMissileAction.constructActionMessage(attacker.getName()));
    defender.dealDamage(MAGIC_MISSILE_DAMAGE);
  }

  static constructActionMessage(wizardName) {
    return `${wizardName} casts Magic Missile and deals ${MAGIC_MISSILE_DAMAGE} damage!`;
  }
};
