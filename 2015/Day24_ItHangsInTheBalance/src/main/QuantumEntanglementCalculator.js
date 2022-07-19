const ArrayHelpers = require('./ArrayHelpers');

module.exports = class QuantumEntanglementCalculator {
  static calculateMinimum(packages, bucketCount) {
    const items = ArrayHelpers.sortArrayDescending(packages);
    const weightThreshold = ArrayHelpers.sumOfArray(packages) / bucketCount;
    const balanced = this.calculateMinimumBalancedGroups(items, weightThreshold);
    return QuantumEntanglementCalculator.calculateMinimumQE(balanced);
  }

  static calculateMinimumBalancedGroups(items, weightThreshold) {
    const balanced = [];
    let collectionSize = 1;
    do {
      const collections = this.calculatePermutations(items, 0, collectionSize, weightThreshold);
      collections.forEach((collection) => {
        if (ArrayHelpers.sumOfArray(collection) === weightThreshold) {
          balanced.push(collection);
        }
      });
      collectionSize += 1;
    } while (balanced.length === 0 && collectionSize < items.length);
    return balanced;
  }

  static calculatePermutations(items, currentIndex, depth, max) {
    if (depth === 0 || currentIndex >= items.length) { return [[]]; }

    const toReturn = [];
    for (let i = currentIndex; i < items.length; i += 1) {
      let futureArrays = QuantumEntanglementCalculator.calculatePermutations(items, i + 1, depth - 1, max);
      futureArrays.map((array) => array.push(items[i]));
      futureArrays = futureArrays.filter((array) => ArrayHelpers.sumOfArray(array) <= max);
      futureArrays.map((array) => toReturn.push(array));
    }
    return toReturn;
  }

  static calculateMinimumQE(balanced) {
    let minQuantumEntanglement = Number.MAX_VALUE;
    balanced.forEach((balance) => {
      if (this.computeQE(balance) < minQuantumEntanglement) {
        minQuantumEntanglement = this.computeQE(balance);
      }
    });
    if (minQuantumEntanglement === Number.MAX_VALUE) { return 0; }
    return minQuantumEntanglement;
  }

  static computeQE(array) {
    return ArrayHelpers.productsOfArray(array);
  }
};
