var fs = require("fs");
var assert = require("assert");

var FUZZY_BRIGHTNESS = true;

// Initialize an n x n array to all zeroes
var createSquareArray = function(n) {
  var grid = new Array();
  for(var x = 0; x < n; x = x + 1) {
    grid[x] = new Array();
    for(var y = 0; y < n; y = y + 1) {
      grid[x][y] = 0;
    }
  }
  return grid;
};

var displaySquareArray = function(grid) {
  var dimension = grid.length;
  for(var y = dimension - 1; y >= 0; y = y - 1) {
    var stringToLog = '';
    for(var x = 0; x < dimension; x = x + 1) {
      stringToLog = stringToLog + "    " + grid[x][y];
    }
    console.log(stringToLog);
  }
  console.log('\n')
};

var performInstruction = function(lightGrid, instruction) {
  for(var x = instruction.startX; x <= instruction.endX; x = x + 1) {
    for(var y = instruction.startY; y <= instruction.endY; y = y + 1) {
      if(FUZZY_BRIGHTNESS) {
        if(instruction.command === "on") {
          lightGrid[x][y]++;
        } else if(instruction.command === "off" && lightGrid[x][y] > 0) {
          lightGrid[x][y]--;
        } else if(instruction.command === "toggle") {
          lightGrid[x][y] += 2;
        } else {
          if(lightGrid[x][y] > 0) {
            console.log("HOLY MOLY FAM!");
          }
        }
      } else {
        if(instruction.command === "on" ||
           instruction.command === "toggle" && lightGrid[x][y] === 0) {
          lightGrid[x][y] = 1;
        } else if(instruction.command === "off" ||
                  instruction.command === "toggle" && lightGrid[x][y] === 1) {
          lightGrid[x][y] = 0;
        } else {
          console.log("DANGER WILL ROBINSON!!!");
        }
      }
    }
  }
  return lightGrid;
}

var extractInstruction = function(line) {
  var lineTokens = line.toString().split(' ');
  var instruction = {
    command: undefined,
    startX: undefined,
    startY: undefined,
    endX: undefined,
    endY: undefined
  }
  var startingPoint;

  if(lineTokens.length === 4) {
    assert(lineTokens[0] === "toggle");
    instruction.command = "toggle";
    startingPoint = 1;
  } else if(lineTokens.length === 5) {
    assert(lineTokens[1] === "off" || lineTokens[1] === "on");
    instruction.command = lineTokens[1];
    startingPoint = 2;
  } else {
    console.log("BAD LENGTH SEEN");

  }
  var pair = extractPair(lineTokens[startingPoint]);
  instruction.startX = Number(pair.x);
  instruction.startY = Number(pair.y);
  pair = extractPair(lineTokens[startingPoint + 2]);
  instruction.endX = Number(pair.x);
  instruction.endY = Number(pair.y);

  return instruction;
}

var extractPair = function(pair) {
  var numbers = pair.toString().split(',');
  var pair = {
    'x': numbers[0],
    'y': numbers[1]
  };
  return pair;
}

var calculateTotalBrightness = function(lightGrid) {
  var dimension = lightGrid.length;
  var count = 0;
  for(var x = 0; x < dimension; x = x + 1) {
    for(var y = 0; y < dimension; y = y + 1) {
      count += lightGrid[x][y];
    }
  }
  return count;
}

var lightGrid = createSquareArray(1000);
//displaySquareArray(lightGrid);
//console.log('\n');

fs.readFile('input.txt', function (err, text) {
  if(err) {
    return console.error(err);
  }
  var instructionList = text.toString().split('\n');
  for(var i = 0; i < instructionList.length; i = i + 1) {
    if(instructionList[i].toString() === '') {
      continue;
    }
    var currentInstruction = extractInstruction(instructionList[i]);
    lightGrid = performInstruction(lightGrid, currentInstruction);
    //displaySquareArray(lightGrid);
    //console.log('\n');
  }
  console.log(calculateTotalBrightness(lightGrid));
});
