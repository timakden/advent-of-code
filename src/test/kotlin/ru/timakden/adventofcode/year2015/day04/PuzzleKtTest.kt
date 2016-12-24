package ru.timakden.adventofcode.year2015.day04

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(solve("abcdef", 27), 609043)
        assertEquals(solve("pqrstuv", 27), 1048970)
    }
}
