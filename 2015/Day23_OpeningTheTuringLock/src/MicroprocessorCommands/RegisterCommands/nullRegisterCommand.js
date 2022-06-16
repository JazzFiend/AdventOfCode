/* eslint-disable class-methods-use-this */
const RegisterCommand = require('./registerCommand');

module.exports = class NullRegisterCommand extends RegisterCommand {
  execute() {
    // This is a Null Object and should do nothing.
  }
};
