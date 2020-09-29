const Action = require('./Action');
const PoisonEffect = require('../Effects/PoisonEffect');

const POISON_MANA_COST = 173;

module.exports = class PoisonAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    const poisonEffect = new PoisonEffect();
    Action.assertEffectNotApplied(defender, poisonEffect.getName());
    attacker.spendMana(POISON_MANA_COST);
    battleLogger.log(PoisonAction.constructActionMessage(attacker.getName()));
    defender.addEffect(poisonEffect);
  }

  static constructActionMessage(wizardName) {
    return `${wizardName} casts Poison!`;
  }
};
