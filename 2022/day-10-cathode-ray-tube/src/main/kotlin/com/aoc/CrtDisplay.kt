package com.aoc

class CrtDisplay {
  companion object {
    fun generateDisplay(registerValues: List<Int>, height: Int, width: Int): List<String> {
      val display = initializeDisplay(height, width)
      var beamTarget = Pair(0, 0)

      registerValues.forEach { register: Int ->
        if (isWithinSprite(register, beamTarget.second, width)) {
          turnOnPixel(display, beamTarget)
        }
        beamTarget = incrementBeamTarget(beamTarget, width)
      }
      return display
    }

    private fun initializeDisplay(height: Int, width: Int): ArrayList<String> {
      val display = ArrayList<String>()
      for (i in IntRange(0, height - 1)) {
        display.add("")
        for (j in IntRange(0, width - 1)) {
          display[i] = display[i] + "."
        }
      }
      return display
    }

    private fun isWithinSprite(register: Int, centerPixel: Int, maxWidth: Int): Boolean {
      val spritePixels = HashSet<Int>()
      spritePixels.add(centerPixel)

      if (centerPixel > 0) {
        spritePixels.add(centerPixel - 1)
      }
      if (centerPixel < maxWidth - 1) {
        spritePixels.add(centerPixel + 1)
      }
      return spritePixels.contains(register)
    }

    private fun turnOnPixel(
      display: ArrayList<String>,
      beamTarget: Pair<Int, Int>
    ): ArrayList<String> {
      val lineAsCharArray = display[beamTarget.first].toCharArray()
      lineAsCharArray[beamTarget.second] = '#'
      display[beamTarget.first] = String(lineAsCharArray)
      return display
    }

    private fun incrementBeamTarget(beamTarget: Pair<Int, Int>, maxWidth: Int): Pair<Int, Int> {
      return if (beamTarget.second < maxWidth - 1) {
        Pair(beamTarget.first, beamTarget.second + 1)
      } else {
        Pair(beamTarget.first + 1, 0)
      }
    }
  }
}
