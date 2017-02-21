package ru.timakden.adventofcode.year2015.day01

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(3, solvePartOne("(()(()("))
        assertEquals(-3, solvePartOne(")())())"))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(1, solvePartTwo(")"))
        assertEquals(5, solvePartTwo("()())"))
    }
}
