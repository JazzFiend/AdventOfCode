
TEST_ROOM_LIST = [
  'aaaaa-bbb-z-y-x-123[abxyz]',
  'a-b-c-d-e-f-g-h-987[abcde]',
  'not-a-real-room-404[oarel]',
  'totally-real-room-120[decoy]',
  'qzmt-zixmtkozy-ivhz-343[zimth]'
]

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

def extractFiveMostCommonChars(string):
  charCount = {}
  fiveMostCommon = []
  for char in string:
    if(char == '-'):
      continue
    if(charCount.has_key(char)):
      charCount[char] = charCount[char] + 1
    else:
      charCount[char] = 1

  while(len(fiveMostCommon) <= 4):
    nextMostCommon = getLargestCount(charCount);
    for item in nextMostCommon:
      fiveMostCommon.append(item)
      del charCount[item]
      if(len(fiveMostCommon) >= 5):
        break
  return fiveMostCommon

# Start Here
sectorTotal = 0
getFileInput = True
roomList = []
f = open('input.txt', 'r')

#roomList = TEST_ROOM_LIST
while(getFileInput):
    line = f.readline()
    if(not line == ''):
        roomList.append(line)
    else:
        getFileInput = False

for room in roomList:
  decoy = False;
  finalDashLocation = room.rfind('-')
  encryptedName = room[:finalDashLocation]
  openBracketLocation = room.rfind('[')
  sectorID = room[finalDashLocation + 1:openBracketLocation]
  closeBracketLocation = len(room) - 1
  checksum = room[openBracketLocation + 1:closeBracketLocation]
  fiveMostCommon = extractFiveMostCommonChars(encryptedName)
  for i in range(0,len(fiveMostCommon)):
    if(fiveMostCommon[i] != checksum[i]):
      decoy = True
      break
  if(not decoy):
    sectorTotal += int(sectorID)

print("Sector Total: %s" % sectorTotal)
