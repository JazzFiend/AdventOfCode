const BattleLogger = require('../../BattleLogger');
const Wizard = require('../../Character/Wizard');
const ShieldEffect = require('../../Effects/ShieldEffect');

test('Shield Effect should increase armor by 7', () => {
  const w = new Wizard('w', 10, 10);
  const shield = new ShieldEffect();
  const logger = new BattleLogger();

  expect(shield.getName()).toEqual('Shield');
  shield.applyEffect(w, logger);
  expect(shield.getTimer()).toEqual(5);
});
