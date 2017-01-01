package ru.timakden.adventofcode.year2015.day11

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun checkPassword() {
        assertFalse(checkPassword("hijklmmn"))
        assertFalse(checkPassword("abbceffg"))
        assertFalse(checkPassword("abbcegjk"))
        assertTrue(checkPassword("abcdffaa"))
        assertTrue(checkPassword("ghjaabcc"))
    }
}
