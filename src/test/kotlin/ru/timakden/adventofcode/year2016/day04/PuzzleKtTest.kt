package ru.timakden.adventofcode.year2016.day04

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solvePartOne() {
        val input = listOf("aaaaa-bbb-z-y-x-123[abxyz]", "a-b-c-d-e-f-g-h-987[abcde]", "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]")
        assertEquals(1514, solvePartOne(input))
    }
}
