class DecompressionState(object):
  def __init__(self):
    pass

  def advanceState(self, context, character):
    raise NotImplementedError('Abstract Function')

  def _isStartMarker(self, character):
    return character == '('

  def _isMidMarker(self, character):
    return character == 'x'

  def _isEndMarker(self, character):
    return character == ')'

class NotCompressedState(DecompressionState):
  def __init__(self):
    super(NotCompressedState, self).__init__()

  def advanceState(self, context, character):
    if(self._isStartMarker(character)):
      context.setState(ExtractCharacterLengthState())
    else:
      context.increaseDecompressedLength(1)

class ExtractCharacterLengthState(DecompressionState):
  def __init__(self):
    super(ExtractCharacterLengthState, self).__init__()
    self._charLength = ""
  
  def advanceState(self, context, character):
    if(self._isMidMarker(character)):
      context.setCharLength(int(self._charLength))
      context.setState(ExtractDuplicatesState())
    else:
      self._charLength += character

class ExtractDuplicatesState(DecompressionState):
  def __init__(self):
    super(ExtractDuplicatesState, self).__init__()
    self._duplicateLength = ""

  def advanceState(self, context, character):
    if(self._isEndMarker(character)):
      context.setDuplicateLength(int(self._duplicateLength))
      context.setState(SkipDuplicateState())
    else:
      self._duplicateLength += character

class SkipDuplicateState(DecompressionState):
  def __init__(self):
    super(SkipDuplicateState, self).__init__()
    self._skipCounter = 1

  def advanceState(self, context, character):
    if(self._skipCounter < int(context.getCharLength())):
      self._skipCounter += 1
    else:
      self._skipCounter = 1
      context.increaseDecompressedLength(context.computeDecompressedLength())
      context.resetCompressionValues()
