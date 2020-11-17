from Instruction import Instruction

class BotInstruction(Instruction):
  def __init__(self, botId, lowerType, lowerChip, higherType, higherChip):
    self.botId = botId
    self.lowerType = lowerType
    self.lowerChip = lowerChip
    self.higherType = higherType
    self.higherChip = higherChip

  def getBotId(self):
    return self.botId

  def getLowerType(self):
    return self.lowerType

  def getLowerChip(self):
    return self.lowerChip

  def getHigherType(self):
    return self.higherType
  
  def getHigherChip(self):
    return self.higherChip
