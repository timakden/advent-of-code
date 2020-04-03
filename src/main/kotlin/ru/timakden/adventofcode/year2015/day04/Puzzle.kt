package ru.timakden.adventofcode.year2015.day04

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.md5Hex
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: String, part: Constants.Part): Long {
    var count = 0L
    var encoded: String

    while (true) {
        encoded = md5Hex(input + count.toString())
        if (encoded.startsWith(if (part == PART_TWO) "000000" else "00000")) break
        count++
    }

    return count
}
