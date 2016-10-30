var fs = require("fs");

var parseInstruction = function(instructionString) {
  var splitString = instructionString.split(' ');
  var parsedInstruction = {
    input1 : undefined,
    input2 : undefined,
    operation : undefined,
    output : undefined
  }
  if(splitString.length === 3) {
    // Passthrough instruction
    parsedInstruction.input1 = splitString[0];
    parsedInstruction.operation = "PASSTHROUGH";
    parsedInstruction.output = splitString[2];
  } else if (splitString.length === 4) {
    // NOT Instruction
    parsedInstruction.input1 = splitString[1];
    parsedInstruction.operation = splitString[0]
    parsedInstruction.output = splitString[3];
  } else if (splitString.length === 5) {
    // All other Instructions
    parsedInstruction.input1 = splitString[0];
    parsedInstruction.input2 = splitString[2];
    parsedInstruction.operation = splitString[1]
    parsedInstruction.output = splitString[4];
  } else if (splitString.length === 1) {
    // Blank line.  Return null.
    return undefined;
  } else {var currentInstruction
    throw("Incorrect number of args seen.");
  }
  return parsedInstruction;
}

var isInstructionActionable = function(instruction, outputMap) {
  if(isNaN(instruction.input1) &&
     !(instruction.input1 in outputMap)) {
    return false;
  }
  if(instruction.input2 != undefined &&
    (isNaN(instruction.input2) &&
    !(instruction.input2 in outputMap))) {
    return false;
  }
  return true;
}

var computeValue = function(instruction, circuitValueMap) {
  var input1;
  var input2;

  if(isNaN(parseInt(instruction.input1))) {
    input1 = circuitValueMap[instruction.input1];
  } else {
    input1 = instruction.input1;
  }

  if(instruction.input2 != undefined) {
    if(isNaN(parseInt(instruction.input2))) {
      input2 = circuitValueMap[instruction.input2];
    } else {
      input2 = instruction.input2;
    }
  }

  if(instruction.operation === "PASSTHROUGH") {
    return parseInt(input1);
  } else if(instruction.operation === "NOT") {
    return (~(input1) & 65535);
  } else if(instruction.operation === "AND") {
    return (input1 & input2);
  } else if(instruction.operation === "OR") {
    return (input1 | input2);
  } else if(instruction.operation === "LSHIFT") {
    return ((input1 << input2) & 65535);
  } else if(instruction.operation === "RSHIFT") {
    return (input1 >> input2);
  } else {
    throw("Unknown instruction seen");
  }
}

var constructCircuit = function(instructionList) {
  var currentInstructionList = instructionList;
  var circuitValueMap = [];
  while(currentInstructionList.length > 0) {
    var nonActionableInstructions = new Array();
    currentInstructionList.forEach(function(currentInstruction) {
      if(isInstructionActionable(currentInstruction, circuitValueMap)) {
        console.log("ACTIONABLE", currentInstruction);
        circuitValueMap[currentInstruction.output] = computeValue(currentInstruction, circuitValueMap);
      } else {
        console.log("NOT ACTIONABLE", currentInstruction);
        nonActionableInstructions[nonActionableInstructions.length] = currentInstruction;
      }
    });
    currentInstructionList = nonActionableInstructions;
    console.log(circuitValueMap);
  }
}


fs.readFile('inputRoundTwo.txt', function (err, text) {
  if(err) {
    console.error(err);
  }

  console.log(text.toString());
  var instructionText = text.toString().split('\n');
  var instructionList = new Array();
  instructionText.forEach(function(currentValue) {
    var currentInstruction = parseInstruction(currentValue);
    if(currentInstruction != undefined) {
      instructionList[instructionList.length] = currentInstruction;
    }
  });
  constructCircuit(instructionList);
});
