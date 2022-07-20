module.exports = class CopyProtectionCodeGenerator {
  static generateCode(x, y) {
    if (x === 0 || y === 0) { return undefined; }

    const codeLocation = CopyProtectionCodeGenerator.calculateLoopCount(x, y);
    return CopyProtectionCodeGenerator.calculateCode(codeLocation);
  }

  static calculateLoopCount(x, y) {
    let currentX = 1;
    let currentY = 1;
    let loopCount = 0;
    let maxY = 1;
    
    while (!(currentX === x && currentY === y)) {
      if (currentY === 1) {
        currentX = 1;
        maxY += 1;
        currentY = maxY;
      } else {
        currentX += 1;
        currentY -= 1;
      }
      loopCount += 1;
    }
    return loopCount;
  }

  static calculateCode(loopCount) {
    let currentTotal = 20151125;
    for (let i = 0; i < loopCount; i += 1) {
      currentTotal = (currentTotal * 252533) % 33554393;
    }
    return currentTotal;
  }
};
