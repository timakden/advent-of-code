package ru.timakden.adventofcode.year2015.day02

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>): Int {
    return input.sumOf {
        val dimensions = mutableListOf<Int>()
        it.split("x").mapTo(dimensions) { s -> s.toInt() }
        val areas = listOf(
            2 * dimensions[0] * dimensions[1],
            2 * dimensions[1] * dimensions[2],
            2 * dimensions[0] * dimensions[2]
        )

        areas.sum() + (areas.minOrNull() ?: 0) / 2
    }
}

fun solvePartTwo(input: List<String>): Int {
    return input.sumOf {
        val dimensions = mutableListOf<Int>()
        it.split('x').mapTo(dimensions) { s -> s.toInt() }.sort()

        2 * dimensions[0] + 2 * dimensions[1] + dimensions[0] * dimensions[1] * dimensions[2]
    }
}
