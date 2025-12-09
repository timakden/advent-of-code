package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

/**
 * [Day 9: Movie Theater](https://adventofcode.com/2025/day/9).
 */
object Day09 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day09_test")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val points = input.points()
        var maxArea = 0L

        for (i in 0..<points.lastIndex) {
            for (j in (i + 1)..points.lastIndex) {
                val a = points[i]
                val b = points[j]
                val area = (abs(a.x - b.x) + 1).toLong() * (abs(a.y - b.y) + 1).toLong()
                if (area > maxArea) maxArea = area
            }
        }

        return maxArea
    }

    fun part2(input: List<String>): Long {
        TODO()
    }

    private fun List<String>.points() = map { str ->
        val (x, y) = str.split(',').map { it.toInt() }
        Point(x, y)
    }
}
