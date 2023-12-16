package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2016.Day01.Direction.*
import kotlin.math.abs

/**
 * [Day 1: No Time for a Taxicab](https://adventofcode.com/2016/day/1).
 */
object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day01").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): Int {
        var x = 0
        var y = 0
        var direction = NORTH

        input.split(", ").forEach { instruction ->
            val numberOfBlocks = instruction.substring(1).toInt()

            direction = direction.turn(instruction.first())
            when (direction) {
                NORTH -> x += numberOfBlocks
                SOUTH -> x -= numberOfBlocks
                EAST -> y -= numberOfBlocks
                WEST -> y += numberOfBlocks
            }
        }

        return abs(x) + abs(y)
    }

    fun part2(input: String): Int {
        var x = 0
        var y = 0
        var direction = NORTH
        val coordinates = mutableListOf<Point>()

        input.split(", ").forEach { instruction ->
            val numberOfBlocks = instruction.substring(1).toInt()

            direction = direction.turn(instruction.first())

            repeat(numberOfBlocks) {
                when (direction) {
                    NORTH -> x++
                    SOUTH -> x--
                    EAST -> y--
                    WEST -> y++
                }

                if (Point(x, y) in coordinates) return abs(x) + abs(y)

                coordinates += Point(x, y)
            }
        }

        return 0
    }

    private enum class Direction {
        NORTH, SOUTH, EAST, WEST;

        fun turn(turnTo: Char) = when (turnTo) {
            'L' -> {
                when (this) {
                    NORTH -> WEST
                    SOUTH -> EAST
                    EAST -> NORTH
                    WEST -> SOUTH
                }
            }

            'R' -> {
                when (this) {
                    NORTH -> EAST
                    SOUTH -> WEST
                    EAST -> SOUTH
                    WEST -> NORTH
                }
            }

            else -> error("Unsupported direction")
        }
    }
}
