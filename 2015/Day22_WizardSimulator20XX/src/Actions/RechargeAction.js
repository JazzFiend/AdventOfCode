const Action = require('./Action');
const RechargeEffect = require('../Effects/RechargeEffect');

const RECHARGE_MANA_COST = 229;

module.exports = class RechargeAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    const rechargeEffect = new RechargeEffect();
    Action.assertEffectNotApplied(attacker, rechargeEffect.getName());
    attacker.spendMana(RECHARGE_MANA_COST);
    battleLogger.log(RechargeAction.constructActionMessage(attacker.getName()));
    attacker.addEffect(rechargeEffect);
  }

  static constructActionMessage(wizardName) {
    return `${wizardName} casts Recharge!`;
  }
};
