const _ = require('lodash');

const Battle = require('./Battle');
const MagicMissileAction = require('./Actions/MagicMissileAction');
const DrainAction = require('./Actions/DrainAction');
const PoisonAction = require('./Actions/PoisonAction');
const ShieldAction = require('./Actions/ShieldAction');
const RechargeAction = require('./Actions/RechargeAction');
const BattleLogger = require('./BattleLogger');
const ConsoleLogObserver = require('./LogObserver/ConsoleLogObserver');

module.exports = class BattleManaAnalyzer {
  static calculateWinWithMinimumMana(playerWizard, boss, isHardMode) {
    const battleLogger = new BattleLogger();
    const initialState = BattleManaAnalyzer.initializeBattle(playerWizard, boss, isHardMode);
    let minMana = Number.MAX_VALUE;
    let battlesWithAllNewActions = this.assignEachActionToBattle(initialState);
    // BattleManaAnalyzer.setVerbose(battleLogger);

    while (battlesWithAllNewActions.length > 0) {
      // There are definitely more efficient ways to do this search, but this does work.
      const currentBattle = battlesWithAllNewActions[0].battle;
      const { nextAction } = battlesWithAllNewActions[0];
      battlesWithAllNewActions = battlesWithAllNewActions.slice(1);
      try {
        currentBattle.initiateRoundOfCombat(nextAction);
        if (!currentBattle.isBattleInProgress()) {
          if (BattleManaAnalyzer.foundLowerManaValue(currentBattle, playerWizard, minMana)) {
            battleLogger.log(currentBattle.getHistory());
            minMana = currentBattle.manaSpentByPlayer();
          }
        } else if (BattleManaAnalyzer.currentManaWithinLimit(currentBattle, minMana)) {
          battlesWithAllNewActions = battlesWithAllNewActions.concat(this.assignEachActionToBattle(currentBattle));
        }
      } catch (error) {
        battleLogger.log(error.message);
      }
    }
    return minMana;
  }

  static setVerbose(battleLogger) {
    battleLogger.addLogObserver(ConsoleLogObserver);
  }

  static currentManaWithinLimit(battle, minMana) {
    return battle.manaSpentByPlayer() < minMana;
  }

  static foundLowerManaValue(battle, playerWizard, minMana) {
    return battle.getWinner().getName() === playerWizard.getName()
        && battle.manaSpentByPlayer() < minMana;
  }

  static initializeBattle(playerWizard, boss, isHardMode) {
    const initialState = new Battle(playerWizard, boss, isHardMode);
    initialState.beginBattle();
    return initialState;
  }

  static assignEachActionToBattle(battle) {
    const battles = [];
    battles.push({ battle: (_.cloneDeep(battle)), nextAction: MagicMissileAction });
    battles.push({ battle: (_.cloneDeep(battle)), nextAction: DrainAction });
    battles.push({ battle: (_.cloneDeep(battle)), nextAction: PoisonAction });
    battles.push({ battle: (_.cloneDeep(battle)), nextAction: ShieldAction });
    battles.push({ battle: (_.cloneDeep(battle)), nextAction: RechargeAction });
    return battles;
  }
};
