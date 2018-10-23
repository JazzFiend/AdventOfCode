import unittest

from Decompressor import Decompressor
from DecompressorV2 import DecompressorV2

class DecompressorAcceptanceTest(unittest.TestCase):
  def test_v1NoCompression(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('ADVENT'), 6)

  def test_v1OneCharacterCompressed(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('A(1x5)BC'), 7)

  def test_v1ManyCharactersCompressed(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('(3x3)XYZ'), 9)

  def test_v1ManyDecompressions(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('A(2x2)BCD(2x2)EFG'), 11)

  def test_v1OneChar(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('(6x1)(1x3)A'), 6)

  def test_v1SkipCompressionString(self):
    decompressor = Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLength('X(8x2)(3x3)ABCY'), 18)

  def test_partOne(self):
    decompressor = Decompressor()
    f = open('input.txt', 'r')
    compressedString = f.readline()
    f.close()
    print("\nPart One - Length of decompressed String: %s" % decompressor.calculateUncompressedLength(compressedString))

  def test_v2OneCompression(self):
    decompressor = DecompressorV2()
    self.assertEqual(decompressor.calculateUncompressedLength('(3x3)XYZ'), 9)

  def test_v2CompressionWithinCompression(self):
    decompressor = DecompressorV2()
    self.assertEqual(decompressor.calculateUncompressedLength('X(8x2)(3x3)ABCY'), 20)

  def test_v2ATonOfSubsequentCompressions(self):
    decompressor = DecompressorV2()
    self.assertEqual(decompressor.calculateUncompressedLength('(27x12)(20x12)(13x14)(7x10)(1x12)A'), 241920)

  def test_v2SwitchBetweenCompressionsAndSkips(self):
    decompressor = DecompressorV2()
    self.assertEqual(decompressor.calculateUncompressedLength('(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN'), 445)

  def test_partTwo(self):
    decompressor = DecompressorV2()
    f = open('input.txt', 'r')
    compressedString = f.readline()
    f.close()
    print("\nPart Two - Length of decompressed String: %s" % decompressor.calculateUncompressedLength(compressedString))

if __name__ == '__main__':
  unittest.main()
