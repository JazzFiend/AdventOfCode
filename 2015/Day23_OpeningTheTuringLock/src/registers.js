module.exports = class Registers {
  constructor() {
    this.registerMap = {
      a: 0,
      b: 0,
    };
  }

  getRegister(register) {
    this.validateRegister(register);
    return this.registerMap[register];
  }

  setRegister(register, value) {
    this.validateRegister(register);
    this.registerMap[register] = value;
  }

  validateRegister(register) {
    if (!Object.prototype.hasOwnProperty.call(this.registerMap, register)) {
      throw new Error('Incorrect register given');
    }
  }
};
