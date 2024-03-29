package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 6: Signals and Noise](https://adventofcode.com/2016/day/6).
 */
object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = buildString {
        input.first().indices.forEach { i ->
            input.fold("") { acc, s -> acc + s[i] }
                .associateWith { c -> input.fold("") { acc, s -> acc + s[i] }.count { it == c } }
                .maxByOrNull { it.value }?.let { append(it.key) }
        }
    }

    fun part2(input: List<String>) = buildString {
        input.first().indices.forEach { i ->
            input.fold("") { acc, s -> acc + s[i] }
                .associateWith { c -> input.fold("") { acc, s -> acc + s[i] }.count { it == c } }
                .minByOrNull { it.value }?.let { append(it.key) }
        }
    }
}
