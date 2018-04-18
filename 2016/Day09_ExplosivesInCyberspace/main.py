from Decompressor import Decompressor

def readCompressedString(fileName):
  f = open(fileName, 'r')
  return f.readline()

# main
compressedString = readCompressedString("input.txt")
#compressedString = "X(8x2)(3x3)ABCY"
decompressor = Decompressor(compressedString)
decompressedLength = decompressor.calculateUncompressedLength()
print "Decompressed Length: %i" % decompressedLength
