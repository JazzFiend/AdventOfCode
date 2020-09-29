const Wizard = require('../../Character/Wizard');

test('Check out of mana', () => {
  const w = new Wizard('a', 30, 52);

  expect(() => {
    w.spendMana(53);
  }).toThrow('Out of Mana');
});
