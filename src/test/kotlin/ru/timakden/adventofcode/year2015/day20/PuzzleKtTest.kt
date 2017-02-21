package ru.timakden.adventofcode.year2015.day20

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(1, solve(10, false))
        assertEquals(2, solve(30, false))
        assertEquals(3, solve(40, false))
        assertEquals(4, solve(70, false))
        assertEquals(5, solve(60, false))
        assertEquals(6, solve(120, false))
        assertEquals(7, solve(80, false))
        assertEquals(8, solve(150, false))
        assertEquals(9, solve(130, false))
    }
}
