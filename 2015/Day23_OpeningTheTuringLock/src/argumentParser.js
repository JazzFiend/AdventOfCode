module.exports = class ArgumentParser {
  static parse(argumentList) {
    const parsedArguments = {};

    if (argumentList.length >= 2) {
      [parsedArguments.registerName] = argumentList;
      parsedArguments.jumpOffset = this.extractJumpOffset(argumentList[1]);
    } else {
      parsedArguments.jumpOffset = ArgumentParser.extractJumpOffset(argumentList[0]);
      if (parsedArguments.jumpOffset === undefined) {
        [parsedArguments.registerName] = argumentList;
      }
    }

    return parsedArguments;
  }

  static extractJumpOffset(str) {
    let jumpOffsetResult;
    if (ArgumentParser.isStringANumber(str)) {
      jumpOffsetResult = Number.parseInt(str, 10);
    }
    return jumpOffsetResult;
  }

  static isStringANumber(str) {
    return Number.isFinite(Number.parseInt(str, 10));
  }
};
