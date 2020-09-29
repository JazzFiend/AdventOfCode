const Action = require('./Action');

const DRAIN_MANA_COST = 73;
const DRAIN_DAMAGE = 2;
const DRAIN_HEAL = 2;

module.exports = class DrainAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    attacker.spendMana(DRAIN_MANA_COST);
    battleLogger.log(DrainAction.constructActionMessage(attacker.getName()));
    defender.dealDamage(DRAIN_DAMAGE);
    attacker.healDamage(DRAIN_HEAL);
  }

  static constructActionMessage(wizardName) {
    return `${wizardName} casts Drain! They deal ${DRAIN_DAMAGE} damage and heal ${DRAIN_HEAL} HP!`;
  }
};
