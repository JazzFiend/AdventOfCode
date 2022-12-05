package com.aoc

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertEquals

class BasicRPSScorerTest {
    private val rps = BasicRPSScorer()

    @Test
    fun noGames() {
        assertEquals(0, rps.calculateScore(ArrayList()))
    }

    @ParameterizedTest(name = "game: \"{0}\", score: \"{1}\"")
    @MethodSource("gameArguments")
    fun `Single game`(games: List<String>, expectedScore: Int) {
        assertEquals(expectedScore, rps.calculateScore(games))
    }

    private companion object {
        @JvmStatic
        fun gameArguments(): Stream<Arguments> = Stream.of(
            Arguments.of(listOf("A Z"), 3), // Rock beats scissors
            Arguments.of(listOf("B Z"), 9), // Scissors beat paper
            Arguments.of(listOf("B X"), 1), // Paper beats rock
            Arguments.of(listOf("C Y"), 2), // Player loses
            Arguments.of(listOf("A X"), 4), // Player ties
            Arguments.of(listOf("A Y"), 8), // Player wins
        )
    }

    @Test
    fun multipleGames() {
        val games = listOf("A X", "C X", "C Y")
        assertEquals(13, rps.calculateScore(games))
    }

    @Test
    fun acceptanceTest() {
        val games = listOf("A Y", "B X", "C Z")
        assertEquals(15, rps.calculateScore(games))
    }

    @Test
    fun puzzleOne() {
        val games = ArrayList<String>()
        File("./src/test/kotlin/com/aoc/input.txt").forEachLine { games.add(it) }
        assertEquals(10816, rps.calculateScore(games))
    }
}
