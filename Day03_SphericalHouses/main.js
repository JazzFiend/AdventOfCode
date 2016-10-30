var _ = require('lodash');
var fs = require("fs");

var NUMBER_OF_SANTAS = 2;

var isLocationEqual = function(location1, location2) {
  if(location1.x === location2.x &&
     location1.y === location2.y) {
       return true;
     }
  return false;
};

var updateLocation = function(location, char) {
  var thisX;
  var thisY;

  if (char === '>') {
    thisX = location.x + 1;
    thisY = location.y;
  } else if (char === '<') {
    thisX = location.x - 1;
    thisY = location.y;
  } else if (char === '^') {
    thisX = location.x;
    thisY = location.y + 1;
  } else if (char === 'v') {
    thisX = location.x;
    thisY = location.y - 1;
  } else {
    return undefined;
  }
  var newLocation = {
    "x": thisX,
    "y": thisY
  };

  return newLocation;
}

var origin = {
  "x" : 0,
  "y" : 0,
}
var locationsVisited = new Array(origin);
var santaLocations = new Array();
var lastX = 0;
var lastY = 0;

fs.readFile('input.txt', function (err, input) {
  if(err) {
    return console.error(err);
  }
  var inputText = input.toString();

  //Initialize
  for(var i = 0; i < NUMBER_OF_SANTAS; i++) {
    santaLocations[i] = origin;
  }

  for(var i = 0; i < inputText.length; i = i + 1) {
    var location = santaLocations[i % NUMBER_OF_SANTAS];
    location = updateLocation(location, inputText[i]);
    if(location === undefined) {
      continue;
    }

    var containsDuplicate = false;
    _.forEach(locationsVisited, function(iteratedLocation) {
      if(isLocationEqual(iteratedLocation, location)) {
        containsDuplicate = true;
      }
    });

    if(!containsDuplicate) {
      locationsVisited[locationsVisited.length] = location;
    }
    santaLocations[i % NUMBER_OF_SANTAS] = location;
  }

  console.log("Total Locations Visited: ", locationsVisited.length);
});
