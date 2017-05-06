package ru.timakden.adventofcode.year2015.day04

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(609043, solve("abcdef", false))
        assertEquals(1048970, solve("pqrstuv", false))
    }
}
