package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.max
import kotlin.math.min
import kotlin.time.ExperimentalTime

object Day14 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day14")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val cave = buildCave(input)
        val sandSource = 500 to 0
        val caveLimits = listOf(
            cave.minOf { it.first },
            cave.maxOf { it.first },
            cave.maxOf { it.second }
        )
        var counter = 0

        outer@ while (true) {
            var unitOfSand = sandSource

            while (true) {
                val x = unitOfSand.first
                val y = unitOfSand.second
                if (!cave.contains(x to (y + 1))) unitOfSand = unitOfSand.copy(second = y + 1) else {
                    if (cave.contains((x - 1) to (y + 1))) {
                        if (cave.contains((x + 1) to (y + 1))) {
                            counter++
                            cave += unitOfSand
                            break
                        } else unitOfSand = (x + 1) to (y + 1)
                    } else unitOfSand = (x - 1) to (y + 1)

                    if (unitOfSand.first in caveLimits || unitOfSand.second in caveLimits) break@outer
                }
            }
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        val cave = buildCave(input)
        val sandSource = 500 to 0
        val caveLimit = cave.maxOf { it.second + 1 }
        var counter = 0

        outer@ while (true) {
            var unitOfSand = sandSource

            while (true) {
                val x = unitOfSand.first
                val y = unitOfSand.second
                if (!cave.contains(x to (y + 1))) unitOfSand = unitOfSand.copy(second = y + 1) else
                    if (cave.contains((x - 1) to (y + 1))) {
                        if (cave.contains((x + 1) to (y + 1))) {
                            counter++
                            cave += unitOfSand
                            if (unitOfSand == sandSource)
                                break@outer
                            break
                        } else unitOfSand = (x + 1) to (y + 1)
                    } else unitOfSand = (x - 1) to (y + 1)


                if (unitOfSand.second == caveLimit) {
                    counter++
                    cave += unitOfSand
                    if (unitOfSand == sandSource) break@outer
                    break
                }
            }
        }

        return counter
    }

    private fun buildCave(input: List<String>): MutableSet<Pair<Int, Int>> {
        val cave = mutableSetOf<Pair<Int, Int>>()
        input.forEach { line ->
            val points = line.split(" -> ")
                .map { point -> point.split(',').let { it.first().toInt() to it.last().toInt() } }

            (1..points.lastIndex).forEach { index ->
                val point1 = points[index - 1]
                val point2 = points[index]

                (min(point1.first, point2.first)..max(point1.first, point2.first)).forEach {
                    cave += it to point1.second
                }

                (min(point1.second, point2.second)..max(point1.second, point2.second)).forEach {
                    cave += point1.first to it
                }
            }
        }

        return cave
    }
}
