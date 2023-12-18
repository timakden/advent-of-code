package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.Polygon
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 18: Lavaduct Lagoon](https://adventofcode.com/2023/day/18).
 */
object Day18 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day18")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        var currentPoint = Point(0, 0)
        val trench = mutableListOf(currentPoint)
        input.forEach { s ->
            val digPlan = DigPlan.fromString(s)
            currentPoint = when (digPlan.direction) {
                'R' -> currentPoint.moveRight(digPlan.meters)
                'D' -> currentPoint.moveDown(digPlan.meters)
                'L' -> currentPoint.moveLeft(digPlan.meters)
                'U' -> currentPoint.moveUp(digPlan.meters)
                else -> currentPoint
            }
            trench += currentPoint
        }

        val polygon = Polygon(trench.distinct())

        return (polygon.area + polygon.perimeter / 2 + 1).toLong()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun part2(input: List<String>): Long {
        var currentPoint = Point(0, 0)
        val trench = mutableListOf(currentPoint)
        input.forEach { s ->
            val digPlan = DigPlan.fromString(s)
            val meters = digPlan.color.substringAfter('#').dropLast(1).hexToInt()
            currentPoint = when (digPlan.color.last()) {
                '0' -> currentPoint.moveRight(meters)
                '1' -> currentPoint.moveDown(meters)
                '2' -> currentPoint.moveLeft(meters)
                '3' -> currentPoint.moveUp(meters)
                else -> currentPoint
            }
            trench += currentPoint
        }

        val polygon = Polygon(trench.distinct())

        return (polygon.area + polygon.perimeter / 2 + 1).toLong()
    }

    private data class DigPlan(val direction: Char, val meters: Int, val color: String) {
        companion object {
            fun fromString(input: String): DigPlan {
                val direction = input.first()
                val meters = input.substringAfter(' ').substringBefore(' ').toInt()
                val color = input.substringAfter('(').substringBefore(')')
                return DigPlan(direction, meters, color)
            }
        }
    }
}
