package ru.timakden.adventofcode.year2015.day17

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf(20, 15, 10, 5, 5)
        val containers = getContainers(input, 25)
        assertEquals(4, solve(containers, false))
        assertEquals(3, solve(containers, true))
    }
}
