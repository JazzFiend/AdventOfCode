var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var isCorner = function(i, j, length) {
  if((i === 0 && j === 0) ||
     (i === 0 && j === length - 1) ||
     (i === length - 1 && j === 0) ||
     (i === length - 1 && j === length - 1)) {
    return true;
  } else {
    return false;
  }
}

// Initialize an n x n array to all zeroes
var createSquareGrid = function(n) {
  var grid = new Array();
  for(var x = 0; x < n; x = x + 1) {
    grid[x] = new Array();
    for(var y = 0; y < n; y = y + 1) {
      grid[x][y] = 0;
    }
  }
  return grid;
};

var createInitalGrid = function(grid, lines) {
  var newGrid = grid;
  lines.forEach(function(line, index) {
    var pointer = 0;
    while(pointer < line.length) {
      if(isCorner(index, pointer, grid.length)) {
        newGrid[index][pointer] = '#';
      } else {
        newGrid[index][pointer] = line[pointer];
      }
      pointer++;
    }
  });
  return newGrid;
}

var copyGrid = function(grid) {
  var newGrid = [];
  for (var i = 0; i < grid.length; i++) {
    newGrid[i] = grid[i].slice();
  }
  return newGrid;
}

var isInvalidIndex = function(i, j, tempI, tempJ, length) {
  if((tempI === i && tempJ === j) ||
     tempI < 0 || tempJ < 0 || tempI >= length || tempJ >= length) {
       return true;
  } else {
    return false;
  }
}

var isGridLocationOn = function(grid, i, j) {
  if(grid[i][j] === '#') {
    return true;
  } else {
    return false;
  }
}

var stepGrid = function(grid) {
  var newGrid = copyGrid(grid);
  for(var i = 0; i < grid.length; i++) {
    for(var j = 0; j < grid.length; j++) {
      var lightsOn = 0;
      for(var tempI = i - 1; tempI <= i + 1; tempI++) {
        for(var tempJ = j - 1; tempJ <= j + 1; tempJ++) {
          if(isInvalidIndex(i, j, tempI, tempJ, grid.length)) {
            continue;
          } else {
            if(isGridLocationOn(grid, tempI, tempJ)) {
              lightsOn++;
            }
          }
        }
      }
      if(isCorner(i, j, grid.length)) {
        newGrid[i][j] = '#';
      } else if(isGridLocationOn(grid, i, j) &&
         (lightsOn < 2 || lightsOn > 3)) {
        newGrid[i][j] = '.';
      } else if(!isGridLocationOn(grid, i, j) &&
                lightsOn === 3) {
        newGrid[i][j] = '#';
      } else {
        newGrid[i][j] = grid[i][j];
      }
    }
  }
  return newGrid
}

var countLightsOn = function(grid) {
  var count = 0;
  for(var i = 0; i < grid.length; i++) {
    for(var j = 0; j < grid.length; j++) {
      if(isGridLocationOn(grid, i, j)) {
        count++;
      }
    }
  }
  return count;
}

// Main
const ARRAY_LENGTH = 100;
const NUM_STEPS = 100;
var lines = getInputData("input.txt").then(function(lines) {
  var grid = createSquareGrid(ARRAY_LENGTH);
  grid = createInitalGrid(grid, lines);
  //console.log(grid);
  for(var i = 0; i < NUM_STEPS; i++) {
    grid = stepGrid(grid);
  }
  //console.log(grid);
  console.log(countLightsOn(grid));
});
