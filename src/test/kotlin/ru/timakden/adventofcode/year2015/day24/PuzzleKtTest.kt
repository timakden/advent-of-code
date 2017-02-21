package ru.timakden.adventofcode.year2015.day24

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)
        assertEquals(solve(input, false), 99)
        assertEquals(solve(input, true), 44)
    }
}
