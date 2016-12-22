import Triangle

# Start Here
TRIANGLE_LIST = ['5  10  10']
triangleList = []
f = open('input.txt', 'r')
validTriangles = 0
getFileInput = True

while(getFileInput):
    line = f.readline()
    if(not line == ''):
        triangleList.append(line)
    else:
        getFileInput = False

for triangleString in triangleList:
    triangleValues = triangleString.split()
    triangle = Triangle.Triangle(int(triangleValues[0]), int(triangleValues[1]), int(triangleValues[2]))
    if(triangle.isValid()):
        validTriangles = validTriangles + 1

print("Valid Triangles: %s" % validTriangles)
