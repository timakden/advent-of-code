package ru.timakden.adventofcode.year2015.day08

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        assertEquals(solvePartOne(listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")), 12)
    }

    @Test
    fun solvePartTwo() {
        assertEquals(solvePartTwo(listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")), 19)
    }
}
