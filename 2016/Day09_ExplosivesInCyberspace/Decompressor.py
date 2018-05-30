from DecompressionState import DecompressionState

class Decompressor:
  def __init__(self):
    self.decompressionState = DecompressionState.NOT_COMPRESSED
    self.charLength = ""
    self.duplicateLength = ""
    return

  def calculateUncompressedLengthV1(self, compressedString):
    self.decompressedLength = 0
    self.decompressionState = DecompressionState.NOT_COMPRESSED
    self.charLength = ""
    self.duplicateLength = ""
    skipCounter = 1
    for character in compressedString:
      if(self.decompressionState == DecompressionState.NOT_COMPRESSED):
        self.__notCompressedAction(character)
      elif(self.decompressionState == DecompressionState.EXTRACT_CHAR_LENGTH):
        self.__extractCharLengthAction(character)
      elif(self.decompressionState == DecompressionState.EXTRACT_DUPLICATES):
        self.__extractDuplicatesAction(character)
      elif(self.decompressionState == DecompressionState.SKIP_DUPLICATES):
        if(skipCounter < int(self.charLength)):
          skipCounter += 1
        else:
          skipCounter = 1
          self.charLength = ""
          self.duplicateLength = ""
          self.decompressionState = DecompressionState.NOT_COMPRESSED

    return self.decompressedLength

  def calculateUncompressedLengthV2(self, compressedString):
    self.decompressedLength = 0
    self.decompressionState = DecompressionState.NOT_COMPRESSED
    self.charLength = ""
    self.duplicateLength = ""
    skipCounter = 1
    for i in range(0, len(compressedString)):
      if(self.decompressionState == DecompressionState.NOT_COMPRESSED):
        self.__notCompressedAction(compressedString[i])
      elif(self.decompressionState == DecompressionState.EXTRACT_CHAR_LENGTH):
        self.__extractCharLengthAction(compressedString[i])
      elif(self.decompressionState == DecompressionState.EXTRACT_DUPLICATES):
        self.__extractDuplicatesAction(compressedString[i])
      elif(self.decompressionState == DecompressionState.SKIP_DUPLICATES):
        if(skipCounter < int(self.charLength)):
          skipCounter += 1
        else:
          skipCounter = 1
          self.charLength = ""
          self.duplicateLength = ""
          self.decompressionState = DecompressionState.NOT_COMPRESSED

    return self.decompressedLength

  def __notCompressedAction(self, character):
    if(self.__isStartMarker(character)):
      self.decompressionState = DecompressionState.EXTRACT_CHAR_LENGTH
    else:
      self.decompressedLength += 1

  def __extractCharLengthAction(self, character):
    if(self.__isMidMarker(character)):
      self.decompressionState  = DecompressionState.EXTRACT_DUPLICATES
    else:
      self.charLength += character

  def __extractDuplicatesAction(self, character):
    if(self.__isEndMarker(character)):
      self.decompressionState = DecompressionState.SKIP_DUPLICATES
      self.decompressedLength += (int(self.charLength) * int(self.duplicateLength))
    else:
      self.duplicateLength += character

  def __isStartMarker(self, character):
    return character == '('

  def __isMidMarker(self, character):
    return character == 'x'
  
  def __isEndMarker(self, character):
    return character == ')'