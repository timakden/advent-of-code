package ru.timakden.aoc.year2017

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Spiral Memory](https://adventofcode.com/2017/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2017/Day03")
            println("Part One: ${part1(input.single())}")
            println("Part Two: ${part2(input.single())}")
        }
    }

    fun part1(input: String): Int {
        val lastSquare = input.toInt()

        if (lastSquare <= 1) return 0

        val directions = directionIterator()
        var currentPoint = Point(0, 0)
        var filledSquares = 1
        var stepsInDirection = 1
        var movesWithoutIncrease = 0
        var nextDirection = directions.next()

        loop@ while (true) {
            repeat(stepsInDirection) {
                currentPoint = when (nextDirection) {
                    'R' -> currentPoint.moveRight()
                    'U' -> currentPoint.moveUp()
                    'L' -> currentPoint.moveLeft()
                    'D' -> currentPoint.moveDown()
                    else -> error("Unsupported direction")
                }
                filledSquares++
                if (filledSquares == lastSquare) break@loop
            }
            nextDirection = directions.next()

            // R, L -> 1 point
            // U, D -> 2 points
            // R, L -> 3 points
            // U, D -> 4 points
            // etc.
            movesWithoutIncrease++
            if (movesWithoutIncrease == 2) {
                stepsInDirection++
                movesWithoutIncrease = 0
            }
        }

        return currentPoint.distanceTo(Point(0, 0))
    }

    fun part2(input: String): Int {
        val lastValue = input.toInt()

        if (lastValue <= 1) return 0

        val directions = directionIterator()
        var currentPoint = Point(0, 0)
        var totalPoints = 1
        var stepsInDirection = 1
        var movesWithoutIncrease = 0
        var nextDirection = directions.next()
        val points = mutableMapOf(currentPoint to 1)

        loop@ while (true) {
            repeat(stepsInDirection) {
                currentPoint = when (nextDirection) {
                    'R' -> currentPoint.moveRight()
                    'U' -> currentPoint.moveUp()
                    'L' -> currentPoint.moveLeft()
                    'D' -> currentPoint.moveDown()
                    else -> error("Unsupported direction")
                }
                totalPoints++

                val value = points.filterKeys { it in currentPoint.neighbors() }.values.sum()
                points[currentPoint] = value
                if (value > lastValue) break@loop
            }
            nextDirection = directions.next()

            movesWithoutIncrease++
            if (movesWithoutIncrease == 2) {
                stepsInDirection++
                movesWithoutIncrease = 0
            }
        }

        return points[currentPoint] ?: 0
    }

    private fun directionIterator() = iterator {
        while (true) {
            yieldAll(listOf('R', 'U', 'L', 'D'))
        }
    }
}
