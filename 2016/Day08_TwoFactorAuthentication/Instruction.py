import sys

import Screen

from enum import Enum

class INSTRUCTION_TYPE(Enum):
  RECT = 1
  ROTATE_ROW = 2
  ROTATE_COLUMN = 3

class Instruction():
  # Constructor
  def __init__(self, instructionString):
    self._decodeInstructionString(instructionString);

  # Private Functions
  def _decodeInstructionString(self, instructionString):
    tokenizedInstruction = instructionString.split(' ')
    if(tokenizedInstruction[0] == "rect"):
      tokenizedArgs = tokenizedInstruction[1].split('x')
      self.instruction = INSTRUCTION_TYPE.RECT
      self.argument1 = int(tokenizedArgs[0])
      self.argument2 = int(tokenizedArgs[1])
    elif(tokenizedInstruction[0] == "rotate"):
      if(tokenizedInstruction[1] == "row"):
        self.instruction = INSTRUCTION_TYPE.ROTATE_ROW
      elif(tokenizedInstruction[1] == "column"):
        self.instruction = INSTRUCTION_TYPE.ROTATE_COLUMN
      else:
        raise ValueError('Incorrect Instruction: %s' % tokenizedInstruction[1])

      self.argument1 = int(tokenizedInstruction[2].split('=')[1])
      self.argument2 = int(tokenizedInstruction[4])
    else:
      raise ValueError('Incorrect Instruction: %s' % tokenizedInstruction[0])


  # Public Functions
  def display(self):
    print 'Instruction Type: %s' % self.instruction
    print "Arg1: %s" % self.argument1
    print "Arg2: %s" % self.argument2
    print "\n"

  def execute(screen)
    
