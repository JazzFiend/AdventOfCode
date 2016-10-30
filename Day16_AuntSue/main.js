var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var auntSue = {
  name: '0',
  children: '3',
  cats: '>7',
  samoyeds: '2',
  pomeranians: '<3',
  akitas: '0',
  vizslas: '0',
  goldfish: '<5',
  trees: '>3',
  cars: '2',
  perfumes: '1'
};

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var isObjectSimilar = function(objectToCompare, goldenObject) {
  var toReturn = true;
  Object.keys(objectToCompare).forEach(function(keyToCompare) {
    if(keyToCompare !== 'name') {
      if(goldenObject[keyToCompare][0] === '>') {
        if(Number(objectToCompare[keyToCompare]) <= Number(removeFirstCharacter(goldenObject[keyToCompare]))) {
          toReturn = false;
        }
      } else if(goldenObject[keyToCompare][0] === '<') {
        if(Number(objectToCompare[keyToCompare]) >= Number(removeFirstCharacter(goldenObject[keyToCompare]))) {
          toReturn = false;
        }
      } else {
        if(Number(objectToCompare[keyToCompare]) !== Number(goldenObject[keyToCompare])) {
          toReturn = false;
        }
      }
    }
  });
  return toReturn;
}

var removeLastCharacter = function(string) {
  return string.substr(0, string.length - 1);
}

var removeFirstCharacter = function(string) {
  return string.substr(1, string.length);
}

var makeSueObject = function(text) {
  var tokenized = text.split(' ');
  var toReturn = {};
  toReturn['name'] = Number(removeLastCharacter(tokenized[1]));
  toReturn[removeLastCharacter(tokenized[2])] = removeLastCharacter(tokenized[3]);
  toReturn[removeLastCharacter(tokenized[4])] = removeLastCharacter(tokenized[5]);
  toReturn[removeLastCharacter(tokenized[6])] = tokenized[7];
  return toReturn;
}


// Main
var lines = getInputData("input.txt").then(function(lines) {
  lines.forEach(function(line) {
    if(line !== '') {
      var currentSue = makeSueObject(line);
      if(isObjectSimilar(currentSue, auntSue)) {
        console.log(currentSue.name);
      }
    }
  });
});
