const AttackWithWeaponAction = require('../../Actions/AttackWithWeaponAction');
const BattleLogger = require('../../BattleLogger');
const Character = require('../../Character/Character');

test('Perform Weapon Attack', () => {
  const attacker = new Character('a', 5, 10, 30);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  AttackWithWeaponAction.performAction(attacker, defender, logger);
  expect(defender.getHitPoints()).toEqual(22);
  expect(attacker.getHitPoints()).toEqual(30);
});

test('Attack when armor exceeds attack value', () => {
  const attacker = new Character('a', 5, 10, 30);
  const defender = new Character('d', 200, 4, 30);
  const logger = new BattleLogger();
  AttackWithWeaponAction.performAction(attacker, defender, logger);
  expect(defender.getHitPoints()).toEqual(29);
  expect(attacker.getHitPoints()).toEqual(30);
});
