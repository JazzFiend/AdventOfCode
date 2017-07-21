import sys

class Screen():
  # Constructor
  def __init__(self, height, width):
    self.display = [['.' for x in range(width)] for y in range(height)]


  # Public Methods
  def displayScreen(self):
    for line in self.display:
      for pixel in line:
        sys.stdout.write(pixel)
      sys.stdout.write('\n')
    sys.stdout.flush()
