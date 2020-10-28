const BattleLogger = require('../../BattleLogger');
const Character = require('../../Character/Character');
const PoisonEffect = require('../../Effects/PoisonEffect');

test('Poison Effect should remove three hitpoints per turn', () => {
  const c = new Character('c', 4, 2, 10);
  const poison = new PoisonEffect();
  const logger = new BattleLogger();

  expect(poison.getName()).toEqual('Poison');
  poison.applyEffect(c, logger);
  expect(c.getHitPoints()).toEqual(7);
  expect(poison.getTimer()).toEqual(5);
});
