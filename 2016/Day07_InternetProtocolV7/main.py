def satisfiesABBA(str):
  for i in range(0, len(str)):
    if(i < 1 or i >= len(str) - 2):
      continue
    if(str[i] == str[i + 1] and str[i - 1] == str[i + 2] and str[i - 1] != str[i]):
      return True
  return False

def extractABASequences(str):
  toReturn = []
  for i in range(0, len(str) - 2):
    if(str[i] == str[i + 2] and str[i + 1] != str[i]):
      toReturn.append(str[i:i + 3])
  return toReturn

def satisfiesBAB(str, aba):
  a = aba[0]
  b = aba[1]
  
  for i in range(0, len(str) - 2):
    if(str[i] == b and str[i + 1] == a and str[i + 2] == b):
      return True
  return False  

def supportsTLS(ipSequenceList, hypernetSequenceList):
  tempString = ""
  foundSequence = False

  for str in ipSequenceList:
    if(satisfiesABBA(str)):
      foundSequence = True;
  if(not foundSequence):
    return False

  for str in hypernetSequenceList:
    if(satisfiesABBA(str)):
      return False

  return True

def supportsSSL(ipSequenceList, hypernetSequenceList):
  ABASequences = []
  for ipSequence in ipSequenceList:
    ABAs = extractABASequences(ipSequence)
    if(ABAs):
      for ABA in ABAs:
        ABASequences.append(ABA)

  for ABA in ABASequences:
    for hypernetSequence in hypernetSequenceList:
      if(satisfiesBAB(hypernetSequence, ABA)):
        return True
  return False 

def seperateByBrackets(string):
  toReturn = {
    'withoutBrackets': [],
    'withBrackets': []
  }
  tempString = ''

  for character in string:
    if(character == '['):
      if(len(tempString) > 0):
        toReturn['withoutBrackets'].append(tempString)
        tempString = ""
    elif character == ']':
      toReturn['withBrackets'].append(tempString)
      tempString = ""
    else:
      tempString = tempString + character
  toReturn['withoutBrackets'].append(tempString)
  return toReturn

#Start Here
stringList = []
getFileInput = True;
TLSCount = 0
SSLCount = 0

f = open('input.txt', 'r')
while(getFileInput):
  line = f.readline()
  if(not line == ''):
    line = line[:-1]
    stringList.append(line)
  else:
    getFileInput = False

for str in stringList: 
  brokenDownIp = seperateByBrackets(str)
  if(supportsTLS(brokenDownIp['withoutBrackets'], brokenDownIp['withBrackets'])):
    TLSCount = TLSCount + 1
  if(supportsSSL(brokenDownIp['withoutBrackets'], brokenDownIp['withBrackets'])):
    SSLCount = SSLCount + 1

print("Number of TLS: %s" % TLSCount)
print("Number of SSL: %s" % SSLCount)
