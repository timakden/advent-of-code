package ru.timakden.adventofcode.year2016.day07

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = listOf("abba[mnop]qrst", "abcd[bddb]xyyx", "aaaa[qwer]tyui", "ioxxoj[asdfgh]zxcvbn")
        assertEquals(2, solve(input, false))
    }

    @Test
    fun solvePartTwo() {
        val input = listOf("aba[bab]xyz", "xyx[xyx]xyx", "aaa[kek]eke", "zazbz[bzb]cdb")
        assertEquals(3, solve(input, true))
    }
}
