package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day06 {
    @JvmStatic
    @ExperimentalTime
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
