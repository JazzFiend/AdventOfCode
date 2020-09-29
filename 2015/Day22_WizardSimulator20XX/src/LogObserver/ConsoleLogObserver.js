const LogObserver = require('./LogObserver');

module.exports = class ConsoleLogObserver extends LogObserver {
  static update(message) {
    console.log(message);
  }
};
