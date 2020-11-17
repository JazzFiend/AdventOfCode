from ChipStorage import ChipStorage

class OutputChipStorage(ChipStorage):
  def __init__(self):
    super().__init__()
  
  def storeChip(self, chipId):
    if(len(self.chips) >= 1):
      raise Exception("Output can only hold one chip")
    else:
      self.chips.append(chipId)
