var Container = require('./container.js');
var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var pourEggnog = function(containerList, capacity) {
  // console.log("Pour Eggnog: ", containerList, capacity);
  var toReturn = [];
  containerList.forEach(function(container, index) {
    var containerListFullCapacity;
    var newCapacity;
    var newContainerList = containerList.slice();

    if(capacity < container.getCapacity()) {
      return;
    } else {
      newCapacity = capacity - container.getCapacity();
    }

    if(newCapacity > 0) {
      newContainerList.splice(0, index + 1);
      containerListFullCapacity = pourEggnog(newContainerList, newCapacity);
      if(containerListFullCapacity.length !== 0) {
        containerListFullCapacity.forEach(function(list) {
          list.push(container);
          toReturn.push(list);
        });
      }
    } else {
      var oneItemList = [];
      oneItemList.push(container);
      toReturn.push(oneItemList);
    }
  });

  // console.log("To Return: ", toReturn);
  return toReturn;
}

var numberOfMinimumCapacity = function(listOfLists) {
  var sizeToQuantityMap = {};

  listOfLists.forEach(function(list) {
    var currentSize = list.length.toString();
    if(currentSize in sizeToQuantityMap) {
      sizeToQuantityMap[currentSize] = sizeToQuantityMap[currentSize] + 1;
    } else {
      sizeToQuantityMap[currentSize] = 1;
    }
  });

  var smallestLength = Number.MAX_VALUE;
  Object.keys(sizeToQuantityMap).forEach(function(key) {
    if(sizeToQuantityMap[key] < smallestLength) {
      smallestLength = Number(key);
    }
  });
  return sizeToQuantityMap[smallestLength];
}

// Main
const MAX_LITERS = 150;
var lines = getInputData("input.txt").then(function(lines) {
  var containerList = [];
  var finalLists;
  lines.forEach(function(line) {
    if(line !== '') {
      containerList.push(new Container(Number(line)));
    }
  });
  containerList.sort(function(a, b) {
    return b.getCapacity() - a.getCapacity();
  });
  finalLists = pourEggnog(containerList, MAX_LITERS);
  console.log(finalLists);
  console.log("Number of Combinations: ", finalLists.length);

  console.log("Smallest of smallest collection: ", numberOfMinimumCapacity(finalLists))
});
