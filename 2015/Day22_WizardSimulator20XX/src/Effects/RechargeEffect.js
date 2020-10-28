const TemporaryEffect = require('./TemporaryEffect');

module.exports = class RechargeEffect extends TemporaryEffect {
  constructor() {
    super();
    this.name = 'Recharge';
    this.manaGain = 101;
    this.timer = 5;
  }

  applyEffect(wizard, battleLogger) {
    wizard.chargeMana(this.manaGain);
    battleLogger.log(RechargeEffect.constructEffectLog(wizard.getName(), this.manaGain));
    this.decrementTimer();
  }

  static constructEffectLog(name, mana) {
    return `${name} recharges ${mana} mana!`;
  }
};
