package ru.timakden.adventofcode.year2015.day01

import org.junit.Assert
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        Assert.assertEquals(solvePartOne(")())())"), -3)
    }

    @Test
    fun solvePartTwo() {
        Assert.assertEquals(solvePartTwo("()())"), 5)
    }
}
