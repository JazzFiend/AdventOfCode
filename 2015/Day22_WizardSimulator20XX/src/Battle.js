const AttackWithWeaponAction = require('./Actions/AttackWithWeaponAction');
const ConsoleLogObserver = require('./LogObserver/ConsoleLogObserver');
const BattleLogger = require('./BattleLogger');
const HardModeEffect = require('./Effects/HardModeEffect');

module.exports = class Battle {
  constructor(player, boss, hardModeOn) {
    this.battleBegun = false;
    this.winner = undefined;
    this.player = player;
    this.boss = boss;
    this.battleLogger = new BattleLogger();
    this.history = [];
    if (hardModeOn) {
      const hardMode = new HardModeEffect();
      player.addEffect(hardMode);
    }
  }

  beginBattle() {
    if (!this.battleBegun) {
      this.battleBegun = true;
    } else {
      throw new Error('Battle already started');
    }
  }

  isBattleInProgress() {
    return this.battleBegun;
  }

  getHistory() {
    return this.history;
  }

  logHistory() {
    this.battleLogger.log(this.history);
  }

  initiateRoundOfCombat(userAction) {
    this.history.push(userAction);
    this.runTurn(this.player, this.boss, userAction);
    this.runTurn(this.boss, this.player, AttackWithWeaponAction);
  }

  runTurn(attacker, defender, userAction) {
    if (this.isBattleInProgress()) {
      this.logStatus(attacker, defender);
      this.handleEffects(attacker);
      this.checkDefeat();
      if (this.isBattleInProgress()) {
        userAction.performAction(attacker, defender, this.battleLogger);
        this.checkDefeat();
      }
    }
  }

  logStatus(currentCharacter, otherCharacter) {
    let statusMessage = `${Battle.constructCurrentTurnMessage(currentCharacter.getName())}\n`;
    statusMessage += `${currentCharacter.constructStatusMessage()}\n`;
    statusMessage += otherCharacter.constructStatusMessage();
    this.battleLogger.log(statusMessage);
  }

  handleEffects(currentTurnCharacter) {
    const isPlayersTurn = (currentTurnCharacter.getName() === this.player.getName());
    this.runCharacterEffects(this.player, isPlayersTurn);
    this.runCharacterEffects(this.boss, !isPlayersTurn);
  }

  runCharacterEffects(character, isCharactersTurn) {
    const effects = character.getEffects();
    effects.forEach((effect) => {
      effect.applyEffect(character, this.battleLogger, isCharactersTurn);
      if (effect.getTimer() <= 0) {
        character.removeEffect(effect.getName());
      }
    });
  }

  checkDefeat() {
    if (this.player.isCharacterDefeated()) {
      this.endBattle(this.boss);
    } else if (this.boss.isCharacterDefeated()) {
      this.endBattle(this.player);
    }
  }

  endBattle(winner) {
    this.battleBegun = false;
    this.winner = winner;
    this.battleLogger.log(this.constructWinnerMessage());
    this.history.push(this.constructWinnerMessage());
  }

  getWinner() {
    if (this.battleBegun) {
      throw new Error('Battle still in progress');
    } else if (!this.winner) {
      throw new Error('No Battle has taken place');
    }
    return this.winner;
  }

  manaSpentByPlayer() {
    return this.player.getSpentMana();
  }

  static constructCurrentTurnMessage(currentCharacterName) {
    return `--- ${currentCharacterName} turn ---`;
  }

  constructWinnerMessage() {
    return `${this.winner.getName()} has won the battle!`;
  }

  setVerboseConsole() {
    this.battleLogger.addLogObserver(ConsoleLogObserver);
  }
};
