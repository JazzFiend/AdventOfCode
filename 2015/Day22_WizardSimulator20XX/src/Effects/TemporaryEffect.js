const Effect = require('./Effect');

module.exports = class TemporaryEffect extends Effect {
  decrementTimer() {
    this.timer -= 1;
  }
};
