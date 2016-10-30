var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var happinessTable = {};

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var removeTrailingPeriod = function(string) {
  if(string[string.length - 1] === '.') {
    return string.substring(0, string.length - 1);
  } else {
    return string;
  }
}

var computePermutations = function(itemsCollection) {
  var collectionsToReturn = [];
  if(itemsCollection.length === 1) {
    collectionsToReturn.push(itemsCollection);
  } else {
    itemsCollection.forEach(function(currentValue, currentIndex) {
      var modifiedCollection = JSON.parse(JSON.stringify(itemsCollection));
      modifiedCollection.splice(currentIndex, 1);
      var returnedCollections = computePermutations(modifiedCollection);
      returnedCollections.forEach(function(returnedCollection) {
        returnedCollection.unshift(currentValue);
        collectionsToReturn.push(returnedCollection);
      });
    });
  }
  return collectionsToReturn;
}

var addToHappinessTable = function(personKey, gainOrLoss, happinessDelta, personValue) {
  if(Object.keys(happinessTable).lastIndexOf(personKey) === -1) {
    happinessTable[personKey] = {};
  }

  var happiness;
  if(gainOrLoss === "lose") {
    happiness = happinessDelta * -1;
  } else {
    happiness = happinessDelta;
  }
  var personHappinessElement = {};
  happinessTable[personKey][removeTrailingPeriod(personValue)] = happiness;
}

var addMeToHappinessTable = function() {
  var initialHappinessTableLength = Object.keys(happinessTable).length;
  happinessTable["Me"] = {};
  for(var i = 0; i < initialHappinessTableLength; i++) {
    var currentPerson = (Object.keys(happinessTable))[i];
    happinessTable[currentPerson]["Me"] = "0";
    happinessTable["Me"][currentPerson] = "0";
  }
}

var getLocationCircular = function(location, arraySize) {
  if(location === -1) {
    return arraySize - 1;
  } else if(location === arraySize) {
    return 0;
  } else {
    return location;
  }
}

var computeTableHappines = function(tableOrder) {
  var runningTotal = 0;
  for(var i = 0; i < tableOrder.length; i++) {
     var leftNeighborLocation = getLocationCircular(i - 1, tableOrder.length);
     var rightNeighborLocation = getLocationCircular(i + 1, tableOrder.length);
     runningTotal += Number(happinessTable[tableOrder[i]][tableOrder[leftNeighborLocation]]);
     runningTotal += Number(happinessTable[tableOrder[i]][tableOrder[rightNeighborLocation]]);
  }
  return runningTotal;
}

// Main
var lines = getInputData("input.txt").then(function(lines) {
  for(var i = 0; i < lines.length; i++) {
    if(lines[i].length === 0) {
      continue;
    }
    var tokenizedString = lines[i].split(' ');
    addToHappinessTable(tokenizedString[0], tokenizedString[2], tokenizedString[3], tokenizedString[10]);
  }
  addMeToHappinessTable();
  console.log(happinessTable);

  var permutations = computePermutations(Object.keys(happinessTable));
  var highestHappinessValue = (Number.MAX_SAFE_INTEGER) * -1;
  for(var i = 0; i < permutations.length; i++) {
    var currentHappiess = computeTableHappines(permutations[i]);
    if(currentHappiess > highestHappinessValue) {
      highestHappinessValue = currentHappiess;
    }
  }
  console.log("Highest Happiness Value: ", highestHappinessValue);
});
