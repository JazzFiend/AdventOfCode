class DecompressionStateV2(object):
  def __init__(self):
    pass

  def advanceState(self, context, character, location):
    raise NotImplementedError('Abstract Function')

  def _isStartMarker(self, character):
    return character == '('

  def _isMidMarker(self, character):
    return character == 'x'

  def _isEndMarker(self, character):
    return character == ')'

class RegularCharacterState(DecompressionStateV2):
  def __init__(self):
    super(RegularCharacterState, self).__init__()

  def advanceState(self, context, character, location):
    if(self._isStartMarker(character)):
      context.setState(ExtractCharacterLengthState())
    else:
      context.incrementForSingleCharacter()

class ExtractCharacterLengthState(DecompressionStateV2):
  def __init__(self):
    super(ExtractCharacterLengthState, self).__init__()
    self._charLength = ""
  
  def advanceState(self, context, character, location):
    if(self._isMidMarker(character)):
      context.setState(ExtractDuplicatesState(int(self._charLength)))
    else:
      self._charLength += character

class ExtractDuplicatesState(DecompressionStateV2):
  def __init__(self, charLength):
    super(ExtractDuplicatesState, self).__init__()
    self._charLength = charLength
    self._duplicateLength = ""

  def advanceState(self, context, character, location):
    if(self._isEndMarker(character)):
      context.addDecompression(self._charLength, int(self._duplicateLength), location)
      context.setState(RegularCharacterState())
    else:
      self._duplicateLength += character
