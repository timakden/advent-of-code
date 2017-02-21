package ru.timakden.adventofcode.year2015.day23

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf("inc a", "jio a, +2", "tpl a", "inc a")
        assertEquals(2, solve(input, false)[0])
    }
}
