package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 8: Matchsticks](https://adventofcode.com/2015/day/8).
 */
object Day08 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day08")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf {
        it.length - (it.replace("\\\\\\W|\\\\x\\w{2}".toRegex(), "*").length - 2)
    }

    fun part2(input: List<String>) = input.sumOf {
        it.replace("^\"|\"$".toRegex(), "***")
            .replace("\\\\{2}|\\\\\"".toRegex(), "****")
            .replace("\\\\x".toRegex(), "***").length - it.length
    }
}
