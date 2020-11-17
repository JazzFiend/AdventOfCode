import re

from InputInstruction import InputInstruction
from BotInstruction import BotInstruction

class InstructionAnalyzer:
  @staticmethod
  def isInputInstruction(instruction):
    return "value" in instruction

  @staticmethod
  def extractInputData(inputInstruction):
    numbersInInstruction = re.findall("\d+", inputInstruction)
    return InputInstruction(int(numbersInInstruction[1]), int(numbersInInstruction[0]))

  @staticmethod
  def isBotInstruction(instruction):
    return "gives" in instruction

  @staticmethod
  def extractBotData(botInstruction):
    numbersInInstruction = re.findall("\d+", botInstruction)
    instructionTokenized = re.split("\d+", botInstruction)
    lowerType = InstructionAnalyzer.determineRecieverType(instructionTokenized[1])
    higherType = InstructionAnalyzer.determineRecieverType(instructionTokenized[2])
    return BotInstruction(numbersInInstruction[0], lowerType, numbersInInstruction[1], higherType, numbersInInstruction[2])

  @staticmethod
  def determineRecieverType(reciever):
    if("bot" in reciever):
      return "bot"
    elif("output" in reciever):
      return "output"
    else:
      raise "Bad Instruction Seen: {}".format(reciever)