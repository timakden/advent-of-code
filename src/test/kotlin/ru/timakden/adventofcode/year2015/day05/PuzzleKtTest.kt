package ru.timakden.adventofcode.year2015.day05

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun isNicePartOne() {
        assertEquals(isNicePartOne("ugknbfddgicrmopn"), true)
        assertEquals(isNicePartOne("aaa"), true)
        assertEquals(isNicePartOne("jchzalrnumimnmhp"), false)
        assertEquals(isNicePartOne("haegwjzuvuyypxyu"), false)
        assertEquals(isNicePartOne("dvszwmarrgswjxmb"), false)
    }

    @Test
    fun isNicePartTwo() {
        assertEquals(isNicePartTwo("qjhvhtzxzqqjkmpb"), true)
        assertEquals(isNicePartTwo("xxyxx"), true)
        assertEquals(isNicePartTwo("uurcxstgmygtbstg"), false)
        assertEquals(isNicePartTwo("ieodomkazucvgmuy"), false)
    }
}
