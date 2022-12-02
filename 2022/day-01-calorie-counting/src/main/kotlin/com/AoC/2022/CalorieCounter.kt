package com.AoC.`2022`

class CalorieCounter {
  companion object {
     fun getMostCaloriesCarried(calorieList: List<String> ) : Int {
         val elves = constructElvesList(calorieList)
         return elves.max()
     }

      fun getTopThreeMostCalories(calorieList: List<String>): Int {
          val elves = constructElvesList(calorieList)
          return sumTopThree(elves)
      }

      private fun sumTopThree(elves: MutableList<Int>): Int {
          var total = 0
          for (i in 1..3) {
              total += elves.max()
              elves.remove(elves.max())
          }
          return total
      }

      private fun constructElvesList(calorieList: List<String>): MutableList<Int> {
          val elves = mutableListOf(0)
          for (calorieValueString in calorieList) {
              val calorieValue = calorieValueString.toIntOrNull()
              if (calorieValue == null) {
                  elves.add(0)
              } else {
                  elves[elves.size - 1] += calorieValue
              }
          }
          return elves
      }
  }
}

