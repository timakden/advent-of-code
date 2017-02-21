package ru.timakden.adventofcode.year2015.day03

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(2, solvePartOne(">"))
        assertEquals(4, solvePartOne("^>v<"))
        assertEquals(2, solvePartOne("^v^v^v^v^v"))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(3, solvePartTwo("^v"))
        assertEquals(3, solvePartTwo("^>v<"))
        assertEquals(11, solvePartTwo("^v^v^v^v^v"))
    }
}
