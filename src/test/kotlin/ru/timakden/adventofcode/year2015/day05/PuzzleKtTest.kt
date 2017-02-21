package ru.timakden.adventofcode.year2015.day05

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun isNicePartOne() {
        assertTrue(isNicePartOne("ugknbfddgicrmopn"))
        assertTrue(isNicePartOne("aaa"))
        assertFalse(isNicePartOne("jchzalrnumimnmhp"))
        assertFalse(isNicePartOne("haegwjzuvuyypxyu"))
        assertFalse(isNicePartOne("dvszwmarrgswjxmb"))
    }

    @Test
    fun isNicePartTwo() {
        assertTrue(isNicePartTwo("qjhvhtzxzqqjkmpb"))
        assertTrue(isNicePartTwo("xxyxx"))
        assertFalse(isNicePartTwo("uurcxstgmygtbstg"))
        assertFalse(isNicePartTwo("ieodomkazucvgmuy"))
    }
}
