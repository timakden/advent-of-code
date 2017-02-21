package ru.timakden.adventofcode.year2015.day09

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")
        assertEquals(605, solve(input, false))
        assertEquals(982, solve(input, true))
    }
}
