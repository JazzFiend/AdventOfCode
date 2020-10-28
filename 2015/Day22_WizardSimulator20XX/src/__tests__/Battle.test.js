const Character = require('../Character/Character');
const Battle = require('../Battle');
const Wizard = require('../Character/Wizard');
const PoisonAction = require('../Actions/PoisonAction');
const MagicMissileAction = require('../Actions/MagicMissileAction');
const RechargeAction = require('../Actions/RechargeAction');
const ShieldAction = require('../Actions/ShieldAction');
const DrainAction = require('../Actions/DrainAction');

test('Battle between boss and wizard where only magic missile is cast', () => {
  const c = new Character('c', 20, 5, 75);
  const w = new Wizard('w', 40, 500);
  const b = new Battle(w, c, false);

  // b.setVerboseConsole();
  b.beginBattle();
  while (b.isBattleInProgress()) {
    b.initiateRoundOfCombat(MagicMissileAction);
  }
  expect(b.getWinner().getName()).toEqual('c');
});

test('Attempt to start battle that has already started', () => {
  const c = new Character('c', 20, 5, 75);
  const w = new Wizard('w', 40, 500);
  const b = new Battle(w, c, false);

  b.beginBattle();
  expect(() => {
    b.beginBattle();
  }).toThrow('Battle already started');
});

test('Attempt to get a winner from a battle that isn\'t over', () => {
  const c = new Character('c', 20, 5, 75);
  const w = new Wizard('w', 40, 500);
  const b = new Battle(w, c, false);

  b.beginBattle();
  expect(() => {
    b.getWinner();
  }).toThrow('Battle still in progress');
});

test('Attempt to get a winner from a battle that hasn\'t started', () => {
  const c = new Character('c', 20, 5, 75);
  const w = new Wizard('w', 40, 500);
  const b = new Battle(w, c, false);

  expect(() => {
    b.getWinner();
  }).toThrow('No Battle has taken place');
});

test('Battle Example #1', () => {
  const c = new Character('Boss', 20, 8, 13);
  const w = new Wizard('Player', 10, 250);
  const b = new Battle(w, c, false);

  // b.setVerboseConsole();
  b.beginBattle();
  b.initiateRoundOfCombat(PoisonAction);
  while (b.isBattleInProgress()) {
    b.initiateRoundOfCombat(MagicMissileAction);
  }
  expect(b.getWinner().getName()).toEqual('Player');
  expect(b.manaSpentByPlayer()).toEqual(226);
});

test('Battle Example #2', () => {
  const c = new Character('Boss', 20, 8, 14);
  const w = new Wizard('Player', 10, 250);
  const b = new Battle(w, c, false);

  // b.setVerboseConsole();
  b.beginBattle();
  b.initiateRoundOfCombat(RechargeAction);
  b.initiateRoundOfCombat(ShieldAction);
  b.initiateRoundOfCombat(DrainAction);
  b.initiateRoundOfCombat(PoisonAction);
  b.initiateRoundOfCombat(MagicMissileAction);
  expect(b.getWinner().getName()).toEqual('Player');
  expect(b.manaSpentByPlayer()).toEqual(641);
});

test('Battle Example #2 with Hard Mode', () => {
  const c = new Character('Boss', 20, 8, 14);
  const w = new Wizard('Player', 20, 250);
  const b = new Battle(w, c, true);

  // b.setVerboseConsole();
  b.beginBattle();
  b.initiateRoundOfCombat(RechargeAction);
  b.initiateRoundOfCombat(ShieldAction);
  b.initiateRoundOfCombat(DrainAction);
  b.initiateRoundOfCombat(PoisonAction);
  b.initiateRoundOfCombat(MagicMissileAction);
  expect(b.getWinner().getName()).toEqual('Player');
  expect(b.manaSpentByPlayer()).toEqual(641);
});
