import unittest

from Factory import Factory

class FactoryTest(unittest.TestCase):
  def test_OneBotInputBinToOutputBin(self):
    input = [
      "value 25 goes to bot 7",
      "value 10 goes to bot 7",
      "bot 7 gives low to output 1 and high to output 10",
    ]

    factory = Factory()
    factory.runBots(input)
    self.assertEqual(factory.findBotComparisonById(7), "10,25")
    self.assertEqual(factory.findBotComparison("10,25"), 7)
    self.assertEqual(factory.getOutputBinValue("1"), [10])
    self.assertEqual(factory.getOutputBinValue("10"), [25])

  def test_throwWhenRunTwice(self):
    input = [
      "value 25 goes to bot 7",
      "value 10 goes to bot 7",
      "bot 7 gives low to output 1 and high to output 10",
    ]

    factory = Factory()
    factory.runBots(input)
    with self.assertRaises(Exception): factory.runBots(input)

  def test_OneBotInputBinToOutputBin_Backwards(self):
    input = [
      "bot 7 gives low to output 1 and high to output 10",
      "value 10 goes to bot 7",
      "value 25 goes to bot 7",
    ]

    factory = Factory()
    factory.runBots(input)
    self.assertEqual(factory.findBotComparisonById(7), "10,25")
    self.assertEqual(factory.getOutputBinValue("1"), [10])
    self.assertEqual(factory.getOutputBinValue("10"), [25])

  def test_BadInstruction(self):
    input = [
      "Way Down in the Hole",
    ]

    factory = Factory()
    with self.assertRaises(Exception): factory.runBots(input)

  def test_MultipleBotChain(self):
    input = [
      "value 2 goes to bot 2",
      "value 5 goes to bot 2",
      "bot 0 gives low to output 2 and high to output 0",
      "bot 1 gives low to output 1 and high to bot 0",
      "bot 2 gives low to bot 1 and high to bot 0",
      "value 3 goes to bot 1",
    ]

    factory = Factory()
    factory.runBots(input)
    self.assertEqual(factory.findBotComparisonById(2), "2,5")
    self.assertEqual(factory.findBotComparisonById(1), "2,3")
    self.assertEqual(factory.findBotComparisonById(0), "3,5")
    self.assertEqual(factory.getOutputBinValue("0"), [5])
    self.assertEqual(factory.getOutputBinValue("1"), [2])
    self.assertEqual(factory.getOutputBinValue("2"), [3])

  def test_Puzzle(self):
    instructionList = []
    getFileInput = True
    f = open('input.txt', 'r')
    while(getFileInput):
      line = f.readline()
      if(not line == ''):
        line = line[:-1]
        instructionList.append(line)
      else:
        getFileInput = False
    f.close()

    factory = Factory()
    factory.runBots(instructionList)
    self.assertEqual(factory.findBotComparison("17,61"), 116)
    multipliedValue = factory.getOutputBinValue("0")[0] * factory.getOutputBinValue("1")[0] * factory.getOutputBinValue("2")[0]
    self.assertEqual(multipliedValue, 116)

if __name__ == '__main__':
  unittest.main()
