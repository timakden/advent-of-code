package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime


object Day25 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day25")
            println("Part One: ${part1(input)}")
        }
    }

    fun part1(input: List<String>): String =
        input.sumOf { it.fromSnafu() }.toSnafu()

    private fun String.fromSnafu(): Long =
        fold(0) { carry, char ->
            (carry * 5) + when (char) {
                '-' -> -1
                '=' -> -2
                else -> char.digitToInt()
            }
        }

    private fun Long.toSnafu(): String =
        generateSequence(this) { (it + 2) / 5 }
            .takeWhile { it != 0L }
            .map { "012=-"[(it % 5).toInt()] }
            .joinToString("")
            .reversed()
}
