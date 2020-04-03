package ru.timakden.adventofcode.year2015.day20

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: Int, part: Constants.Part): Int {
    val presentsPerElf = if (part == PART_TWO) 11 else 10

    val houses = IntArray(1000000)

    (1..houses.lastIndex).forEach {
        var count = 0
        var i = it
        while (i <= houses.lastIndex) {
            houses[i] += presentsPerElf * it
            if (part == PART_TWO && ++count == 50) break

            i += it
        }
    }

    return houses.indexOfFirst { it >= input }
}
