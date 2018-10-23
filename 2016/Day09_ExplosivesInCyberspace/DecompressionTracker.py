class DecompressionTracker(object):
  def __init__(self, stopPoint, duplicates):
    self._stopPoint = stopPoint
    self._duplicates = duplicates
    self._count = 0

  def incrementCount(self):
    self._count += 1

  def addToCount(self, value):
    self._count += value

  def calculateTotalLength(self):
    return self._duplicates * self._count

  def atStopPoint(self, location):
   return location == self._stopPoint