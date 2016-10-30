var Reindeer = require('./reindeer.js');
var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));
var RACE_TIME = 2503;

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var awardPoints = function(reindeerRace) {
  var currentLeaders = [];
  reindeerRace.forEach(function(reindeer) {
    if(currentLeaders.length === 0) {
      currentLeaders.push(reindeer);
    } else if(reindeer.getDistance() > currentLeaders[0].getDistance()) {
      currentLeaders = [];
      currentLeaders.push(reindeer);
    } else if(reindeer.getDistance() === currentLeaders[0].getDistance()) {
      currentLeaders.push(reindeer);
    }
  });
  currentLeaders.forEach(function(reindeer) {
    reindeer.givePoint();
  })
}

// Main
var lines = getInputData("input.txt").then(function(lines) {
  var reindeerRace = [];
  for(var i = 0; i < lines.length; i++) {
    var tokenized = lines[i].split(' ');
    if(lines[i] != '') {
      reindeerRace.push(new Reindeer(tokenized[0], tokenized[3], tokenized[6], tokenized[13]));
    }
  }

  for(var i = 0; i < RACE_TIME; i++) {
    reindeerRace.forEach(function(reindeer) {
      reindeer.advanceSecond();
    });
    awardPoints(reindeerRace);
  }

  var farthestDistance = -1;
  var highestPoints = -1;
  reindeerRace.forEach(function(reindeer) {
    reindeer.display();
    if(reindeer.getDistance() > farthestDistance) {
      farthestDistance = reindeer.getDistance();
    }
    if(reindeer.getPoints() > highestPoints) {
      highestPoints = reindeer.getPoints();
    }
  });
  console.log("Farthest Distance: ", farthestDistance);
  console.log("Highest Points: ", highestPoints);
});
