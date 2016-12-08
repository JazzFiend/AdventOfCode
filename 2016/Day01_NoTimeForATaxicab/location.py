class Location():
    def __init__(self, x = 0, y = 0):
        self._x = x
        self._y = y
    def __eq__(self, other):
        if (self.getx() == other.getx() and self.gety() == other.gety()):
            return True
        else:
            return False
    def __hash__(self):
        return 2^self.getx() + 2^self.gety()
    def getx(self):
        return self._x
    def setx(self, value):
        self._x = value
    def gety(self):
        return self._y
    def sety(self, value):
        self._y = value

    def moveXAxis(self, value):
        self.setx(self.getx() + value)
    def moveYAxis(self, value):
        self.sety(self.gety() + value)
    def display(self):
        print "(%s, %s)" % (self.getx(), self.gety())

    @staticmethod
    def getAllMidpoints(location1, location2):
        locationList = []
        lowValue = 0
        highValue = 0

        if location1.getx() != location2.getx():
            if location1.getx() > location2.getx():
                lowValue = location2.getx()
                highValue = location1.getx()
            else:
                lowValue = location1.getx() + 1
                highValue = location2.getx() + 1
            for i in range(lowValue, highValue):
                locationList.append(Location(i, location1.gety()))
        elif location1.gety() != location2.gety():
            if location1.gety() >= location2.gety():
                lowValue = location2.gety()
                highValue = location1.gety()
            else:
                lowValue = location1.gety() + 1
                highValue = location2.gety() + 1
            for i in range(lowValue, highValue):
                locationList.append(Location(location1.getx(), i))
        return locationList

    x = property(getx, setx, None, 'X Coordinate')
    y = property(gety, sety, None, 'Y Coordinate')
