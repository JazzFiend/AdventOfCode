module.exports = class LogObserver {
  static update() {
    throw new Error('LogObserver.update is an abstract class');
  }
};
