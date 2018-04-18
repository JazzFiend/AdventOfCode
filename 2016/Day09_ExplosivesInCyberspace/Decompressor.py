from DecompressionState import DecompressionState

class Decompressor:
  # Constructor
  def __init__(self, compressedString):
    self.compressedString = compressedString
    self.decompressionState = DecompressionState.NOT_COMPRESSED
    self.decompressedLength = 0
    self.charLength = ""
    self.duplicateLength = ""

  # Public Methods
  def calculateUncompressedLength(self):
    self.decompressedLength = 0
    self.charLength = ""
    self.duplicateLength = ""
    skipCounter = 1
    for character in self.compressedString:
      if(self.decompressionState == DecompressionState.NOT_COMPRESSED):
        self.notCompressedAction(character)
      elif(self.decompressionState == DecompressionState.EXTRACT_CHAR_LENGTH):
        self.extractCharLengthAction(character)
      elif(self.decompressionState == DecompressionState.EXTRACT_DUPLICATES):
        self.extractDuplicatesAction(character)
      elif(self.decompressionState == DecompressionState.SKIP_DUPLICATES):
        if(skipCounter < int(self.charLength)):
          skipCounter += 1
        else:
          skipCounter = 1
          self.charLength = ""
          self.duplicateLength = ""
          self.decompressionState = DecompressionState.NOT_COMPRESSED

    return self.decompressedLength
  
  def getDecompressedLength(self):
    if(self.decompressedLength == 0):
      return self.calculateUncompressedLength()
    else:
      return self.decompressedLength

  # Private Methods
  def notCompressedAction(self, character):
    if(self.isStartMarker(character)):
      self.decompressionState = DecompressionState.EXTRACT_CHAR_LENGTH
    else:
      self.decompressedLength += 1

  def extractCharLengthAction(self, character):
    if(self.isMidMarker(character)):
      self.decompressionState  = DecompressionState.EXTRACT_DUPLICATES
    else:
      self.charLength += character

  def extractDuplicatesAction(self, character):
    if(self.isEndMarker(character)):
      self.decompressionState = DecompressionState.SKIP_DUPLICATES
      self.decompressedLength += (int(self.charLength) * int(self.duplicateLength))
    else:
      self.duplicateLength += character

  def isStartMarker(self, character):
    return character == '('

  def isMidMarker(self, character):
    return character == 'x'
  
  def isEndMarker(self, character):
    return character == ')'