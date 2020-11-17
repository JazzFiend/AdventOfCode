from Instruction import Instruction

class InputInstruction(Instruction):
  def __init__(self, botId, chipValue):
    self.botId = botId
    self.chipValue = chipValue
  
  def getBotId(self):
    return self.botId

  def getChipValue(self):
    return self.chipValue