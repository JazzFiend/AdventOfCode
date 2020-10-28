const Character = require('../../Character/Character');
const Wizard = require('../../Character/Wizard');
const DrainAction = require('../../Actions/DrainAction');
const BattleLogger = require('../../BattleLogger');

test('Perform Drain Attack', () => {
  const attacker = new Wizard('a', 30, 73);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  attacker.dealDamage(2);
  expect(attacker.getHitPoints()).toEqual(28);
  DrainAction.performAction(attacker, defender, logger);
  expect(defender.getHitPoints()).toEqual(28);
  expect(attacker.getHitPoints()).toEqual(30);
  expect(attacker.getMana()).toEqual(0);
});
