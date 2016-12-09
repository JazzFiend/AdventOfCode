class Keypad():
    # Constants
    WIDTH = 3
    HEIGHT = 3
    LOCATION_X = 0
    LOCATION_Y = 1

    # Constructor
    def __init__(self, location = (1, 1)):
        self._keypad = [[0 for x in range(self.WIDTH)] for y in range(self.HEIGHT)]
        self._keypad[0][0] = '1'
        self._keypad[1][0] = '2'
        self._keypad[2][0] = '3'
        self._keypad[0][1] = '4'
        self._keypad[1][1] = '5'
        self._keypad[2][1] = '6'
        self._keypad[0][2] = '7'
        self._keypad[1][2] = '8'
        self._keypad[2][2] = '9'
        self._location = location

    # Location Property - 2 Integer Tuple
    def locationGet(self):
        return self._location
    def locationSet(self, value):
        self._location = value
    location = property(locationGet, locationSet, None, 'Current Location on the Keypad - Two Integer Tuple')

    # Get Keypad value at current location
    def buttonPress(self):
        locationToReturn = self.locationGet()
        return self._keypad[locationToReturn[self.LOCATION_X]][locationToReturn[self.LOCATION_Y]]

    # Movement Functions
    def moveLocationUp(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_Y] == 0)):
            self.locationSet((currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] - 1))

    def moveLocationDown(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_Y] == self.HEIGHT - 1)):
            self.locationSet((currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] + 1))

    def moveLocationLeft(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_X] == 0)):
            self.locationSet((currentLocation[self.LOCATION_X] - 1, currentLocation[self.LOCATION_Y]))

    def moveLocationRight(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_X] == self.WIDTH - 1)):
            self.locationSet((currentLocation[self.LOCATION_X] + 1, currentLocation[self.LOCATION_Y]))

    # Display Function
    def displayLocation(self):
        locationToPrint = self.locationGet()
        # print locationToPrint
        print self._keypad[locationToPrint[self.LOCATION_X]][locationToPrint[self.LOCATION_Y]]
