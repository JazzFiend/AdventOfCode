package com.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertEquals

class PacketFinderTest {
  @Test
  fun emptyBuffer() {
    assertEquals(-1, PacketFinder.findFirstPacketLocation("", 4))
  }

  @Test
  fun notEnoughCharacters() {
    assertEquals(-1, PacketFinder.findFirstPacketLocation("hso", 4))
  }

  @Test
  fun earliestPossibleLocation() {
    assertEquals(4, PacketFinder.findFirstPacketLocation("nhujigbfer", 4))
  }

  @Test
  fun packetStartsJustAfterEarliest() {
    assertEquals(5, PacketFinder.findFirstPacketLocation("rbhrvwguihbwiuh", 4))
  }

  @Test
  fun minCharsNoStart() {
    assertEquals(-1, PacketFinder.findFirstPacketLocation("btbf", 4))
  }

  @Test
  fun repeatedCharIsNotFirst() {
    assertEquals(-1, PacketFinder.findFirstPacketLocation("nuii", 4))
  }

  @Test
  fun minCharsWithStart() {
    assertEquals(4, PacketFinder.findFirstPacketLocation("rtew", 4))
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("buffers")
  fun acceptanceTest(buffer: String, firstPacketLocation: Int) {
    assertEquals(firstPacketLocation, PacketFinder.findFirstPacketLocation(buffer, 4))
  }

  @Test
  fun puzzleOne() {
    var stream = ""
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { stream = it }
    assertEquals(1833, PacketFinder.findFirstPacketLocation(stream, 4))
  }

  @Test
  fun packetLengthAsArgument() {
    assertEquals(7, PacketFinder.findFirstPacketLocation("vnenvuienviueinu", 5))
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("variableLengthBuffers")
  fun acceptanceTestNewHeaderLength(buffer: String, firstPacketLocation: Int) {
    assertEquals(firstPacketLocation, PacketFinder.findFirstPacketLocation(buffer, 14))
  }

  @Test
  fun puzzleTwo() {
    var stream = ""
    File("./src/test/kotlin/com/aoc/input.txt").forEachLine { stream = it }
    assertEquals(3425, PacketFinder.findFirstPacketLocation(stream, 14))
  }

  private companion object {
    @JvmStatic
    fun buffers(): Stream<Arguments> = Stream.of(
      Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb!", 7),
      Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz!", 5),
      Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 6),
      Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
      Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
    )

    @JvmStatic
    fun variableLengthBuffers(): Stream<Arguments> = Stream.of(
      Arguments.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb!", 19),
      Arguments.of("bvwbjplbgvbhsrlpgdmjqwftvncz!", 23),
      Arguments.of("nppdvjthqldpwncqszvftbrmjlhg", 23),
      Arguments.of("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
      Arguments.of("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26)
    )
  }
}