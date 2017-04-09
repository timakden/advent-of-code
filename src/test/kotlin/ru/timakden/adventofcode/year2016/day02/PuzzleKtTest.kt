package ru.timakden.adventofcode.year2016.day02

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
        assertEquals("1985", solve(input, false))
    }

    @Test
    fun solvePartTwo() {
        val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
        assertEquals("5DB3", solve(input, true))
    }
}
