package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.md5
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day04 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day04").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): Long {
        var count = 0L
        var encoded: String

        while (true) {
            encoded = (input + count.toString()).md5()
            if (encoded.startsWith("00000")) return count
            count++
        }
    }

    fun part2(input: String): Long {
        var count = 0L
        var encoded: String

        while (true) {
            encoded = "$input$count".md5()
            if (encoded.startsWith("000000")) return count
            count++
        }
    }
}
