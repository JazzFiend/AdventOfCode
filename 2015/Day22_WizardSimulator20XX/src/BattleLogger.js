module.exports = class BattleLogger {
  constructor() {
    this.logObservers = [];
  }

  addLogObserver(logObserverClass) {
    if (!this.logObservers.includes(logObserverClass)) {
      this.logObservers.push(logObserverClass);
    }
  }

  log(message) {
    if (this.logObservers !== undefined) {
      this.logObservers.forEach((logObserver) => logObserver.update(message));
    }
  }
};
