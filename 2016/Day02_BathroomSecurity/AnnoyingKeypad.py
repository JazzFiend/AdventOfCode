import Keypad

class  AnnoyingKeypad(Keypad.Keypad):
    # Constants
    WIDTH = 5
    HEIGHT = 5

    def __init__(self, location = (0, 2)):
        keypad = [[0 for x in range(self.WIDTH)] for y in range(self.HEIGHT)]
        keypad[0][0] = None
        keypad[1][0] = None
        keypad[2][0] = '1'
        keypad[3][0] = None
        keypad[4][0] = None

        keypad[0][1] = None
        keypad[1][1] = '2'
        keypad[2][1] = '3'
        keypad[3][1] = '4'
        keypad[4][1] = None

        keypad[0][2] = '5'
        keypad[1][2] = '6'
        keypad[2][2] = '7'
        keypad[3][2] = '8'
        keypad[4][2] = '9'

        keypad[0][3] = None
        keypad[1][3] = 'A'
        keypad[2][3] = 'B'
        keypad[3][3] = 'C'
        keypad[4][3] = None

        keypad[0][4] = None
        keypad[1][4] = None
        keypad[2][4] = 'D'
        keypad[3][4] = None
        keypad[4][4] = None

        Keypad.Keypad.__init__(self, location, keypad, self.WIDTH, self.HEIGHT)
