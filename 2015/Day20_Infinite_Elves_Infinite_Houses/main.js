
var calculateFactors = function(integer) {
  var factorList = [];
  var squareRoot = Math.sqrt(integer);

  if(integer === 1) {
    return [1];
  }

  for(var i = 1; i <= squareRoot; i++) {
    var divideResult;
    if(integer % i === 0) {
      factorList.push(i);
      divideResult = integer / i;
      if (i !== divideResult) {
        factorList.push(divideResult);
      }
    }
  }
  return factorList;
}

var removeLimits = function(numberList, numberCountMap) {
  slimmedNumberList = [];
  numberList.forEach(function(number) {
    var numberString = number.toString();
    if(numberCountMap[numberString] === undefined) {
      numberCountMap[numberString] = 1;
      slimmedNumberList.push(number);
    } else if(numberCountMap[numberString] < MAX_VISITS) {
      numberCountMap[numberString] += 1;
      slimmedNumberList.push(number);
    }
  });
  return slimmedNumberList;
}


// Main
var PRESENTS_PER_ELF = 11;
var MAX_VISITS = 50;
var TARGET = 33100000;
//var TARGET = 28080;
//var TARGET = 15;

var houseNumber = 0;
var targetFound = false;
var elfPresentMap = {};

while(!targetFound) {
  var presentCount = 0;
  houseNumber++;
  var factorList = calculateFactors(houseNumber);
  factorList = removeLimits(factorList, elfPresentMap);
  factorList.forEach(function(elfNumber) {
    presentCount += elfNumber * PRESENTS_PER_ELF;
  });
  if(houseNumber % 1000 === 0) {
    console.log("House Number: ", houseNumber);
    console.log("PresentCount: ", presentCount, "\n");
  }

  if(presentCount >= TARGET) {
    targetFound = true;
    console.log("House Number: ", houseNumber);
    console.log("PresentCount: ", presentCount, "\n");
  }
}
