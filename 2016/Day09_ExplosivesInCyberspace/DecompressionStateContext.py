from DecompressionState import NotCompressedState

class DecompressionStateContext:
  def __init__(self, decompresssionVersion):
    self._state = NotCompressedState()
    self._charLength = 0
    self._duplicateLength = 0
    self._decompressedLength = 0
    self._version = decompresssionVersion
    # Should make v2 a subclass here. May need a subclass for Statemachine too.
    self._v2Overflow = 0

  def resetCompressionValues(self):
    self._state = NotCompressedState()
    self._charLength = 0
    self._duplicateLength = 0
    self._v2Overflow = 0

  def appendToDuplicateLength(self, character):
    self._duplicateLength += character

  def computeDecompressedLength(self):
    return (self._charLength * self._duplicateLength)

  def setState(self, value):
    self._state = value

  def getCharLength(self):
    return self._charLength

  def increaseDecompressedLength(self, value):
    self._decompressedLength = self._decompressedLength + value

  def setCharLength(self, value):
    self._charLength = value
  
  def setDuplicateLength(self, value):
    self._duplicateLength = value
  
  def applyOverflow(self):
    if(self._v2Overflow == 0):
      self._v2Overflow = self._duplicateLength
    else:
      self._v2Overflow *= self._duplicateLength

  def getOverflow(self):
    return self._v2Overflow

  def getVersion(self):
    return self._version