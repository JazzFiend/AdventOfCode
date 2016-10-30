var fs = require("fs");

var computeArea = function(length1, length2) {
  return length1 * length2;
}

var computeSurfaceAreaWithSlack = function(boxDimensions) {
  var totalSurfaceArea = 0;

  var currentSurfaceArea = computeArea(boxDimensions.length, boxDimensions.width);
  var smallestSurfaceArea = currentSurfaceArea;
  totalSurfaceArea = totalSurfaceArea + (2 * currentSurfaceArea);

  currentSurfaceArea = computeArea(boxDimensions.width, boxDimensions.height);
  if (currentSurfaceArea < smallestSurfaceArea) {
    smallestSurfaceArea = currentSurfaceArea;
  }
  totalSurfaceArea = totalSurfaceArea + (2 * currentSurfaceArea);

  currentSurfaceArea = computeArea(boxDimensions.length, boxDimensions.height);
  if (currentSurfaceArea < smallestSurfaceArea) {
    smallestSurfaceArea = currentSurfaceArea;
  }
  totalSurfaceArea = totalSurfaceArea + (2 * currentSurfaceArea + smallestSurfaceArea);

  return totalSurfaceArea;
}

var computePerimeter = function(side1, side2) {
  return 2 * side1 + 2 * side2;
}

var getMinOfThree = function(number1, number2, number3) {
  if(number1 <= number2 && number1 <= number3) {
    return number1;
  } else if(number2 <= number1 && number2 <= number3) {
    return number2;
  } else if(number3 <= number1 && number3 <= number2) {
    return number3;
  }
  throw("Unexpected state");
}

var extractSmallestPerimeter = function(cubicRectangle) {
  var perimeter1 = computePerimeter(cubicRectangle.length, cubicRectangle.width);
  var perimeter2 = computePerimeter(cubicRectangle.length, cubicRectangle.height);
  var perimeter3 = computePerimeter(cubicRectangle.width, cubicRectangle.height);
  return getMinOfThree(perimeter1, perimeter2, perimeter3);
}

var computeCubeVolume = function(length, width, height) {
  return length * width * height;
}

var computeRibbonLengthWithBow = function(boxDimensions) {
  return(extractSmallestPerimeter(boxDimensions) + computeCubeVolume(boxDimensions.length, boxDimensions.width, boxDimensions.height));
}

fs.readFile('input.txt', function (err, text) {
  if(err) {
    return console.error(err);
  }

  var lines = text.toString().split('\n');
  var totalWrappingPaper = 0;
  var totalRibbonLength = 0;
  for(var i = 0; i < lines.length; i++){
    if(lines[i]) {
      var seperatedString = lines[i].split('x');
      var currentBox = {
        length: seperatedString[0],
        width: seperatedString[1],
        height: seperatedString[2]
      };

      totalWrappingPaper += computeSurfaceAreaWithSlack(currentBox);
      totalRibbonLength += computeRibbonLengthWithBow(currentBox);
    }
  }
  console.log("Total Wrapping Paper: ", totalWrappingPaper);
  console.log("Total Ribbon Length: ", totalRibbonLength);
});
