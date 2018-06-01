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
          self.charLength = ""
          self.duplicateLength = ""
          decompressionStatus.decompressionState = DecompressionState.NOT_COMPRESSED
    return self._decompressedLength

  def calculateUncompressedLengthV2(self, compressedString):
    return self.calculateUncompressedLengthV1(compressedString)

  def __notCompressedAction(self, character, decompressionStatus):
    modifiedDecompressionStatus = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isStartMarker(character)):
      modifiedDecompressionStatus.decompressionState = DecompressionState.EXTRACT_CHAR_LENGTH
    else:
      self._decompressedLength += 1
    return modifiedDecompressionStatus

  def __extractCharLengthAction(self, character, decompressionStatus):
    modifiedDecompressionStatus = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isMidMarker(character)):
      modifiedDecompressionStatus.decompressionState = DecompressionState.EXTRACT_DUPLICATES
    else:
      modifiedDecompressionStatus.charLength += character
    return modifiedDecompressionStatus

  def __extractDuplicatesAction(self, character, decompressionStatus):
    modifiedDecompressionStatus = DecompressionStatusFields.clone(decompressionStatus)
    if(self.__isEndMarker(character)):
      modifiedDecompressionStatus.decompressionState = DecompressionState.SKIP_DUPLICATES
      self._decompressedLength += (int(modifiedDecompressionStatus.charLength) * int(modifiedDecompressionStatus.duplicateLength))
    else:
      modifiedDecompressionStatus.duplicateLength += character
    return modifiedDecompressionStatus

  def __isStartMarker(self, character):
    return character == '('

  def __isMidMarker(self, character):
    return character == 'x'
  
  def __isEndMarker(self, character):
    return character == ')'