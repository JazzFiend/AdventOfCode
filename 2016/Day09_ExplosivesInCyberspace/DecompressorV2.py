from copy import deepcopy

from DecompressionStateContextV2 import DecompressionStateContextV2

class DecompressorV2:
  def __init__(self):
    self._decompressedLength = 0
    self._stateContext = None

  def calculateUncompressedLength(self, compressedString):
    self._stateContext = DecompressionStateContextV2(compressedString)

    for i in range(0, len(compressedString)):
      self._stateContext.advanceState(i)
      self._stateContext.checkStopPoints(i)

    return self._stateContext.getFinalLength()
