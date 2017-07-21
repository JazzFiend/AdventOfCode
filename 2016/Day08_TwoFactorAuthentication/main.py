import sys

import Instruction
import Screen

# Start Here

TEST_INSTRUCTIONS = [
  'rect 3x2',
  'rotate column x=1 by 1',
  'rotate row x=0 by 4',
  'rotate column x=1 by 1'
]

WIDTH = 50
HEIGHT = 6
instructionList = []
screen = Screen.Screen(HEIGHT, WIDTH)
getFileInput = True

f = open('input.txt', 'r')
while(getFileInput):
  line = f.readline()
  if(not line == ''):
    line = line[:-1]
    instructionList.append(Instruction.Instruction(line))
  else:
    getFileInput = False

#for instruction in TEST_INSTRUCTIONS:
#  instructionList.append(Instruction.Instruction(instruction))

for instruction in instructionList:
  instruction.execute(screen)

print screen.getLitPixelCount()
print screen.displayScreen()
