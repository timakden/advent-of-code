package ru.timakden.adventofcode.year2015.day01

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(solvePartOne("(()(()("), 3)
        assertEquals(solvePartOne(")())())"), -3)
    }

    @Test
    fun solvePartTwo() {
        assertEquals(solvePartTwo(")"), 1)
        assertEquals(solvePartTwo("()())"), 5)
    }
}
