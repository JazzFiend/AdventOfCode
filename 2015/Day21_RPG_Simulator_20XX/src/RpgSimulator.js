const Shop = require('./Shop.js');
const Player = require('./Player.js');
const Battle = require('./Battle.js');
const Character = require('./Character.js');

const DEFAULT_HIT_POINTS = 100;

module.exports = class RpgSimulator {
  constructor() {
    this.lowestCost = 1000000;
    this.highestCost = -1;
  }

  calculateBattleCombinations(armor, damage, hitPointMax) {
    let characterCollection = this._getAllPossiblePlayers();
    characterCollection.forEach((character) => {
      let boss = new Character('boss', armor, damage, hitPointMax);
      this._calculateBattleCost(boss, character);
    });
  }

  _getAllPossiblePlayers() {
    const shop = new Shop();
    let equipmentCombinations = shop.computeEquipmentCombinations()
    let allPossiblePlayers = this._computeCharacterEquipmentCombinations(equipmentCombinations);
    return allPossiblePlayers;
  }

  _computeCharacterEquipmentCombinations(equipmentCombinations) {
    let playerEquipmentCombos = [];
    let playerNumber = 0;
    for(let equipmentCombination of equipmentCombinations) {
      let currentPlayer = new Player("Player " + playerNumber++, Player.computeArmor(equipmentCombination), Player.computeDamage(equipmentCombination), DEFAULT_HIT_POINTS, equipmentCombination);
      playerEquipmentCombos.push(currentPlayer);
    }
    return playerEquipmentCombos;
  }

  _calculateBattleCost(boss, character) {
    let battle = new Battle(character, boss);
    // battle.setVerbose();
    let winner = battle.fight();
    this._updateLowestCost(winner);
    this._updateHighestCost(winner, character);
  }

  _updateHighestCost(winner, character) {
    if (winner.getName() === 'boss') {
      if (character.getEquipmentCost() > this.highestCost) {
        this.highestCost = character.getEquipmentCost();
      }
    }
  }

  _updateLowestCost(winner) {
    if (winner.getName() !== 'boss') {
      if (winner.getEquipmentCost() < this.lowestCost) {
        this.lowestCost = winner.getEquipmentCost();
      }
    }
  }

  getLowestCost() {
    return this.lowestCost;
  }

  getHighestCost() {
    return this.highestCost;
  }
}


