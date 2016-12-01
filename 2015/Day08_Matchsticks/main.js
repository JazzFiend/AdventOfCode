var Promise = require("bluebird");
var fs = Promise.promisifyAll(require("fs"));

var getInputData = function(fileName) {
  var lines;
  return fs.readFileAsync(fileName).then(function(fileData) {
    lines = fileData.toString().split('\n');
    return lines;
  });
}

var getMemoryCharacterCount = function(string) {
  if(string.length < 2 ||
    (string[0] != '\"' && string[string.length - 1] != '\"')) {
      throw("Quotes Missing");
  }
  var characterCount = 0;
  for(var i = 1; i < (string.length - 1); i++) {
    if(string[i] === '\\') {
      if(string[i + 1] === '\\' || string[i + 1] === '\"') {
        i++;
      } else if(string[i + 1] === 'x') {
        i += 3;
      } else {
        throw("Bad Backslash Character");
      }
    }
    characterCount++;
  }
  return characterCount;
}

var encodeString = function(string) {
  newString = '\"';
  for(var i = 0; i < string.length; i++) {
    if(string[i] === '\"') {
      newString += "\\\"";
    } else if(string[i] === '\\') {
      newString += "\\\\";
    } else {
      newString += string[i];
    }
  }
  newString += '\"';
  return newString.length;
}

// Main Start
var codeCharacterCount = 0;
var memoryCharacterCount = 0;
var newlyEncodedStringCount = 0;
var lines = getInputData("Input.txt").then(function(lines) {
  for(var i = 0; i < lines.length; i++) {
    if(lines[i] === "") {
      continue;
    }
    codeCharacterCount += lines[i].length;
    memoryCharacterCount += getMemoryCharacterCount(lines[i]);
    newlyEncodedStringCount += encodeString(lines[i]);
  }
  console.log("Code Character Count: ", codeCharacterCount);
  console.log("Memory Character Count: ", memoryCharacterCount);
  console.log("Newly Encoded String Count: ", newlyEncodedStringCount);
  console.log("Memory Code Difference: ", codeCharacterCount - memoryCharacterCount);
  console.log("Encoded String Difference: ", newlyEncodedStringCount - codeCharacterCount);
});
