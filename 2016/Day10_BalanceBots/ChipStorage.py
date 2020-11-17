class ChipStorage:
  def __init__(self):
    self.chips = []
  
  def storeChip(self, chipId):
    raise Exception("Abstract Function")

  def getChips(self):
    return self.chips