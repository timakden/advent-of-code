package ru.timakden.adventofcode.year2015.day19

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val replacements = listOf("H => HO", "H => OH", "O => HH")
        val molecule = "HOH"
        assertEquals(4, solvePartOne(replacements, molecule))
    }

    @Test
    fun solvePartTwo() {
        val replacements = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
        val molecule = "HOHOHO"
        assertEquals(6, solvePartTwo(replacements, molecule))
    }
}
