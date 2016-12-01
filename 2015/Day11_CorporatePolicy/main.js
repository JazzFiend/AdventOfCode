var VALID_STRAIGHT = 3;
var INVALID_CHARACTERS = "iol";
var INPUT_PASSWORD = "hepxxyzz";

var replaceChar = function(string, location, character) {
  var toReturn = "";
  for(var i = 0; i < string.length; i++) {
    if(i === location) {
      toReturn += character;
    } else {
      toReturn += string[i];
    }
  }
  return toReturn;
}

var incrementString = function(string) {
  var pointer = string.length - 1;
  var s = string;
  var carry = true;
  while(carry === true) {
    if(s[pointer] === 'z') {
      if(pointer === 0) {
        s = "";
        for(var i = 0; i < string.length + 1; i++) {
          s += "a";
        }
        carry = false;
      } else {
        s = replaceChar(s, pointer, 'a');
        pointer--;
      }
    } else {
      s = replaceChar(s, pointer, String.fromCharCode(s.charCodeAt(pointer) + 1));
      carry = false;
    }
  }
  return s;
}

var containsStraight = function(string, consecutive) {
  var foundStraight = false;
  for(var i = 0; i < string.length - consecutive + 1; i++) {
    var charPointerValue = string.charCodeAt(i);
    foundStraight = true;
    for(var j = 1; j < consecutive; j++) {
      if((charPointerValue + j) != string.charCodeAt(i + j)) {
        foundStraight = false;
        break;
      }
    }
    if(foundStraight) {
      return true;
    }
  }
  return false;
}

var containsChar = function(string, charList) {
  for(var i = 0; i < string.length; i++) {
    for(var j = 0; j < charList.length; j++) {
      if(string[i] === charList[j]) {
        return true;
      }
    }
  }
  return false;
}

var countPairs = function(string) {
  var pairsSeen = 0;
  for(var i = 0; i < string.length - 1; i++) {
    if(string.charCodeAt(i) === string.charCodeAt(i + 1)) {
      pairsSeen++;
      i++;
    }
  }
  return pairsSeen;
}

// Main
var currentPassword = INPUT_PASSWORD;
var validPassword = false;
while(!validPassword) {
  currentPassword = incrementString(currentPassword);
  if(containsStraight(currentPassword, VALID_STRAIGHT) &&
     !containsChar(currentPassword, INVALID_CHARACTERS) &&
     countPairs(currentPassword) >= 2) {
    validPassword = true;
  }
}
console.log(currentPassword);
