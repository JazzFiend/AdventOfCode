import StandardKeypad
import AnnoyingKeypad

def moveBasedOnChar(keypad, char):
    if(char == 'U'):
        keypad.moveLocationUp()
    elif(char == 'D'):
        keypad.moveLocationDown()
    elif(char == 'L'):
        keypad.moveLocationLeft()
    elif(char == 'R'):
        keypad.moveLocationRight()
    else:
        raise ValueError('Incorrect Move seen. Got: %s' % char)

# Start Here
TEST_INSTRUCTIONS = ["ULL", "RRDDD", "LURDL", "UUUUD"]
f = open('input.txt', 'r')
# location = (1, 1)
location = (0, 2)
password = ''
getFileInput = True;
instructionList = []

while(getFileInput):
    line = f.readline()
    if(not line == ''):
        instructionList.append(line)
    else:
        getFileInput = False

for instruction in instructionList:
    # bathroomKeypad = StandardKeypad.StandardKeypad(location)
    bathroomKeypad = AnnoyingKeypad.AnnoyingKeypad(location)
    for character in instruction:
        if(not character == '\n'):
            moveBasedOnChar(bathroomKeypad, character)
        # bathroomKeypad.displayLocation()
    location = bathroomKeypad.locationGet()
    password += bathroomKeypad.buttonPress()

print "Bathroon Password: %s" % password
