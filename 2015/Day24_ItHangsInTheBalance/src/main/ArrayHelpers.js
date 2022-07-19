const _ = require('lodash');

module.exports = class ArrayHelpers {
  static sortArrayDescending(array) {
    const itemsSorted = _.cloneDeep(array);
    return itemsSorted.sort((a, b) => b - a);
  }

  static sumOfArray(array) {
    if (array.length === 0) { return 0; }
    return array.reduce((prev, next) => prev + next);
  }

  static productsOfArray(array) {
    if (array.length === 0) { return 0; }
    return array.reduce((prev, next) => prev * next);
  }
};
