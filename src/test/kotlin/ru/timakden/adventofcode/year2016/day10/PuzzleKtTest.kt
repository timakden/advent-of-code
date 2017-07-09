package ru.timakden.adventofcode.year2016.day10

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf("value 5 goes to bot 2", "bot 2 gives low to bot 1 and high to bot 0",
                "value 3 goes to bot 1", "bot 1 gives low to output 1 and high to bot 0",
                "bot 0 gives low to output 2 and high to output 0", "value 2 goes to bot 2").toMutableList()
        val valuesToCompare = listOf(2, 5)
        assertEquals(2, solve(input, valuesToCompare, false))
    }
}
