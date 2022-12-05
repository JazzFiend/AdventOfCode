package com.aoc

class BasicRPSScorer : RPSScorer() {
    override fun scoreGame(player: Char, opponent: Char): Int {
        if(player == 'X' && opponent == 'A' ||
           player == 'Y' && opponent == 'B' ||
           player == 'Z' && opponent == 'C') {
            return 3
        } else if(player == 'X' && opponent == 'C' ||
                  player == 'Y' && opponent == 'A' ||
                  player == 'Z' && opponent == 'B') {
            return 6
        }
        return 0
    }

    override fun scoreChoice(player: Char, opponent: Char): Int {
        when (player) {
            'X' -> return 1
            'Y' -> return 2
            'Z' -> return 3
        }
        return 0
    }
}
