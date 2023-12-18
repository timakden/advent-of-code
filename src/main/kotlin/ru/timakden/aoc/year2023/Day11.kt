package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 11: Cosmic Expansion](https://adventofcode.com/2023/day/11).
 */
object Day11 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day11")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = solve(input, 2)

    fun part2(input: List<String>) = solve(input, 1_000_000)

    private fun solve(input: List<String>, multiplier: Int): Long {
        val emptyRowIndices = getEmptyRowIndices(input)
        val emptyColumnIndices = getEmptyColumnIndices(input)
        val galaxies = extractGalaxies(input, emptyRowIndices, emptyColumnIndices, multiplier)
        var sum = 0L
        galaxies.forEachIndexed { i, point ->
            for (j in i..galaxies.lastIndex) {
                sum += point.distanceTo(galaxies[j])
            }
        }
        return sum
    }

    private fun getEmptyRowIndices(input: List<String>) = input.mapIndexedNotNull { i, s ->
        if (s.all { it == '.' }) i else null
    }

    private fun getEmptyColumnIndices(input: List<String>): List<Int> {
        val indices = mutableListOf<Int>()
        for (i in 0..input.first().lastIndex) {
            if (input.all { it[i] == '.' }) indices += i
        }
        return indices
    }

    private fun extractGalaxies(
        input: List<String>,
        emptyRowIndices: List<Int>,
        emptyColumnIndices: List<Int>,
        multiplier: Int
    ): List<Point> {
        val galaxies = mutableListOf<Point>()
        input.forEachIndexed { row, s ->
            s.forEachIndexed { column, c ->
                if (c == '#') {
                    val rowOffset = (multiplier - 1) * emptyRowIndices.count { it < row }
                    val columnOffset = (multiplier - 1) * emptyColumnIndices.count { it < column }
                    galaxies += Point(column + columnOffset, row + rowOffset)
                }
            }
        }
        return galaxies
    }
}
