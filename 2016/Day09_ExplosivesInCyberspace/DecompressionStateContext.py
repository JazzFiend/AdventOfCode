from DecompressionState import NotCompressedState

class DecompressionStateContext(object):
  def __init__(self):
    self._state = NotCompressedState()
    self._charLength = 0
    self._duplicateLength = 0
    self._decompressedLength = 0

  def resetCompressionValues(self):
    self._state = NotCompressedState()
    self._charLength = 0
    self._duplicateLength = 0

  def computeDecompressedLength(self):
    return (self._charLength * self._duplicateLength)

  def setState(self, value):
    self._state = value

  def increaseDecompressedLength(self, value):
    self._decompressedLength = self._decompressedLength + value

  def getCharLength(self):
    return self._charLength

  def setCharLength(self, value):
    self._charLength = value
  
  def setDuplicateLength(self, value):
    self._duplicateLength = value
