package com.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertEquals

class RucksackErrorDetectorTest {
    @Test
    fun emptyList() {
        assertEquals(0, RucksackErrorDetector.scoreErrors(ArrayList()))
    }

    @ParameterizedTest(name = "Sack Contents: \"{0}\", Priority: \"{1}\"")
    @MethodSource("singleItemArgs")
    fun oneItemPerCompartment(sacks: List<String>, expectedScore: Int) {
        assertEquals(expectedScore, RucksackErrorDetector.scoreErrors(sacks))
    }

    private companion object {
        @JvmStatic
        fun singleItemArgs(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf("pp"), 16),
            Arguments.of(listOf("jj"), 10),
            Arguments.of(listOf("QQ"), 43)
        )
    }

    @Test
    fun multipleItemsPerCompartment() {
        val sacks = listOf("hRNR")
        assertEquals(44, RucksackErrorDetector.scoreErrors(sacks))
    }

    @Test
    fun multipleRucks() {
        val sacks = listOf("vSZZgP", "DCaD", "WiDMiA")
        assertEquals((52 + 30 + 9), RucksackErrorDetector.scoreErrors(sacks))
    }

    @Test
    fun acceptanceTest() {
        val sacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
        assertEquals(157, RucksackErrorDetector.scoreErrors(sacks))
    }

    @Test
    fun puzzleOne() {
        val sacks = ArrayList<String>()
        File("./src/test/kotlin/com/aoc/input.txt").forEachLine { sacks.add(it) }
        assertEquals(7878, RucksackErrorDetector.scoreErrors(sacks))
    }
}