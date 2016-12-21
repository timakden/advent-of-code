package ru.timakden.adventofcode.year2015.day02

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(solvePartOne(listOf("2x3x4")), 58)
        assertEquals(solvePartOne(listOf("1x1x10")), 43)
    }

    @Test
    fun solvePartTwo() {
        assertEquals(solvePartTwo(listOf("2x3x4")), 34)
        assertEquals(solvePartTwo(listOf("1x1x10")), 14)
    }

}
