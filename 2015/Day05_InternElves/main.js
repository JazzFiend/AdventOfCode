var fs = require("fs");

var isVowel = function(c) {
  if(c === 'a' ||
     c === 'e' ||
     c === 'i' ||
     c === 'o' ||
     c === 'u') {
    return true;
  }
  return false;
};

var hasThreeVowels = function(s) {
  var numberOfVowels = 0;
  for(var i = 0; i < s.length; i = i + 1) {
    if(isVowel(s[i])) {
      numberOfVowels = numberOfVowels + 1;
    }
    if(numberOfVowels === 3) {
      return true;
    }
  }
  return false;
};

var hasDoubleChar = function(s) {
  for(var i = 0; i < (s.length - 1); i = i + 1) {
    if(s[i] === s[i + 1]) {
      return true;
    }
  }
  return false;
}

var isNaughtyPair = function(c) {
  if(c === 'ab' ||
     c === 'cd' ||
     c === 'pq' ||
     c === 'xy') {
    return true;
  }
  return false;
}

var hasNaughtyPair = function(s) {
  for(var i = 0; i < (s.length - 1); i = i + 1) {
    if(isNaughtyPair(s[i] + s[i + 1])) {
      return true;
    }
  }
  return false;
}

var hasNicePair = function(s) {
  for(var i = 0; i < s.length - 2; i++) {
    var testPair = s[i] + s[i + 1];
    for(var j = i + 2; j < s.length - 1; j++) {
      if(testPair === (s[j] + s[j + 1])) {
        return true;
      }
    }
  }
  return false;
}

var hasSkippedRepeat = function(s) {
  for(var i = 0; i < s.length - 2; i++) {
    if(s[i] === s[i + 2]) {
      return true;
    }
  }
  return false;
}

fs.readFile('input.txt', function (err, text) {
  if(err) {
    return console.error(err);
  }

  var lines = text.toString().split('\n');
  var niceCount = 0;
  var newNiceCount = 0;
  for(var i = 0;i < lines.length;i++) {
    if(hasThreeVowels(lines[i]) &&
       hasDoubleChar(lines[i]) &&
       !hasNaughtyPair(lines[i])) {
         niceCount = niceCount + 1;
    }

    if(hasNicePair(lines[i]) &&
       hasSkippedRepeat(lines[i])) {
         newNiceCount += 1;
    }
  }
  console.log('Nice Strings Criteria1: ', niceCount);
  console.log('Nice Strings Criteria2: ', newNiceCount);
});
