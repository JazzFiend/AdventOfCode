const Character = require('../Character/Character');
const BattleManaAnalyzer = require('../BattleManaAnalyzer');
const Wizard = require('../Character/Wizard');

test('Player Unable to cast any spells', () => {
  const c = new Character('c', 1, 1, 1);
  const w = new Wizard('w', 1, 1);
  expect(BattleManaAnalyzer.calculateWinWithMinimumMana(w, c, false)).toEqual(Number.MAX_VALUE);
});

test('Min Mana Example #1', () => {
  const c = new Character('Boss', 20, 8, 13);
  const w = new Wizard('Player', 10, 250);
  expect(BattleManaAnalyzer.calculateWinWithMinimumMana(w, c, false)).toEqual(226);
});

test('Min Mana Example #2', () => {
  const c = new Character('Boss', 20, 8, 14);
  const w = new Wizard('Player', 10, 250);
  expect(BattleManaAnalyzer.calculateWinWithMinimumMana(w, c, false)).toEqual(641);
});

test.skip('Puzzle #1', () => {
  const c = new Character('Boss', 55, 8, 55);
  const w = new Wizard('Player', 50, 500);
  expect(BattleManaAnalyzer.calculateWinWithMinimumMana(w, c, false)).toEqual(953);
});

test.skip('Puzzle #2', () => {
  const c = new Character('Boss', 55, 8, 55);
  const w = new Wizard('Player', 50, 500);
  expect(BattleManaAnalyzer.calculateWinWithMinimumMana(w, c, true)).toEqual(1289);
});
