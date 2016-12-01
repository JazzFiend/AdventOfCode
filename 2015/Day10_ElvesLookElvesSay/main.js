var lookAndSay = function(numberString) {
  var currentNumber = numberString[0];
  var numberCount = 0;
  var toReturn = "";
  for(var i = 0; i < numberString.length; i++) {
    if(currentNumber != numberString[i]) {
      toReturn += numberCount + currentNumber;
      currentNumber = numberString[i];
      numberCount = 1;
    } else {
      numberCount++;
    }
  }
  toReturn += numberCount + currentNumber;
  currentNumber = numberString[i];
  return toReturn
}


// main
var INPUT = "1321131112";
var iterations = 50;

var currentString = INPUT;
for(var i = 0; i < iterations; i++) {
  currentString = lookAndSay(currentString);
  //console.log(currentString);
}
console.log(currentString.length);
