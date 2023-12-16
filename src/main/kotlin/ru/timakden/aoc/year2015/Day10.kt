package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 10: Elves Look, Elves Say](https://adventofcode.com/2015/day/10).
 */
object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day10").single()
            println("Part One: ${solve(input, 40).length}")
            println("Part Two: ${solve(input, 50).length}")
        }
    }

    fun solve(s: String, numberOfRuns: Int): String {
        val regex = "(\\d)\\1*".toRegex()

        return generateSequence(s) {
            buildString {
                regex.findAll(it).forEach { matchResult ->
                    append(matchResult.value.length)
                    append(matchResult.value[0])
                }
            }
        }.elementAt(numberOfRuns)
    }
}
