
def satisfiesABBA(str):
  for i in range(0, len(str)):
    if(i < 1 or i >= len(str) - 2):
      continue
    if(str[i] == str[i + 1] and str[i - 1] == str[i + 2] and str[i - 1] != str[i]):
      return True
  return False

def isIP7(inputString):
  tempString = ""
  ipSequenceList = []
  hypernetSequenceList = []
  foundSequence = False

  for character in inputString:
    if(character == '['):
      if(len(tempString) > 0):
        ipSequenceList.append(tempString)
        tempString = ""
    elif character == ']':
      hypernetSequenceList.append(tempString)
      tempString = ""
    else:
      tempString = tempString + character
  ipSequenceList.append(tempString)

  for str in ipSequenceList:
    if(satisfiesABBA(str)):
      foundSequence = True;
  if(not foundSequence):
    return False

  for str in hypernetSequenceList:
    if(satisfiesABBA(str)):
      return False

  return True


#Start Here
stringList = []
getFileInput = True;
count = 0

f = open('input.txt', 'r')
while(getFileInput):
    line = f.readline()
    if(not line == ''):
        line = line[:-1]
        stringList.append(line)
    else:
        getFileInput = False

for str in stringList:
  if(isIP7(str)):
    count = count + 1;


print("Number of IP7s: %s" % count);
