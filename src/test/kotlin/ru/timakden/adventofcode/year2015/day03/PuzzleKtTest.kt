package ru.timakden.adventofcode.year2015.day03

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(solvePartOne(">"), 2)
        assertEquals(solvePartOne("^>v<"), 4)
        assertEquals(solvePartOne("^v^v^v^v^v"), 2)
    }

    @Test
    fun solvePartTwo() {
        assertEquals(solvePartTwo("^v"), 3)
        assertEquals(solvePartTwo("^>v<"), 3)
        assertEquals(solvePartTwo("^v^v^v^v^v"), 11)
    }
}
