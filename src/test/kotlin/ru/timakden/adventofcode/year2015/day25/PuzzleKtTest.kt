package ru.timakden.adventofcode.year2015.day25

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun testGenerateNextCode() {
        val codes = longArrayOf(31916031L, 18749137L, 16080970L, 21629792L, 17289845L, 24592653L, 8057251L,
                16929656L, 30943339L, 77061L, 32451966L, 1601130L, 7726640L, 10071777L, 33071741L, 17552253L,
                21345942L, 7981243L, 15514188L, 33511524L)

        var code = 20151125L
        codes.forEach {
            code = generateNextCode(code)
            assertEquals(it, code)
        }
    }

    @Test
    fun testSolve() {
        var input = Pair(6, 6)
        assertEquals(27995004L, solve(input))

        input = Pair(2 ,4)
        assertEquals(7726640L, solve(input))
    }
}
