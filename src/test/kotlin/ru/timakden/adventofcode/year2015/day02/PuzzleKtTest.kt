package ru.timakden.adventofcode.year2015.day02

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(58, solvePartOne(listOf("2x3x4")))
        assertEquals(43, solvePartOne(listOf("1x1x10")))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(34, solvePartTwo(listOf("2x3x4")))
        assertEquals(14, solvePartTwo(listOf("1x1x10")))
    }
}
