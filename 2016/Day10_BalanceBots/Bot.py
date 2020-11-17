from BotChipStorage import BotChipStorage

class Bot:
  def __init__(self):
    self.compared = ""
    self.botChipStorage = BotChipStorage()

  def getChipStorage(self):
    return self.botChipStorage

  def recieveChip(self, chipId):
    self.botChipStorage.storeChip(chipId)

  def getChips(self):
    return self.botChipStorage.getChips()

  def giveChips(self, lesser, greater):
    chips = self.botChipStorage.getChips()
    self.checkMaxChips(chips)
    self.divyChips(chips, lesser, greater)
    self.compared = self.formatComparison(chips)
    self.removeAllChips(chips)

  def checkMaxChips(self, chips):
    if(len(chips) != 2):
      raise Exception("Bot does not have two chips")

  def divyChips(self, chips, lesser, greater):
    if(chips[0] < chips[1]):
      lesser.storeChip(chips[0])
      greater.storeChip(chips[1])
    else:
      greater.storeChip(chips[0])
      lesser.storeChip(chips[1])

  def formatComparison(self, chips):
    if(chips[0] < chips[1]):
      return "{},{}".format(chips[0], chips[1])
    else:
      return "{},{}".format(chips[1], chips[0])

  def removeAllChips(self, chips):
    self.botChipStorage.removeChip(chips[1])
    self.botChipStorage.removeChip(chips[0])

  def getComparison(self):
    return self.compared

  def readyToGive(self):
    return len(self.botChipStorage.getChips()) == 2
