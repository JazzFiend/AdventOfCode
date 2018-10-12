import unittest

import Decompressor

class DecompressorAcceptanceTest(unittest.TestCase):
  def test_v1NoCompression(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('ADVENT'), 6)

  def test_v1OneCharacterCompressed(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('A(1x5)BC'), 7)

  def test_v1ManyCharactersCompressed(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('(3x3)XYZ'), 9)

  def test_v1ManyDecompressions(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('A(2x2)BCD(2x2)EFG'), 11)

  def test_v1OneChar(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('(6x1)(1x3)A'), 6)

  def test_v1SkipCompressionString(self):
    decompressor = Decompressor.Decompressor()
    self.assertEqual(decompressor.calculateUncompressedLengthV1('X(8x2)(3x3)ABCY'), 18)

  def test_partOne(self):
    decompressor = Decompressor.Decompressor()
    f = open('input.txt', 'r')
    compressedString = f.readline()
    f.close()
    print("\nLength of decompressed String: %s" % decompressor.calculateUncompressedLengthV1(compressedString))

  # def test_v2ManyCharactersCompressed(self):
  #   decompressor = Decompressor.Decompressor()
  #   self.assertEqual(decompressor.calculateUncompressedLengthV2('(3x3)XYZ'), 9)

  # def test_v2SkipCompressionString(self):
  #   decompressor = Decompressor.Decompressor()
  #   self.assertEqual(decompressor.calculateUncompressedLengthV2('X(8x2)(3x3)ABCY'), 20)

if __name__ == '__main__':
  unittest.main()
