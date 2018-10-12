from DecompressionState import NotCompressedState

class DecompressionStateContext:
  def __init__(self):
    self._state = NotCompressedState()
    self._charLength = ""
    self._duplicateLength = ""
    self._decompressedLength = 0

  def appendToCharacterLength(self, character):
    self._charLength += character

  def appendToDuplicateLength(self, character):
    self._duplicateLength += character

  def computeDecompressedLength(self):
    return (int(self._charLength) * int(self._duplicateLength))

  def reset(self):
    self._state = NotCompressedState()
    self._charLength = ""
    self._duplicateLength = ""

  # Make this a property
  def setState(self, state):
    self._state = state

  # Make this a property too
  def getCharLength(self):
    return self._charLength

  def increaseDecompressedLength(self, value):
    self._decompressedLength = self._decompressedLength + value