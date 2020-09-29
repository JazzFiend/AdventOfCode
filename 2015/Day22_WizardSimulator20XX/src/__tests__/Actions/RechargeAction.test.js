const Character = require('../../Character/Character');
const Wizard = require('../../Character/Wizard');
const RechargeAction = require('../../Actions/RechargeAction');
const BattleLogger = require('../../BattleLogger');

test('Perform Recharge Spell', () => {
  const attacker = new Wizard('a', 30, 229);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();

  RechargeAction.performAction(attacker, defender, logger);
  expect(attacker.getMana()).toEqual(0);
  expect(defender.getHitPoints()).toEqual(30);
  expect(attacker.hasEffect('Recharge')).toBeTruthy();
});

test('Recharge should not be applied twice', () => {
  const attacker = new Wizard('a', 30, 500);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  RechargeAction.performAction(attacker, defender, logger);
  expect(() => {
    RechargeAction.performAction(attacker, defender, logger);
  }).toThrow('Recharge already applied');
});
