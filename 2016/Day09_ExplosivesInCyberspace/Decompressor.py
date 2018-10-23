from copy import deepcopy

from DecompressionState import NotCompressedState
from DecompressionStateContext import DecompressionStateContext

class Decompressor:
  def __init__(self):
    self._decompressedLength = 0
    self._decompressionState = NotCompressedState()
    self._stateContext = DecompressionStateContext()

  def calculateUncompressedLength(self, compressedString):
    self._decompressedLength = 0

    for character in compressedString:
      self._stateContext._state.advanceState(self._stateContext, character)

    return self._stateContext._decompressedLength
