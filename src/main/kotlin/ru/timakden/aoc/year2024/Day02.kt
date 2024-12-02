package ru.timakden.aoc.year2024

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

/**
 * [Day 2: Red-Nosed Reports](https://adventofcode.com/2024/day/2).
 */
object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        return input.count { line ->
            val levels = line.split(" ").map { it.toInt() }
            levels.isSafe()
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { line ->
            val levels = line.split(" ").map { it.toInt() }
            levels.isSafeWithoutOneLevel()
        }
    }

    private fun List<Int>.isIncreasing() = zipWithNext { a, b -> a < b }.all { it }

    private fun List<Int>.isDecreasing() = zipWithNext { a, b -> a > b }.all { it }

    private fun List<Int>.hasValidDifference() = zipWithNext { a, b -> abs(a - b) in 1..3 }.all { it }

    private fun List<Int>.isSafe() = (this.isIncreasing() || this.isDecreasing()) && this.hasValidDifference()

    private fun List<Int>.isSafeWithoutOneLevel() = this.isSafe() || (0..this.lastIndex).any { index ->
        filterIndexed { i, _ -> i != index }.isSafe()
    }
}
