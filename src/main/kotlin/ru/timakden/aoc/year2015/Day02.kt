package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day02 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val dimensions = mutableListOf<Int>()
            it.split('x').mapTo(dimensions) { s -> s.toInt() }.sort()

            2 * dimensions[0] + 2 * dimensions[1] + dimensions[0] * dimensions[1] * dimensions[2]
        }
    }
}
