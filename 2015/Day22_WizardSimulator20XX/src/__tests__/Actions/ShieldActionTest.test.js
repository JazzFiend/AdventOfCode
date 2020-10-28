const Character = require('../../Character/Character');
const Wizard = require('../../Character/Wizard');
const ShieldAction = require('../../Actions/ShieldAction');
const AttackWithWeaponAction = require('../../Actions/AttackWithWeaponAction');
const BattleLogger = require('../../BattleLogger');

test('Perform Poison Spell', () => {
  const attacker = new Wizard('a', 30, 113);
  const defender = new Character('d', 2, 7, 30);
  const logger = new BattleLogger();
  expect(attacker.getArmor()).toEqual(0);
  ShieldAction.performAction(attacker, defender, logger);

  expect(attacker.getArmor()).toEqual(7);
  AttackWithWeaponAction.performAction(defender, attacker, logger);

  expect(attacker.getHitPoints()).toEqual(29);
  expect(attacker.getEffects()[0].getName()).toEqual('Shield');
});

test('Shield should not be applied twice', () => {
  const attacker = new Wizard('a', 30, 500);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  ShieldAction.performAction(attacker, defender, logger);
  expect(() => {
    ShieldAction.performAction(attacker, defender, logger);
  }).toThrow('Shield already applied');
});
