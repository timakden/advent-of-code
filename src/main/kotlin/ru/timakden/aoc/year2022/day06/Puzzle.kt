package ru.timakden.aoc.year2022.day06

import ru.timakden.aoc.util.Constants.Part
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: String, part: Part): Int {
    val distinctCharacters = if (part == PART_ONE) 4 else 14

    input.indices.forEach { i ->
        if (i >= distinctCharacters - 1) {
            if (input.substring((i - (distinctCharacters - 1))..i).toSet().size == distinctCharacters) {
                return i + 1
            }
        }
    }

    return 0
}
