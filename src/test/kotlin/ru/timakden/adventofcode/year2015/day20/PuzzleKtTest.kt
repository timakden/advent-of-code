package ru.timakden.adventofcode.year2015.day20

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(solve(10, false), 1)
        assertEquals(solve(30, false), 2)
        assertEquals(solve(40, false), 3)
        assertEquals(solve(70, false), 4)
        assertEquals(solve(60, false), 5)
        assertEquals(solve(120, false), 6)
        assertEquals(solve(80, false), 7)
        assertEquals(solve(150, false), 8)
        assertEquals(solve(130, false), 9)
    }
}
