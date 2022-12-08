package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day05 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day05")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.count(::isStringNicePartOne)
    fun part2(input: List<String>) = input.count(::isStringNicePartTwo)

    fun isStringNicePartOne(s: String): Boolean {
        val regex1 = "(.*[aeiou]){3}".toRegex()
        val regex2 = "(.)\\1".toRegex()
        val regex3 = "ab|cd|pq|xy".toRegex()

        return regex1.containsMatchIn(s) && regex2.containsMatchIn(s) && !regex3.containsMatchIn(s)
    }

    fun isStringNicePartTwo(s: String): Boolean {
        val regex1 = "(.{2}).*\\1".toRegex()
        val regex2 = "(.).\\1".toRegex()

        return regex1.containsMatchIn(s) && regex2.containsMatchIn(s)
    }
}
