class Triangle():
    # Constructor
    def __init__(self, side1, side2, side3):
        self._side1 = side1
        self._side2 = side2
        self._side3 = side3

    # Returns true if any two sides are larger than the third side.
    def isValid(self):
        if(self._side1 + self._side2 > self._side3 and
           self._side2 + self._side3 > self._side1 and
           self._side1 + self._side3 > self._side2):
           return True
        else:
            return False
