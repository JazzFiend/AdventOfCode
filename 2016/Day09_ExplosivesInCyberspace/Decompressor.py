from DecompressionState import DecompressionState
from DecompressionStatusFields import DecompressionStatusFields
from copy import deepcopy

class Decompressor:
  def __init__(self):
    self._decompressedLength = 0
    return

  def calculateUncompressedLengthV1(self, compressedString):
    decompressionStatus = DecompressionStatusFields()
    self._decompressedLength = 0
    skipCounter = 1

    for character in compressedString:
      if(decompressionStatus.decompressionState == DecompressionState.NOT_COMPRESSED):
        decompressionStatus = self.__notCompressedAction(character, decompressionStatus)
      elif(decompressionStatus.decompressionState == DecompressionState.EXTRACT_CHAR_LENGTH):
        decompressionStatus = self.__extractCharLengthAction(character, decompressionStatus)
      elif(decompressionStatus.decompressionState == DecompressionState.EXTRACT_DUPLICATES):
        decompressionStatus = self.__extractDuplicatesAction(character, decompressionStatus)
      elif(decompressionStatus.decompressionState == DecompressionState.SKIP_DUPLICATES):
        if(skipCounter < int(decompressionStatus.charLength)):
          skipCounter += 1
        else:
          skipCounter = 1
          decompressionStatus.reset()
    return self._decompressedLength

  def calculateUncompressedLengthV2(self, compressedString):
    return self.calculateUncompressedLengthV1(compressedString)

  def __notCompressedAction(self, character, decompressionStatus):
    newDS = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isStartMarker(character)):
      newDS.decompressionState = DecompressionState.EXTRACT_CHAR_LENGTH
    else:
      self._decompressedLength += 1
    return newDS

  def __extractCharLengthAction(self, character, decompressionStatus):
    newDS = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isMidMarker(character)):
      newDS.decompressionState = DecompressionState.EXTRACT_DUPLICATES
    else:
      newDS.charLength += character
    return newDS

  def __extractDuplicatesAction(self, character, decompressionStatus):
    newDS = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isEndMarker(character)):
      newDS.decompressionState = DecompressionState.SKIP_DUPLICATES
      self._decompressedLength += self.__computeLength(newDS)
    else:
      newDS.duplicateLength += character
    return newDS

  def __computeLength(self, decompressionStatus):
    return (int(decompressionStatus.charLength) * int(decompressionStatus.duplicateLength))

  def __isStartMarker(self, character):
    return character == '('

  def __isMidMarker(self, character):
    return character == 'x'
  
  def __isEndMarker(self, character):
    return character == ')'