file = 'inputTemp.txt'
compressedStrings = []
getFileInput = True

f = open(file, 'r')
while(getFileInput):
  line = f.readline()
  if(not line == ''):
    line = line[:-1]
    compressedStrings.append(line)
  else:
    getFileInput = False

print compressedStrings
