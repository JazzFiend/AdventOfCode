
import unittest

import Decompressor

class DecompressorAcceptanceTest(unittest.TestCase):
  def test_givenTestCases(self):
    tests = {
      'ADVENT': 6,
      'A(1x5)BC': 7,
      '(3x3)XYZ': 0,
      #'A(2x2)BCD(2x2)EFG': 0,
      #'(6x1)(1x3)A': 0,
      #'X(8x2)(3x3)ABCY': 0
    }

    decompressor = Decompressor.Decompressor()
    for k in tests.keys():
      print(k)
      result = decompressor.calculateUncompressedLength(k)
      self.assertEqual(result, tests[k], k)

if __name__ == '__main__':
  unittest.main()
