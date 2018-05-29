
import unittest

import Decompressor

class DecompressorAcceptanceTest(unittest.TestCase):
  def test_noCompression(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('ADVENT'), 6)

  def test_oneCharacterCompressed(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('A(1x5)BC'), 7)

  def test_manyCharactersCompressed(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('(3x3)XYZ'), 9)

  def test_manyDecompressions(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('A(2x2)BCD(2x2)EFG'), 11)

  def test_oneChar(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('(6x1)(1x3)A'), 6)

  def test_skipCompressionString(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('X(8x2)(3x3)ABCY'), 18)

  def test_partOne(self):
    decompressor = Decompressor.Decompressor()
    f = open('input.txt', 'r')
    compressedString = f.readline()
    f.close()
    print("Length of decompressed String: %s" % decompressor.calculateUncompressedLength(compressedString))

if __name__ == '__main__':
  unittest.main()
