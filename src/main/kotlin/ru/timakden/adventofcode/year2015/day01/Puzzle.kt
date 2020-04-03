package ru.timakden.adventofcode.year2015.day01

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: String) = input.count { it == '(' } - input.count { it == ')' }

fun solvePartTwo(input: String): Int {
    var floor = 0

    input.forEachIndexed { i, c ->
        when (c) {
            '(' -> floor++
            ')' -> floor--
        }

        if (floor == -1) return i + 1
    }

    return 0
}
