package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Lobby](https://adventofcode.com/2025/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day03")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf { bank ->
        findLargestJoltage(bank, 2)
    }

    fun part2(input: List<String>) = input.sumOf { bank ->
        findLargestJoltage(bank, 12)
    }

    private fun findLargestJoltage(bank: String, numberOfBatteries: Int): Long {
        var index = -1
        var remainingBatteries = numberOfBatteries
        return buildString {
            while (length < numberOfBatteries) {
                var max = 0
                for (i in (index + 1)..(bank.length - remainingBatteries)) {
                    val current = bank[i].digitToInt()
                    if (current > max) {
                        max = current
                        index = i
                    }
                }
                append(max)
                remainingBatteries--
            }
        }.toLong()
    }
}
