package com.aoc

abstract class RPSScorer {
    fun calculateScore(games : List<String>) : Int {
        var total = 0
        for (game in games) {
            val opponent = game[0]
            val player = game[2]
            total += (scoreGame(player, opponent) + scoreChoice(player, opponent))
        }
        return total
    }

    abstract fun scoreGame(player: Char, opponent: Char): Int

    abstract fun scoreChoice(player: Char, opponent: Char): Int
}
