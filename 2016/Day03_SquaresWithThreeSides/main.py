import Triangle

# Start Here
GROUPING_METHOD = 'COLUMN'
NUM_SIDES = 3
TRIANGLE_LIST = ['5  10  10']
TRIANGLE_LIST2 = [
  '101  301  501',
  '102  302  502',
  '103  303  503',
  '201  401  601',
  '202  402  602',
  '203  403  603',
#  The zeroes here are a hack.  I don't feel like covering for the last case
  '0    0    0'
]
triangleList = []
f = open('input.txt', 'r')
validTriangles = 0
getFileInput = True
sideCounter = 0
triangleTemp = [[], [], []];

while(getFileInput):
    line = f.readline()
    if(not line == ''):
        triangleList.append(line)
    else:
        getFileInput = False

if(GROUPING_METHOD == 'ROW'):
  for triangleString in triangleList:
      triangleValues = triangleString.split()
      triangle = Triangle.Triangle(int(triangleValues[0]), int(triangleValues[1]), int(triangleValues[2]))
      if(triangle.isValid()):
          validTriangles = validTriangles + 1
elif(GROUPING_METHOD == 'COLUMN'):
  for triangleString in triangleList:
    sideCounter = sideCounter + 1
    if(sideCounter > NUM_SIDES):
      for constructedTriangle in triangleTemp:
        triangle = Triangle.Triangle(constructedTriangle[0], constructedTriangle[1], constructedTriangle[2])
        if(triangle.isValid()):
          validTriangles = validTriangles + 1
      sideCounter = 1
      triangleTemp = [[], [], []]
      
    triangleValues = triangleString.split()
    triangleTemp[0].append(int(triangleValues[0]))
    triangleTemp[1].append(int(triangleValues[1]))
    triangleTemp[2].append(int(triangleValues[2]))

print("Valid Triangles: %s" % validTriangles)
