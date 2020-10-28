module.exports = class Action {
  static performAction() { throw new Error('Abstract method'); }

  static assertEffectNotApplied(character, effectName) {
    if (character.hasEffect(effectName)) {
      throw new Error(`${effectName} already applied`);
    }
  }
};
