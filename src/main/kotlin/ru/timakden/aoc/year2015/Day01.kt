package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day01 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day01").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String) = input.count { it == '(' } - input.count { it == ')' }

    fun part2(input: String): Int {
        var floor = 0

        input.forEachIndexed { i, c ->
            when (c) {
                '(' -> floor++
                ')' -> floor--
            }

            if (floor == -1) return i + 1
        }

        return 0
    }
}
