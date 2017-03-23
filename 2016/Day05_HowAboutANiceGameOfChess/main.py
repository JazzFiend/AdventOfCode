import hashlib

def isNumericString(str):
  for char in str:
    if(char == '0' or
       char == '1' or
       char == '2' or
       char == '3' or
       char == '4' or
       char == '5' or
       char == '6' or
       char == '7' or
       char == '8' or
       char == '9'):
      continue
    else:
      return False
  return True

# Start Here
doorPassword = ""
DOOR_ID = "ffykfhsq"
loopCount = 0
while(len(doorPassword) < 8):
  hashFunction = hashlib.md5()
  currentHash = DOOR_ID + str(loopCount)
  hashFunction.update(currentHash)
  result = hashFunction.hexdigest()
  if(result[0:5] == '00000'):
    doorPassword = doorPassword + result[5]
    print(doorPassword, currentHash)
  loopCount = loopCount + 1
print('1st Password: %s' % doorPassword)

doorPassword = ['-', '-', '-', '-', '-', '-', '-', '-']
loopCount = 0
while('-' in doorPassword):
  hashFunction = hashlib.md5()
  currentHash = DOOR_ID + str(loopCount)
  hashFunction.update(currentHash)
  result = hashFunction.hexdigest()
  if(result[0:5] == '00000'):
    if(isNumericString(result[5])):
      location = int(result[5])
      if(location < 8 and doorPassword[location] == '-'):
        doorPassword[location] = result[6]
        print(doorPassword, currentHash)
  loopCount = loopCount + 1
print('2nd Password: %s' % doorPassword)
