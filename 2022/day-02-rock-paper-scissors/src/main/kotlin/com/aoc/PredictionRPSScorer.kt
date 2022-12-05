package com.aoc

class PredictionRPSScorer : RPSScorer() {
    override fun scoreGame(player: Char, opponent: Char): Int {
        when (player) {
            'X' -> return 0
            'Y' -> return 3
            'Z' -> return 6
        }
        return -1
    }

    override fun scoreChoice(player: Char, opponent: Char): Int {
        if(player == 'X' && opponent == 'B' ||
           player == 'Y' && opponent == 'A' ||
           player == 'Z' && opponent == 'C') {
            return 1
        } else if(player == 'X' && opponent == 'C' ||
                  player == 'Y' && opponent == 'B' ||
                  player == 'Z' && opponent == 'A') {
            return 2
        } else if(player == 'X' && opponent == 'A' ||
                  player == 'Y' && opponent == 'C' ||
                  player == 'Z' && opponent == 'B') {
            return 3
        }
        return -1
    }
}
