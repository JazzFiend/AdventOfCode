const ShieldEffect = require('../Effects/ShieldEffect');
const Action = require('./Action');

const SHIELD_MANA_COST = 113;

module.exports = class ShieldAction extends Action {
  static performAction(attacker, defender, battleLogger) {
    const shieldEffect = new ShieldEffect();
    Action.assertEffectNotApplied(attacker, shieldEffect.getName());
    attacker.spendMana(SHIELD_MANA_COST);
    battleLogger.log(this.constructActionMessage(attacker.getName()));
    attacker.addEffect(new ShieldEffect());
  }

  static constructActionMessage(wizardName) {
    return `${wizardName} casts Shield!`;
  }
};
