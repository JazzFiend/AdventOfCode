import Keypad

class StandardKeypad(Keypad.Keypad):
    # Constants
    WIDTH = 3
    HEIGHT = 3

    def __init__(self, location = (1, 1)):
        keypad = [[0 for x in range(self.WIDTH)] for y in range(self.HEIGHT)]
        keypad[0][0] = '1'
        keypad[1][0] = '2'
        keypad[2][0] = '3'
        keypad[0][1] = '4'
        keypad[1][1] = '5'
        keypad[2][1] = '6'
        keypad[0][2] = '7'
        keypad[1][2] = '8'
        keypad[2][2] = '9'
        Keypad.Keypad.__init__(self, location, keypad, self.WIDTH, self.HEIGHT)
