package ru.timakden.adventofcode.year2015.day12

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(solve("[1,2,3]", false), 6)
        assertEquals(solve("""{"a":2,"b":4}""", false), 6)
        assertEquals(solve("[[[3]]]", false), 3)
        assertEquals(solve("""{"a":{"b":4},"c":-1}""", false), 3)
        assertEquals(solve("""{"a":[-1,1]}""", false), 0)
        assertEquals(solve("""[-1,{"a":1}]""", false), 0)
        assertEquals(solve("[]", false), 0)
        assertEquals(solve("{}", false), 0)
        assertEquals(solve("[1,2,3]", true), 6)
        assertEquals(solve("""[1,{"c":"red","b":2},3]""", true), 4)
        assertEquals(solve("""{"d":"red","e":[1,2,3,4],"f":5}""", true), 0)
        assertEquals(solve("""[1,"red",5]""", true), 6)
    }
}
