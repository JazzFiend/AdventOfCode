from Bot import Bot
from InstructionAnalyzer import InstructionAnalyzer
from OutputChipStorage import OutputChipStorage

class Factory:
  def __init__(self):
    self.botDictionary = {}
    self.outputBins = {}
    self.runCompleted = False

  def runBots(self, botInstructions):
    self.checkPreviousRun()
    currentInstructionList = botInstructions

    while(len(currentInstructionList)):
      nextInstructions = []
      for instruction in currentInstructionList:
        if(InstructionAnalyzer.isInputInstruction(instruction)):
          self.giveBotInputChip(instruction)
        elif(InstructionAnalyzer.isBotInstruction(instruction)):
          botInstruction = InstructionAnalyzer.extractBotData(instruction)
          if(self.botInstructionIsExecutable(botInstruction)):
            self.initiateBotGive(botInstruction)
          else:
            nextInstructions.append(instruction)
        else:
          raise "Bad instruction seen"
      currentInstructionList = nextInstructions
    self.runCompleted = True

  def checkPreviousRun(self):
    if(self.runCompleted):
      raise "Factory has already run"

  def giveBotInputChip(self, instruction):
    inputInstruction = InstructionAnalyzer.extractInputData(instruction)
    if(int(inputInstruction.getBotId()) not in self.botDictionary.keys()):
      self.botDictionary[inputInstruction.getBotId()] = Bot()
    self.botDictionary[inputInstruction.getBotId()].recieveChip(inputInstruction.getChipValue())

  def botInstructionIsExecutable(self, botInstruction):
    if(int(botInstruction.getBotId()) not in self.botDictionary.keys()):
      self.botDictionary[int(botInstruction.getBotId())] = Bot()
    return self.botDictionary[int(botInstruction.getBotId())].readyToGive()
    # return int(botInstruction.getBotId()) in self.botDictionary.keys() and self.botDictionary[int(botInstruction.getBotId())].readyToGive()

  def initiateBotGive(self, botInstruction):
    lowerRecieiver = self.prepReciever(botInstruction.getLowerType(), botInstruction.getLowerChip())
    higherReciever = self.prepReciever(botInstruction.getHigherType(), botInstruction.getHigherChip())
    self.botDictionary[int(botInstruction.getBotId())].giveChips(lowerRecieiver, higherReciever)

  def prepReciever(self, recieverType, recieverId):
    if(recieverType == "output"):
      self.outputBins[recieverId] = OutputChipStorage()
      return self.outputBins[recieverId]
    elif(recieverType == "bot"):
      if(int(recieverId) not in self.botDictionary.keys()):
        self.botDictionary[int(recieverId)] = Bot()
      return self.botDictionary[int(recieverId)].getChipStorage()

  def findBotComparisonById(self, botId):
    return self.botDictionary[botId].getComparison()

  def findBotComparison(self, comparison):
    for key in self.botDictionary.keys():
      if(self.botDictionary[key].getComparison() == comparison):
        return key
    return None

  def getOutputBinValue(self, outputId):
    return self.outputBins[outputId].getChips()