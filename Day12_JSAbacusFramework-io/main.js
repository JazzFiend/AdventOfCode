var fs = require("fs");
var Stack = require('./stack');

var removeQuotes = function(text) {
  if(text[0] === '\"' && text[text.length - 1] === '\"') {
    return text.substring(1, text.length - 1);
  } else {
    return text;
  }
}

var parseObject = function(objectText) {
  var objectPairs = parseArray(objectText);
  var elementsToReturn = [];
  for(var i = 0; i < objectPairs.length; i++) {
    var elementStart = objectPairs[i].indexOf(':') + 1;
    var pairValue = objectPairs[i].substring(elementStart, objectPairs[i].length);
    if(objectIgnoreList.lastIndexOf(removeQuotes(pairValue)) >= 0) {
      return [];
    } else {
      elementsToReturn.push(pairValue);
    }
  }
  return elementsToReturn;
}

var parseArray = function(arrayText) {
  var stringBuilder = "";
  var elementsToReturn = [];
  var stack = Stack.initialize();

  if(arrayText.length <= 2) {
    return elementsToReturn;
  }

  for(var i = 1; i < arrayText.length - 1; i++) {
    if(arrayText[i] === ',' && stack.isEmpty()) {
      elementsToReturn.push(stringBuilder);
      stringBuilder = "";
    } else if(arrayText[i] === '{' || arrayText[i] === '[') {
      stack.push(arrayText[i]);
      stringBuilder += arrayText[i];
    } else if(arrayText[i] === '}' || arrayText[i] === ']') {
      stack.pop();
      stringBuilder += arrayText[i];
    } else {
      stringBuilder += arrayText[i];
    }
  }
  elementsToReturn.push(stringBuilder);
  return elementsToReturn;
}

var determineElementType = function(character) {
  if(character === '[') {
    return "array";
  } else if(character === '{') {
    return "object";
  } else if(character === '\"') {
    return "string";
  } else if(character === '-' || (Number(character) >= 0 && Number(character) <= 9)) {
    return "number";
  } else {
    throw("Weird value seen in determineElementType");
  }
}

var extractElements = function(elementText) {
  var elementType = determineElementType(elementText[0]);
  if(elementType === "array") {
    var elementList = parseArray(elementText);
    var arrayTotal = 0;
    for(var i = 0; i < elementList.length; i++) {
      arrayTotal += extractElements(elementList[i]);
    }
    return arrayTotal;
  } else if(elementType === "object") {
    var elementList = parseObject(elementText);
    var objectTotal = 0;
    for(var i = 0; i < elementList.length; i++) {
      objectTotal += extractElements(elementList[i]);
    }
    return objectTotal;
  } else if(elementType === "number") {
    return Number(elementText);
  } else if(elementType === "string") {
    return 0;
  }
}

// Main function
var objectIgnoreList = ["red"];
fs.readFile('input.txt', function (err, line) {
  if(err) {
    return console.error(err);
  }
  if(line.length != 0) {
    //line = "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}";
    var total = extractElements(line.toString());
    console.log("Total: ", total);
  }
});
