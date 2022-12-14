package com.aoc

class PairDecoder {
    companion object {
        fun decodePair(s: String) : Pair<Assignment, Assignment> {
            val twoStrings = s.split(',')
            val firstAssignments = twoStrings[0].split('-')
            val secondAssignments = twoStrings[1].split('-')
            return Pair(
                Assignment(firstAssignments[0].toInt(), firstAssignments[1].toInt()),
                Assignment(secondAssignments[0].toInt(), secondAssignments[1].toInt())
            )
        }
    }
}