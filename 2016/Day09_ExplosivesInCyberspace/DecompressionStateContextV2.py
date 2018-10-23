from DecompressionStateV2 import RegularCharacterState
import DecompressionTracker

class DecompressionStateContextV2(object):
  def __init__(self, stringLength):
    self._state = RegularCharacterState()
    self._decompressionTrackerStack = [DecompressionTracker.DecompressionTracker(stringLength - 1, 1)]

  def resetCompressionValues(self):
    self._state = RegularCharacterState()

  def setState(self, value):
    self._state = value

  def increaseDecompressedLength(self, value):
    self._decompressedLength = self._decompressedLength + value

  def incrementForSingleCharacter(self):
    self._decompressionTrackerStack[(len(self._decompressionTrackerStack) - 1)].incrementCount()

  def addDecompression(self, charLength, duplicateLength, location):
    self._decompressionTrackerStack.append(DecompressionTracker.DecompressionTracker((charLength + location), duplicateLength))

  def checkStopPoints(self, location):
    checkAgain = True
    while(checkAgain):
      checkAgain = False
      if(len(self._decompressionTrackerStack) != 1 and self._decompressionTrackerStack[self._topOfStack()].atStopPoint(location)):
        currentDecompressionCount = self._decompressionTrackerStack.pop().calculateTotalLength()
        self._decompressionTrackerStack[self._topOfStack()].addToCount(currentDecompressionCount)
        checkAgain = True

  def getFinalLength(self):
    return self._decompressionTrackerStack[0].calculateTotalLength()

  def _topOfStack(self):
    return len(self._decompressionTrackerStack) - 1