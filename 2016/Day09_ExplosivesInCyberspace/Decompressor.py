from DecompressionState import DecompressionState

class Decompressor:
  # Constructor
  def __init__(self, compressedString):
    self.compressedString = compressedString
    self.decompressionState = DecompressionState.NOT_COMPRESSED

  # Public Methods
  def calculateUncompressedLength(self):
    decompressedLength = 0
    charLength = ""
    duplicateLength = ""
    skipCounter = 1
    for character in self.compressedString:
      if(self.decompressionState == DecompressionState.NOT_COMPRESSED):
        if(character == '('):
          self.decompressionState = DecompressionState.EXTRACT_CHAR_LENGTH
        else:
          decompressedLength += 1
      elif(self.decompressionState == DecompressionState.EXTRACT_CHAR_LENGTH):
        if(character == 'x'):
          self.decompressionState  = DecompressionState.EXTRACT_DUPLICATES
        else:
          charLength += character
      elif(self.decompressionState == DecompressionState.EXTRACT_DUPLICATES):
        if(character == ')'):
          self.decompressionState = DecompressionState.SKIP_DUPLICATES
          decompressedLength += (int(charLength) * int(duplicateLength))
        else:
          duplicateLength += character
      elif(self.decompressionState == DecompressionState.SKIP_DUPLICATES):
        if(skipCounter < int(charLength)):
          skipCounter += 1
        else:
          skipCounter = 1
          charLength = ""
          duplicateLength = ""
          self.decompressionState = DecompressionState.NOT_COMPRESSED

    return decompressedLength
  