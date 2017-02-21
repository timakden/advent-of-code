package ru.timakden.adventofcode.year2015.day12

import org.junit.Assert.assertEquals
import org.junit.Test

class PuzzleKtTest {
    @Test
    fun solve() {
        assertEquals(6, solve("[1,2,3]", false))
        assertEquals(6, solve("""{"a":2,"b":4}""", false))
        assertEquals(3, solve("[[[3]]]", false))
        assertEquals(3, solve("""{"a":{"b":4},"c":-1}""", false))
        assertEquals(0, solve("""{"a":[-1,1]}""", false))
        assertEquals(0, solve("""[-1,{"a":1}]""", false))
        assertEquals(0, solve("[]", false))
        assertEquals(0, solve("{}", false))
        assertEquals(6, solve("[1,2,3]", true))
        assertEquals(4, solve("""[1,{"c":"red","b":2},3]""", true))
        assertEquals(0, solve("""{"d":"red","e":[1,2,3,4],"f":5}""", true))
        assertEquals(6, solve("""[1,"red",5]""", true))
    }
}
