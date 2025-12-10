package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.md5
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 4: The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4).
 */
object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day04").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): Long {
        var count = 0L

        while (true) {
            if ("$input$count".md5().startsWith("00000")) return count
            count++
        }
    }

    fun part2(input: String): Long {
        var count = 0L

        while (true) {
            if ("$input$count".md5().startsWith("000000")) return count
            count++
        }
    }
}
