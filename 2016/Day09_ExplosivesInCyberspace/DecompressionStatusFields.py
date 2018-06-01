from DecompressionState import DecompressionState
from copy import deepcopy

class DecompressionStatusFields:
  def __init__(self):
    self.reset()

  def reset(self):
    self._decompressionState = DecompressionState.NOT_COMPRESSED
    self._charLength = ""
    self._duplicateLength = ""
  
  @staticmethod
  def clone(decompressionStatus):
    return deepcopy(decompressionStatus)

  @property
  def decompressionState(self):
    return self._decompressionState

  @decompressionState.setter
  def decompressionState(self, value):
    self._decompressionState = value

  @property
  def charLength(self):
    return self._charLength

  @charLength.setter
  def charLength(self, value):
    self._charLength = value

  @property
  def duplicateLength(self):
    return self._duplicateLength

  @duplicateLength.setter
  def duplicateLength(self, value):
    self._duplicateLength = value