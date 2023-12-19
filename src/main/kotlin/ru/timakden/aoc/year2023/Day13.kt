package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.absoluteValue

/**
 * [Day 13: Point of Incidence](https://adventofcode.com/2023/day/13).
 */
object Day13 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day13")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int =
        parseInput(input).sumOf { findMirror(it, 0) }

    fun part2(input: List<String>): Int =
        parseInput(input).sumOf { findMirror(it, 1) }

    private fun findMirror(pattern: List<String>, goalTotal: Int): Int =
        findHorizontalMirror(pattern, goalTotal) ?: findVerticalMirror(pattern, goalTotal)
        ?: error("Pattern does not mirror")

    private fun findHorizontalMirror(pattern: List<String>, goalTotal: Int): Int? =
        (0 until pattern.lastIndex).firstNotNullOfOrNull { start ->
            if (createMirrorRanges(start, pattern.lastIndex)
                    .sumOf { (up, down) ->
                        pattern[up] diff pattern[down]
                    } == goalTotal
            ) (start + 1) * 100
            else null
        }

    private fun findVerticalMirror(pattern: List<String>, goalTotal: Int): Int? =
        (0 until pattern.first().lastIndex).firstNotNullOfOrNull { start ->
            if (createMirrorRanges(start, pattern.first().lastIndex)
                    .sumOf { (left, right) ->
                        pattern.columnToString(left) diff pattern.columnToString(right)
                    } == goalTotal
            ) start + 1
            else null
        }

    private infix fun String.diff(other: String): Int =
        indices.count { this[it] != other[it] } + (length - other.length).absoluteValue

    private fun createMirrorRanges(start: Int, max: Int): List<Pair<Int, Int>> =
        (start downTo 0).zip(start + 1..max)

    private fun List<String>.columnToString(column: Int): String =
        this.map { it[column] }.joinToString("")

    private fun parseInput(input: List<String>): List<List<String>> =
        input.joinToString("\n").split("\n\n").map { it.lines() }
}
