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

  def _turnOnRectangle(self, screen, width, height):
    screen.turnOnRange(width, height)

  def _shiftDown(self, screen, column, distance):
    startingPoint = distance % screen.getHeight()
    originalColumn = []
    pointer = startingPoint
    for i in range(0, screen.getHeight()):
      originalColumn.append(screen.getPixelState(column, i))
    for i in range(0, screen.getHeight()):
      screen.setPixel(column, pointer, originalColumn[i])
      pointer += 1
      if(pointer == screen.getHeight()):
        pointer = 0

  def _shiftRight(self, screen, row, distance):
    startingPoint = distance % screen.getWidth()
    originalRow = []
    pointer = startingPoint
    for i in range(0, screen.getWidth()):
      originalRow.append(screen.getPixelState(i, row))
    for i in range(0, screen.getWidth()):
      screen.setPixel(pointer, row, originalRow[i])
      pointer += 1
      if(pointer == screen.getWidth()):
        pointer = 0

  # Public Functions
  def display(self):
    print 'Instruction Type: %s' % self.instruction
    print "Arg1: %s" % self.argument1
    print "Arg2: %s" % self.argument2
    print "\n"

  def execute(self, screen):
    if(self.instruction == INSTRUCTION_TYPE.RECT):
      self._turnOnRectangle(screen, self.argument1, self.argument2)
    elif(self.instruction == INSTRUCTION_TYPE.ROTATE_COLUMN):
      self._shiftDown(screen, self.argument1, self.argument2)
    elif(self.instruction == INSTRUCTION_TYPE.ROTATE_ROW):
      self._shiftRight(screen, self.argument1, self.argument2)
    else:
      raise ValueError('Incorrect Instruction: %s' % self.instruction)
