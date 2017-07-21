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

WIDTH = 7
HEIGHT = 3
instructionList = []
screen = Screen.Screen(HEIGHT, WIDTH)

for instruction in TEST_INSTRUCTIONS:
  instructionList.append(Instruction.Instruction(instruction))

for instruction in instructionList:
  instruction.execute(screen)



