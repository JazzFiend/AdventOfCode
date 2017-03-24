


# Requires a dictionary that has integers as values.  Returns the key(s) with the
# largest value(s).  In the event of a tie, all keys are returned in alphabetical order.
def getLargestCount(dictonary):
  keysToReturn = []
  largestValue = 0
  for key in dictonary:
    if(dictonary[key] > largestValue):
      largestValue = dictonary[key]
      keysToReturn = []
      keysToReturn.append(key)
    elif(dictonary[key] == largestValue):
      keysToReturn.append(key)
  return sorted(keysToReturn)


# Start Here
signalList = [];
getFileInput = True;
decodedMessage = ""

f = open('input.txt', 'r')
while(getFileInput):
    line = f.readline()
    if(not line == ''):
        line = line[:-1]
        signalList.append(line)
    else:
        getFileInput = False

for i in range(0, len(signalList[0])):
  charCount = {}
  for signal in signalList:
    if(charCount.has_key(signal[i])):
      charCount[signal[i]] = charCount[signal[i]] + 1
    else:
      charCount[signal[i]] = 1
  decodedMessage = decodedMessage + getLargestCount(charCount)[0]

print ("Decoded Message: %s" % decodedMessage)
