package com.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertEquals

class BadgeFinderTest {
    @Test
    fun noSacks() {
        assertEquals(0, BadgeFinder.scoreBadges(ArrayList()))
    }

    @ParameterizedTest(name = "Sack Contents: \"{0}\", Priority: \"{1}\"")
    @MethodSource("singleItemArgs")
    fun oneItemPerSackOneGroup(sacks: List<String>, expectedScore: Int) {
        assertEquals(expectedScore, BadgeFinder.scoreBadges(sacks))
    }

    private companion object {
        @JvmStatic
        fun singleItemArgs(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf("H", "H", "H"), 34),
            Arguments.of(listOf("T", "T", "T"), 46)
        )
    }

    @Test
    fun manyItemsPerSackOneGroup() {
        val sacks = listOf("uYVPf", "YtuXeb", "eJXdY")
        assertEquals(51, BadgeFinder.scoreBadges(sacks))
    }

    @Test
    fun manyItemsManyGroups() {
        val sacks = listOf("FxTON", "vTroO", "bTrrfz", "FjPLi", "zeDjc", "pykmj")
        assertEquals((46 + 10), BadgeFinder.scoreBadges(sacks))
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
        assertEquals(70, BadgeFinder.scoreBadges(sacks))
    }

    @Test
    fun puzzleTwo() {
        val sacks = ArrayList<String>()
        File("./src/test/kotlin/com/aoc/input.txt").forEachLine { sacks.add(it) }
        assertEquals(2760, BadgeFinder.scoreBadges(sacks))
    }
}