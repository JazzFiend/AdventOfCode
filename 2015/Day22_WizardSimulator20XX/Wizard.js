var Character = require("./Character.js");

function Wizard(name, hitPointMax, manaStart) {
  this.mana = manaStart;
  Character.call(this, name, 0, 0, hitPointMax);
}

Wizard.prototype = Object.create(Character.prototype);

Wizard.prototype.constructor = Wizard;

Wizard.prototype.castSpell = function(spellName) {
  var turnAction = {
    'damage': 0,
    'healing': 0,
    'effect': undefined,
    'manaCost': 0
  }

  switch(spellName) {
    case: 'magicMissle'
      turnAction['damage'] = 4;
      turnAction['manaCost'] = 53;
      break;

    case: 'drain'
      turnAction['damage'] = 2,
      turnAction['healing'] = 2,
      turnAction['manaCost'] = 73;
      break;

    case: 'poison'
      turnAction['effect'] = new Effect('enemy', 'damage', 2, 6),
      turnAction['manaCost'] = 173
      break;

    case: 'recharge'
      turnAction['effect'] = new Effect('self', 'mana', 101, 5),
      turnAction['manaCost'] = 229
      break;

    case: 'shield'
      turnAction['effect'] = new Effect('self', 'armor', 7, 6),
      turnAction['manaCost'] = 113
      break;

    default:
      throw new Error("Bad spell seen");
  }
  return turnAction;
}

Wizard.prototype.addMana(value) {
  this.mana += value;
}

module.exports = Wizard;
