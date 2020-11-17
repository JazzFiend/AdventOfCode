import unittest

from Bot import Bot
from OutputChipStorage import OutputChipStorage

class BotTest(unittest.TestCase):
  def test_botTakesTwoChips(self):
    bot = Bot()
    bot.recieveChip(5)
    self.assertEqual(bot.getChips(), [5])
    bot.recieveChip(48)
    self.assertEqual(bot.getChips(), [5, 48])

  def test_botTakesThreeChips(self):
    bot = Bot()
    bot.recieveChip(9)
    self.assertEqual(bot.getChips(), [9])
    bot.recieveChip(3)
    self.assertEqual(bot.getChips(), [9, 3])
    with self.assertRaises(Exception): bot.recieveChip(0)

  def test_botGivesChipsToOtherBots(self):
    botGiver = Bot()
    botTaker1 = Bot()
    botTaker2 = Bot()
    botGiver.recieveChip(10)
    botGiver.recieveChip(30)
    botGiver.giveChips(botTaker1.getChipStorage(), botTaker2.getChipStorage())
    self.assertEqual(botTaker1.getChips(), [10])
    self.assertEqual(botTaker2.getChips(), [30])
    self.assertEqual(botGiver.getComparison(), "10,30")

  def test_botGivesChipsToOtherBots_BackwardsComparison(self):
    botGiver = Bot()
    botTaker1 = Bot()
    botTaker2 = Bot()
    botGiver.recieveChip(30)
    botGiver.recieveChip(10)
    botGiver.giveChips(botTaker2.getChipStorage(), botTaker1.getChipStorage())
    self.assertEqual(botTaker1.getChips(), [30])
    self.assertEqual(botTaker2.getChips(), [10])
    self.assertEqual(botGiver.getComparison(), "10,30")

  def test_botThrowsWhenNotReadyToGive(self):
    botGiver = Bot()
    botTaker1 = Bot()
    botTaker2 = Bot()
    botGiver.recieveChip(3)
    with self.assertRaises(Exception): botGiver.giveChips(botTaker1.getChipStorage(), botTaker2.getChipStorage())

  def test_botGivesChipsToOutputBins(self):
    botGiver = Bot()
    outputBin1 = OutputChipStorage()
    outputBin2 = OutputChipStorage()
    botGiver.recieveChip(50)
    botGiver.recieveChip(90)
    botGiver.giveChips(outputBin1, outputBin2)
    self.assertEqual(outputBin1.getChips(), [50])
    self.assertEqual(outputBin2.getChips(), [90])

if __name__ == '__main__':
  unittest.main()
