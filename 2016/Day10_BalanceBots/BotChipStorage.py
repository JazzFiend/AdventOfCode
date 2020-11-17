from ChipStorage import ChipStorage

class BotChipStorage(ChipStorage):
  def __init__(self):
    super().__init__()
  
  def storeChip(self, chipId):
    if(len(self.chips) >= 2):
      raise Exception("Bot can only hold two chips")
    else:
      self.chips.append(chipId)

  def removeChip(self, chipId):
    self.chips.remove(chipId)