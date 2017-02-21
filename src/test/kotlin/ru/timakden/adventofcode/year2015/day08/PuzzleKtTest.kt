package ru.timakden.adventofcode.year2015.day08

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    val input = listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")

    @Test
    fun solvePartOne() {
        assertEquals(12, solvePartOne(input))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(19, solvePartTwo(input))
    }
}
