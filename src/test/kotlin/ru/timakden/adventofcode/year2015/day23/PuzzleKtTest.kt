package ru.timakden.adventofcode.year2015.day23

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val list = listOf("inc a", "jio a, +2", "tpl a", "inc a")
        assertEquals(solve(list, false)[0], 2)
    }
}
