package ru.timakden.adventofcode.year2015.day09

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")
        assertEquals(solve(input, false), 605)
        assertEquals(solve(input, true), 982)
    }
}
