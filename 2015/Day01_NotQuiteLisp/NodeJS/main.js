var fs = require("fs");

var calculateFloor = function(floorString) {
  var currentFloor = 0;
  var basementTracker = true;
  for (var i = 0; i < floorString.length; ++i) {
    if(floorString[i] === '(') {
      ++currentFloor;
    } else if(floorString[i] === ')') {
      --currentFloor;
    } else if(Number(floorString[i]) === 0) {
      continue;
    } else {
      console.log('uh-oh.  you bwoke it.');
    }
    if(currentFloor === -1 && basementTracker) {
      console.log("Santa entered the basement at position ", i + 1);
      basementTracker = false;
    }
  }
  console.log("Santa is at", currentFloor, "at the end of his list.");
};

fs.readFile('input.txt', function (err, line) {
  if(err) {
    return console.error(err);
  }
  calculateFloor(line.toString());
});
