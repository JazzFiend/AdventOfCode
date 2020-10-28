const Character = require('../../Character/Character');
const Wizard = require('../../Character/Wizard');
const MagicMissileAction = require('../../Actions/MagicMissileAction');
const BattleLogger = require('../../BattleLogger');

test('Perform Magic Missile Attack', () => {
  const attacker = new Wizard('a', 30, 53);
  const defender = new Character('d', 2, 4, 30);
  const logger = new BattleLogger();
  MagicMissileAction.performAction(attacker, defender, logger);
  expect(defender.getHitPoints()).toEqual(26);
  expect(attacker.getHitPoints()).toEqual(30);
  expect(attacker.getMana()).toEqual(0);
});
