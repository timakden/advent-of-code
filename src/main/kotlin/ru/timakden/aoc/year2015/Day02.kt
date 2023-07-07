package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf {
        val dimensions = it.split('x').map { s -> s.toInt() }
        val areas = listOf(
            2 * dimensions[0] * dimensions[1],
            2 * dimensions[1] * dimensions[2],
            2 * dimensions[0] * dimensions[2]
        )

        areas.sum() + (areas.minOrNull() ?: 0) / 2
    }

    fun part2(input: List<String>) = input.sumOf {
        val dimensions = it.split('x').map { s -> s.toInt() }.sorted()

        2 * dimensions[0] + 2 * dimensions[1] + dimensions[0] * dimensions[1] * dimensions[2]
    }
}
