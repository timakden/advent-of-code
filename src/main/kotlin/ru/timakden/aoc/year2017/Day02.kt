package ru.timakden.aoc.year2017

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 2: Corruption Checksum](https://adventofcode.com/2017/day/2).
 */
object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2017/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf { row ->
        val values = row.split("\\s+".toRegex()).map { it.toInt() }
        values.max() - values.min()
    }

    fun part2(input: List<String>) = input.sumOf { row ->
        val values = row.split("\\s+".toRegex()).map { it.toInt() }
        for (i in values.indices) {
            for (j in values.indices - i) {
                if (values[i] % values[j] == 0) {
                    return@sumOf values[i] / values[j]
                }
            }
        }
        0
    }
}
