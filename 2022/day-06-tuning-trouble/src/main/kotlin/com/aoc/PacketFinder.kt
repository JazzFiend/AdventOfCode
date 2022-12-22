package com.aoc

private const val PACKET_HEADER_LENGTH = 4
private const val ERROR_CODE = -1

class PacketFinder {
  companion object {
    fun findFirstPacketLocation(stream: String, packetHeaderLength: Int): Int {
      val streamChars = stream.toCharArray()
      for (i in IntRange(0, streamChars.size - packetHeaderLength)) {
        if (isPacketStart(streamChars, i, packetHeaderLength)
        ) {
          return i + packetHeaderLength
        }
      }
      return ERROR_CODE
    }

    private fun isPacketStart(streamChars: CharArray, i: Int, packetHeaderLength: Int): Boolean {
      val charSet = HashSet<Char>()
      for(j in IntRange(i, i + packetHeaderLength - 1)) {
        if(charSet.contains(streamChars[j])) {
          return false
        } else {
          charSet.add(streamChars[j])
        }
      }
      return true
    }
  }
}
