package ru.timakden.adventofcode.year2016.day06

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        val input = listOf("eedadn", "drvtee", "eandsr", "raavrd", "atevrs", "tsrnev", "sdttsa", "rasrtv", "nssdts",
                "ntnada", "svetve", "tesnvt", "vntsnd", "vrdear", "dvrsen", "enarar")
        assertEquals("easter", solve(input, false))
        assertEquals("advent", solve(input, true))
    }
}
