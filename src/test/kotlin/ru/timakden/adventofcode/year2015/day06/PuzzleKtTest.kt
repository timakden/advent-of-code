package ru.timakden.adventofcode.year2015.day06

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(1000000, solvePartOne(listOf("turn on 0,0 through 999,999")))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(1, solvePartTwo(listOf("turn on 0,0 through 0,0")))
        assertEquals(2000000, solvePartTwo(listOf("toggle 0,0 through 999,999")))
    }
}
