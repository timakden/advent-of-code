package ru.timakden.adventofcode.year2016.day09

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(6, solvePartOne("ADVENT"))
        assertEquals(7, solvePartOne("A(1x5)BC"))
        assertEquals(9, solvePartOne("(3x3)XYZ"))
        assertEquals(11, solvePartOne("A(2x2)BCD(2x2)EFG"))
        assertEquals(6, solvePartOne("(6x1)(1x3)A"))
        assertEquals(18, solvePartOne("X(8x2)(3x3)ABCY"))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(6, solvePartTwo("ADVENT"))
        assertEquals(9, solvePartTwo("(3x3)XYZ"))
        assertEquals(20, solvePartTwo("X(8x2)(3x3)ABCY"))
        assertEquals(241920, solvePartTwo("(27x12)(20x12)(13x14)(7x10)(1x12)A"))
        assertEquals(445, solvePartTwo("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"))
    }
}
