import sys

class Screen():
  # Constructor
  def __init__(self, height, width):
    self.display = [['.' for x in range(width)] for y in range(height)]
    self.HEIGHT = height
    self.WIDTH = width

  # Public Methods
  def displayScreen(self):
    for line in self.display:
      for pixel in line:
        sys.stdout.write(pixel)
      sys.stdout.write('\n')
    sys.stdout.write('\n')
    sys.stdout.flush()

  def turnOnRange(self, width, height):
    for i in range(0, height):
      for j in range(0, width):
        self.display[i][j] = '#'

  def getHeight(self):
    return self.HEIGHT

  def getWidth(self):
    return self.WIDTH

  def getPixelState(self, x, y):
    return self.display[y][x]

  def setPixel(self, x, y, state):
    if(state == '.' or state == '#'):
      self.display[y][x] = state
    else:
      raise ValueError('Wrong pixel state: %s' % state)

  def getLitPixelCount(self):
    count = 0
    for line in self.display:
      for pixel in line:
        if(pixel == '#'):
          count += 1
    return count
