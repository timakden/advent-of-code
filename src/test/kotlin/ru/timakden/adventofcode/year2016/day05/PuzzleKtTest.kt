package ru.timakden.adventofcode.year2016.day05

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = "abc"
        val expected = "18f47a30"
        assertEquals(expected, solvePartOne(input))
    }

    @Test
    fun solvePartTwo() {
        val input = "abc"
        val expected = "05ace8e3"
        assertEquals(expected, solvePartTwo(input))
    }
}
