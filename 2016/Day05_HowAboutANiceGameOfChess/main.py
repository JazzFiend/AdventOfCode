import hashlib

doorPassword = ""
doorID = "ffykfhsq"
loopCount = 0

while(len(doorPassword) < 8):
  hashFunction = hashlib.md5()

  currentHash = doorID + str(loopCount)
  hashFunction.update(currentHash)
  result = hashFunction.hexdigest()

  if(result[0:5] == '00000'):
    doorPassword = doorPassword + result[5]
    print(doorPassword, currentHash)
  loopCount = loopCount + 1

print('Password: %s' % doorPassword)
