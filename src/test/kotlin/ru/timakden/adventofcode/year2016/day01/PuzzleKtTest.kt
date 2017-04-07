package ru.timakden.adventofcode.year2016.day01

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(5, solvePartOne("R2, L3"))
        assertEquals(2, solvePartOne("R2, R2, R2"))
        assertEquals(12, solvePartOne("R5, L5, R5, R3"))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(4, solvePartTwo("R8, R4, R4, R8"))
    }
}
