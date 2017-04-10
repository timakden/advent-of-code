package ru.timakden.adventofcode.year2016.day03

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = listOf("5 10 25", "3 4 5", "12 10 17")
        assertEquals(2, solvePartOne(input))
    }

    @Test
    fun solvePartTwo() {
        val input = listOf("101 301 501", "102 302 502", "103 303 503", "201 401 601", "202 402 602", "203 403 603")
        assertEquals(6, solvePartTwo(input))
    }
}
