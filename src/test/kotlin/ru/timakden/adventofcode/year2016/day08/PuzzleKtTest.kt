package ru.timakden.adventofcode.year2016.day08

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val screen = createScreen(7, 3)
        val input = listOf("rect 3x2", "rotate column x=1 by 1", "rotate row y=0 by 4", "rotate column x=1 by 1")
        assertEquals(6, solvePartOne(screen, input))
    }
}
