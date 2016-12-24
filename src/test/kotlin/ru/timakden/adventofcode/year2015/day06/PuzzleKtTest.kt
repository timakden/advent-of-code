package ru.timakden.adventofcode.year2015.day06

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(solvePartOne(listOf("turn on 0,0 through 999,999")), 1000000)
    }

    @Test
    fun solvePartTwo() {
        assertEquals(solvePartTwo(listOf("turn on 0,0 through 0,0")), 1)
        assertEquals(solvePartTwo(listOf("toggle 0,0 through 999,999")), 2000000)
    }

}
