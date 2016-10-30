var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var computeSums = function(sum, numberOfElements) {
  if(numberOfElements === 1) {
    return [[sum]];
  }
  var toReturn = [];

  for(var currentNumber = 0; currentNumber <= sum; currentNumber++) {
    var nextSum = sum - currentNumber;
    var returnedArrays = computeSums(nextSum, numberOfElements - 1);
    returnedArrays.forEach(function(returnedArray) {
      returnedArray.push(currentNumber);
      toReturn.push(returnedArray);
    });
  }
  return toReturn;
}

var removeLastCharacter = function(string) {
  return string.substr(0, string.length - 1);
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

var calculateScore = function(ingredientList) {
  var totalsArray = [];
  ingredientList.forEach(function(ingredient, index) {
    totalsArray.push([]);
    Object.keys(ingredient).forEach(function(property) {
      if(property !== 'Quantity' && property !== 'Calories' && property !== 'Name') {
        totalsArray[index].push(ingredient[property] * ingredient.Quantity);
      }
    });
  });
  var finalTotalList = [];
  for(var i = 0; i < totalsArray[0].length; i++) {
    var propertyTotal = 0;
    totalsArray.forEach(function(totalList) {
      propertyTotal += totalList[i];
    });
    if(propertyTotal < 0) {
      finalTotalList.push(0);
    } else {
      finalTotalList.push(propertyTotal);
    }
  }
  var finalTotal = 1;
  for(var i = 0; i < finalTotalList.length; i++) {
    finalTotal *= finalTotalList[i];
  }
  return finalTotal;
}

// Main
var lines = getInputData("input.txt").then(function(lines) {
  var ingredientList = [];
  lines.forEach(function(line) {
    if(line !== '') {
      var tokenized = line.split(' ');
      var classToAdd = {};

      ingredientList.push({
        'Name': removeLastCharacter(tokenized[0]),
        'Capacity': Number(removeLastCharacter(tokenized[2])),
        'Durability': Number(removeLastCharacter(tokenized[4])),
        'Flavor': Number(removeLastCharacter(tokenized[6])),
        'Texture': Number(removeLastCharacter(tokenized[8])),
        'Calories': Number(tokenized[10]),
        'Quantity': -1
      });
    }
  });

  var quantityCombinations = computeSums(100, ingredientList.length);
  var ingredientCombinations = computePermutations(ingredientList);
  var largestScore = Number.MAX_SAFE_INTEGER * -1;

  quantityCombinations.forEach(function(quantityList) {
    ingredientCombinations.forEach(function(ingredientCombination) {
      var calorieCount = 0;
      for(var i = 0; i < quantityList.length; i++) {
        ingredientCombination[i].Quantity = quantityList[i];
        calorieCount += (ingredientCombination[i].Calories * ingredientCombination[i].Quantity);
      }
      if(calorieCount === 500) {
        var currentScore = calculateScore(ingredientCombination);
        if(currentScore > largestScore) {
          largestScore = currentScore;
        }
      }
    });
  });
  console.log('Largest Score: ', largestScore);
});
