package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 2: Gift Shop](https://adventofcode.com/2025/day/2).
 */
object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val regex = "^(.+)\\1$".toRegex()
        return input.single().split(',').sumOf { range ->
            LongRange(range.substringBefore('-').toLong(), range.substringAfter('-').toLong()).sumOf {
                if (it.toString().matches(regex)) it else 0
            }
        }
    }

    fun part2(input: List<String>): Long {
        val regex = "^(.+?)\\1+$".toRegex()
        return input.single().split(',').sumOf { range ->
            LongRange(range.substringBefore('-').toLong(), range.substringAfter('-').toLong()).sumOf {
                if (it.toString().matches(regex)) it else 0
            }
        }
    }
}
