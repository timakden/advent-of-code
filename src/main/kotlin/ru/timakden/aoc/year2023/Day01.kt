package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 1: Trebuchet?!](https://adventofcode.com/2023/day/1).
 */
object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val digits = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        return input.sumOf {
            val first = it.findAnyOf(digits)?.second
            val second = it.findLastAnyOf(digits)?.second
            "$first$second".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val digits = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )
        return input.sumOf {
            val first = it.findAnyOf(digits)?.second?.parseDigit()
            val second = it.findLastAnyOf(digits)?.second?.parseDigit()
            "$first$second".toInt()
        }
    }

    private fun String.parseDigit() = when (this) {
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> this
    }
}
