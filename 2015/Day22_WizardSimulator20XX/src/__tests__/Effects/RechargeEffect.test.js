const BattleLogger = require('../../BattleLogger');
const Wizard = require('../../Character/Wizard');
const RechargeEffect = require('../../Effects/RechargeEffect');

test('Recharge Effect should remove three hitpoints per turn', () => {
  const w = new Wizard('c', 2, 200);
  const recharge = new RechargeEffect();
  const logger = new BattleLogger();

  expect(recharge.getName()).toEqual('Recharge');
  w.spendMana(200);
  recharge.applyEffect(w, logger);
  expect(w.getMana()).toEqual(101);
  expect(recharge.getTimer()).toEqual(4);
});
