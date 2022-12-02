package com.AoC.`2022`

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class CalorieCounterTest {
    @Test
    fun emptyList() {
        val calorieList = ArrayList<String>()
        assertEquals(0, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun oneEntry() {
        val calorieList = listOf("5")
        assertEquals(5, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun oneElfMultipleEntries() {
        val calorieList = listOf("5", "10", "20")
        assertEquals(35, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun twoElves() {
        val calorieList = listOf("1", "2", "3", "", "22")
        assertEquals(22, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun acceptanceTest() {
        val calorieList = listOf(
            "1000", "2000", "3000", "",
            "4000", "",
            "5000", "6000", "",
            "7000", "8000", "9000", "",
            "10000")
        assertEquals(24000, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun puzzleOne() {
        val calorieList = ArrayList<String>()
        File("./src/test/kotlin/com/AoC/2022/input.txt").forEachLine { calorieList.add(it) }
        assertEquals(67027, CalorieCounter.getMostCaloriesCarried(calorieList));
    }

    @Test
    fun fourElvesTopThree() {
        val calorieList = listOf("1", "", "2", "", "3", "", "4")
        assertEquals(9, CalorieCounter.getTopThreeMostCalories(calorieList));
    }

    @Test
    fun acceptanceTestTopThree() {
        val calorieList = listOf(
            "1000", "2000", "3000", "",
            "4000", "",
            "5000", "6000", "",
            "7000", "8000", "9000", "",
            "10000")
        assertEquals(45000, CalorieCounter.getTopThreeMostCalories(calorieList));
    }

    @Test
    fun puzzleTwo() {
        val calorieList = ArrayList<String>()
        File("./src/test/kotlin/com/AoC/2022/input.txt").forEachLine { calorieList.add(it) }
        assertEquals(197291, CalorieCounter.getTopThreeMostCalories(calorieList));
    }
}