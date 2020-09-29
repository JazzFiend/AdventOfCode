const Character = require('../../Character/Character');
const Wizard = require('../../Character/Wizard');
const PoisonAction = require('../../Actions/PoisonAction');
const BattleLogger = require('../../BattleLogger');

test('Perform Poison Spell', () => {
  const attacker = new Wizard('a', 30, 173);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  PoisonAction.performAction(attacker, defender, logger);
  expect(defender.getHitPoints()).toEqual(30);
  expect(defender.hasEffect('Poison')).toBeTruthy();
});

test('Poison should not be applied twice', () => {
  const attacker = new Wizard('a', 30, 500);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  PoisonAction.performAction(attacker, defender, logger);
  expect(() => {
    PoisonAction.performAction(attacker, defender, logger);
  }).toThrow('Poison already applied');
});
