package ru.timakden.aoc.year2017

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 1: Inverse Captcha](https://adventofcode.com/2017/day/1).
 */
object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2017/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf { sequence ->
        var sum = 0
        for (i in sequence.indices) {
            val j = if (i == sequence.lastIndex) 0 else i + 1
            if (sequence[i] == sequence[j]) sum += sequence[i].digitToInt()
        }
        sum
    }

    fun part2(input: List<String>) = input.sumOf { sequence ->
        val step = sequence.length / 2
        var sum = 0
        for (i in sequence.indices) {
            val j = if (i + step > sequence.lastIndex) i + step - sequence.length else i + step
            if (sequence[i] == sequence[j]) sum += sequence[i].digitToInt()
        }
        sum
    }
}
