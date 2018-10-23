from DecompressionStateV2 import RegularCharacterState
from DecompressionTracker import DecompressionTracker

class DecompressionStateContextV2(object):
  def __init__(self, compressedString):
    self._state = RegularCharacterState()
    self._compressedString = compressedString
    self._decompressionTrackerStack = [DecompressionTracker(len(compressedString) - 1, 1)]

  def advanceState(self, location):
   self._state.advanceState(self, self._compressedString[location], location)

  def setState(self, value):
    self._state = value

  def checkStopPoints(self, location):
    checkAgain = True
    while(checkAgain):
      checkAgain = False
      if(len(self._decompressionTrackerStack) != 1 and self._currentTrackerAtStopPoint(location)):
        self._updateTrackerStack()
        checkAgain = True

  def _currentTrackerAtStopPoint(self, location):
    return self._decompressionTrackerStack[self._topOfStack()].atStopPoint(location)

  def _topOfStack(self):
    return len(self._decompressionTrackerStack) - 1

  def _updateTrackerStack(self):
    currentDecompressionCount = self._decompressionTrackerStack.pop().calculateTotalLength()
    self._decompressionTrackerStack[self._topOfStack()].addToCount(currentDecompressionCount)

  def incrementForSingleCharacter(self):
    self._decompressionTrackerStack[(len(self._decompressionTrackerStack) - 1)].incrementCount()

  def addDecompression(self, charLength, duplicateLength, location):
    self._decompressionTrackerStack.append(DecompressionTracker((charLength + location), duplicateLength))

  def getFinalLength(self):
    return self._decompressionTrackerStack[0].calculateTotalLength()
