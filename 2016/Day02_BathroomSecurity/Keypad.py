class Keypad():
    # Constants
    LOCATION_X = 0
    LOCATION_Y = 1

    # Constructor
    def __init__(self, location, keypad, width, height):
        self._keypad = keypad
        self._location = location
        self._width = width
        self._height = height

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
        if((not (currentLocation[self.LOCATION_Y] == 0)) and not self._isLocationNone(currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] - 1)):
            self.locationSet((currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] - 1))

    def moveLocationDown(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_Y] == self.HEIGHT - 1) and not self._isLocationNone(currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] + 1)):
            self.locationSet((currentLocation[self.LOCATION_X], currentLocation[self.LOCATION_Y] + 1))

    def moveLocationLeft(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_X] == 0) and not self._isLocationNone(currentLocation[self.LOCATION_X] - 1, currentLocation[self.LOCATION_Y])):
            self.locationSet((currentLocation[self.LOCATION_X] - 1, currentLocation[self.LOCATION_Y]))

    def moveLocationRight(self):
        currentLocation = self.locationGet()
        if(not (currentLocation[self.LOCATION_X] == self.WIDTH - 1) and not self._isLocationNone(currentLocation[self.LOCATION_X] + 1, currentLocation[self.LOCATION_Y])):
            self.locationSet((currentLocation[self.LOCATION_X] + 1, currentLocation[self.LOCATION_Y]))

    # Local Functions
    def _isLocationNone(self, x, y):
        if(self._keypad[x][y] == None):
            return True
        else:
            return False

    # Display Function
    def displayLocation(self):
        locationToPrint = self.locationGet()
        print locationToPrint
        print self._keypad[locationToPrint[self.LOCATION_X]][locationToPrint[self.LOCATION_Y]]
