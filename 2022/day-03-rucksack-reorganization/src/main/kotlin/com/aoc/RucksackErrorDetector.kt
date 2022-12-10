package com.aoc

import com.aoc.ItemPrioritizer.Companion.prioritizeItem

private const val LOWER_CASE_OFFSET = 96
private const val UPPER_CASE_OFFSET = 38

class RucksackErrorDetector {
    companion object {
        fun scoreErrors(sacks : List<String> ) : Int {
            var total = 0
            for(sack in sacks) {
                val (compartment1, compartment2) = extractCompartments(sack)
                val errorItem = detectError(compartment1, compartment2)
                total += prioritizeItem(errorItem)
            }
            return total
        }

        private fun extractCompartments(sack: String): Pair<String, String> {
            return Pair(
                sack.substring(IntRange(0, sack.length / 2 - 1)),
                sack.substring(IntRange(sack.length / 2, sack.length - 1))
            )
        }

        private fun detectError(compartment1: String, compartment2: String): Char {
            for(item in compartment1.toCharArray()) {
                if (compartment2.contains(item)) {
                    return item
                }
            }
            throw Error("No duplicate")
        }
    }
}