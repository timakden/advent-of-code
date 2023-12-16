package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.max
import kotlin.math.min

/**
 * [Day 14: Regolith Reservoir](https://adventofcode.com/2022/day/14).
 */
object Day14 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day14")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val cave = buildCave(input)
        val sandSource = Point(500, 0)
        val caveLimits = listOf(
            cave.minOf { it.x },
            cave.maxOf { it.x },
            cave.maxOf { it.y }
        )
        var counter = 0

        outer@ while (true) {
            var unitOfSand = sandSource

            while (true) {
                val (x, y) = unitOfSand
                if (Point(x, y + 1) !in cave) unitOfSand = unitOfSand.copy(y = y + 1) else {
                    if (Point(x - 1, y + 1) in cave) {
                        if (Point(x + 1, y + 1) in cave) {
                            counter++
                            cave += unitOfSand
                            break
                        } else unitOfSand = Point(x + 1, y + 1)
                    } else unitOfSand = Point(x - 1, y + 1)

                    if (unitOfSand.x in caveLimits || unitOfSand.y in caveLimits) break@outer
                }
            }
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        val cave = buildCave(input)
        val sandSource = Point(500, 0)
        val caveLimit = cave.maxOf { it.y + 1 }
        var counter = 0

        outer@ while (true) {
            var unitOfSand = sandSource

            while (true) {
                val (x, y) = unitOfSand
                if (Point(x, y + 1) !in cave) unitOfSand = unitOfSand.copy(y = y + 1) else
                    if (Point(x - 1, y + 1) in cave) {
                        if (Point(x + 1, y + 1) in cave) {
                            counter++
                            cave += unitOfSand
                            if (unitOfSand == sandSource)
                                break@outer
                            break
                        } else unitOfSand = Point(x + 1, y + 1)
                    } else unitOfSand = Point(x - 1, y + 1)


                if (unitOfSand.y == caveLimit) {
                    counter++
                    cave += unitOfSand
                    if (unitOfSand == sandSource) break@outer
                    break
                }
            }
        }

        return counter
    }

    private fun buildCave(input: List<String>): MutableSet<Point> {
        val cave = mutableSetOf<Point>()
        input.forEach { line ->
            val points = line.split(" -> ").map { point ->
                point.split(',').let { Point(it.first().toInt(), it.last().toInt()) }
            }

            (1..points.lastIndex).forEach { index ->
                val point1 = points[index - 1]
                val point2 = points[index]

                (min(point1.x, point2.x)..max(point1.x, point2.x)).forEach {
                    cave += Point(it, point1.y)
                }

                (min(point1.y, point2.y)..max(point1.y, point2.y)).forEach {
                    cave += Point(point1.x, it)
                }
            }
        }

        return cave
    }
}
