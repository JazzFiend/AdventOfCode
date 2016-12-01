var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));
var ROUTE_DISTANCE_MAP = new Map();
var CITIES = [];

var addCity = function(city) {
  if(CITIES.lastIndexOf(city) === -1) {
    CITIES.push(city);
  }
}

var generateCityPair = function(city1, city2) {
  if(city1 < city2) {
    return (city1 + city2);
  }
  return (city2 + city1);
}

var addRouteDistancePair = function(city1, city2, distance) {
  ROUTE_DISTANCE_MAP[generateCityPair(city1, city2)] = distance;
}

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var createGraph = function(citiesAndDistanceText) {
  citiesAndDistanceText.forEach(function(line) {
    if(line === "") {
      return;
    }
    var tokenizedString = line.toString().split(' ');
    var cityPair;
    addCity(tokenizedString[0]);
    addCity(tokenizedString[2]);
    addRouteDistancePair(tokenizedString[0], tokenizedString[2], tokenizedString[4]);
  });
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

var calculateTotalDistance = function(route) {
  var lastCity = route[0];
  var currentCity;
  var totalDistance = 0;
  for(var i = 1; i < route.length; i++) {
    currentCity = route[i];
    totalDistance += parseInt(ROUTE_DISTANCE_MAP[generateCityPair(lastCity, currentCity)]);
    lastCity = currentCity;
  }
  return totalDistance;
}

// Main
var lines = getInputData("input.txt").then(function(lines) {
  createGraph(lines);
  var permutations = computePermutations(CITIES);
  var smallestDistance = Number.MAX_SAFE_INTEGER;
  var largestDistance = 0;
  permutations.forEach(function(permutation) {
    var currentDistance = calculateTotalDistance(permutation);
    if(currentDistance < smallestDistance) {
      smallestDistance = currentDistance;
    }
    if(currentDistance > largestDistance) {
      largestDistance = currentDistance;
    }
  });
  console.log("Smallest Distance: ", smallestDistance);
  console.log("Largest Distance: ", largestDistance);
});
