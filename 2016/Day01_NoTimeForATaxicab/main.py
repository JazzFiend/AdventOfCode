from enum import Enum

import location

class Turn(Enum):
    LEFT = 1
    RIGHT = 2

class Facing(Enum):
    NORTH = 1
    EAST = 2
    SOUTH = 3
    WEST = 4

class InstructionTuple(Enum):
    TURN = 0
    BLOCKS = 1

class RepeatFound():
    def __init__(self, repeatFound = False):
        self._repeatFound = repeatFound
    def getrepeatFound(self):
        return self._repeatFound
    def setrepeatFound(self, value):
        self._repeatFound = value
    repeatFound = property(getrepeatFound, setrepeatFound, None, 'Repeat Found')

def getInstructions(instructionString):
    instructionArray = []
    splitStrings = instructionString.split(',')
    for instruction in splitStrings:
        sanitizedInstruciton = instruction.translate(None, ' ')
        instructionTuple = ()
        if sanitizedInstruciton[0] == 'L':
            instructionTuple = (Turn.LEFT, int(sanitizedInstruciton[1:]))
        elif sanitizedInstruciton[0] == 'R':
            instructionTuple = (Turn.RIGHT, int(sanitizedInstruciton[1:]))
        else:
            raise ValueError('Incorrect direction seen. Check input')
        instructionArray.append(instructionTuple)
    return instructionArray

def turnLeft(facing):
    if(facing == Facing.NORTH):
        return Facing.WEST
    elif(facing == Facing.WEST):
        return Facing.SOUTH
    elif(facing == Facing.SOUTH):
        return Facing.EAST
    elif(facing == Facing.EAST):
        return Facing.NORTH
    else:
        raise ValueError('Incorrect Facing seen.')

def turnRight(facing):
    if(facing == Facing.NORTH):
        return Facing.EAST
    elif(facing == Facing.WEST):
        return Facing.NORTH
    elif(facing == Facing.SOUTH):
        return Facing.WEST
    elif(facing == Facing.EAST):
        return Facing.SOUTH
    else:
        raise ValueError('Incorrect Facing seen.')

def walk(currentLocation, facing, numberOfBlocks, locationSet, repeatFound):
    locationToReturn = location.Location(currentLocation.getx(), currentLocation.gety())

    if(facing == Facing.NORTH):
        locationToReturn.moveYAxis(numberOfBlocks)
    elif(facing == Facing.SOUTH):
        locationToReturn.moveYAxis(numberOfBlocks * -1)
    elif(facing == Facing.WEST):
        locationToReturn.moveXAxis(numberOfBlocks * -1)
    elif(facing == Facing.EAST):
        locationToReturn.moveXAxis(numberOfBlocks)
    else:
        raise ValueError('Incorrect Facing seen.')
    locationsVisited = location.Location.getAllMidpoints(currentLocation, locationToReturn)
    for locationVisited in locationsVisited:
        if not locationVisited in locationSet:
            locationSet.add(locationVisited)
        elif(locationVisited in locationSet and repeatFound.getrepeatFound() == False):
            repeatFound.setrepeatFound(True);
            print "First Repeat Distance from Origin: %i" % distanceFromOrigin(locationVisited)
    #print "locationSet"
    #for l in locationSet:
    #    print l.display()
    return locationToReturn

def distanceFromOrigin(loc):
    xDistance = abs(loc.getx())
    yDistance = abs(loc.gety())
    return xDistance + yDistance

# Start Here
TEST_STRING = 'R8, R4, R4, R8'
f = open('input.txt', 'r')
fileData = f.readline()
reportFirstRepeat = False;
instructionString = fileData
facing = Facing.NORTH
currentLocation = location.Location()
repeatFound = RepeatFound()
locationsVisited = set()
locationsVisited.add(currentLocation)

instructionArray = getInstructions(instructionString)
for instruction in instructionArray:
    if (instruction[InstructionTuple.TURN.value] == Turn.LEFT):
        facing = turnLeft(facing)
    elif (instruction[InstructionTuple.TURN.value] == Turn.RIGHT):
        facing = turnRight(facing)
    else:
        raise ValueError('Incorrect Turn seen.  Check input')
    currentLocation = walk(currentLocation, facing, instruction[InstructionTuple.BLOCKS.value], locationsVisited, repeatFound)

print "Final Distance from Origin: %i" % distanceFromOrigin(currentLocation)
