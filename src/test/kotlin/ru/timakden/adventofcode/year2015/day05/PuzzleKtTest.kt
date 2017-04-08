package ru.timakden.adventofcode.year2015.day05

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = listOf("ugknbfddgicrmopn", "aaa", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb")
        assertEquals(2, solvePartOne(input))
    }

    @Test
    fun solvePartTwo() {
        val input = listOf("qjhvhtzxzqqjkmpb", "xxyxx", "uurcxstgmygtbstg", "ieodomkazucvgmuy")
        assertEquals(2, solvePartTwo(input))
    }
}
