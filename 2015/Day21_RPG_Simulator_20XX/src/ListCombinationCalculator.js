module.exports = class ListCombinationCalculator {
  static computeCombinations(list, groupSize, allowedDoubles) {
    this.allowedDoubles = allowedDoubles;
    list = list;
    if (groupSize === 1) {
      return this._convertElementsToLists(list);
    } else {
      let combinations = [];
      let listCopy = list.slice();
      for (let i = 0; i < list.length; i++) {
        combinations.push.apply(combinations, this._doRecursiveCase(listCopy, groupSize, list[i]));
        listCopy.splice(0, 1);
      }
      return combinations;
    }
  }

  static _doRecursiveCase(list, groupSize, originalElement) {
    let elementsToConsider = this._calculateRecursiveElements(list);
    let subsequentCombinations = this.computeCombinations(elementsToConsider, groupSize - 1, this.allowedDoubles);
    let successfulCombinations = (this._applyElementToEachList(subsequentCombinations, originalElement));
    return successfulCombinations;
  }

  static _calculateRecursiveElements(list) {
    let listToReturn = list.slice();
    if(this.allowedDoubles && this.allowedDoubles.includes(listToReturn[0])) {
      return listToReturn;
    } else {
      listToReturn.splice(0, 1);
      return listToReturn;
    }
  }

  static _convertElementsToLists(list) {
    let listOfLists = [];
    for (let element of list) {
      let elementAsList = [];
      elementAsList.push(element);
      listOfLists.push(elementAsList);
    }
    return listOfLists;
  }

  static _applyElementToEachList(lists, element) {
    let combinedLists = []
    for (let list of lists) {
      list.push(element);
      combinedLists.push(list);
    }
    return combinedLists;
  }
}