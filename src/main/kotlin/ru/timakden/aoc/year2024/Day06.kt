package ru.timakden.aoc.year2024

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2024.Day06.Direction.*

/**
 * [Day 6: Guard Gallivant](https://adventofcode.com/2024/day/6).
 */
object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    const val GUARD = '^'
    const val OBSTRUCTION = '#'
    const val EMPTY = '.'

    fun part1(input: List<String>): Int {
        val guardPath = input.map { it.toCharArray() }.toTypedArray().findGuardPath()

        when (guardPath) {
            is Either.Left -> throw guardPath.value
            is Either.Right -> return guardPath.value.distinct().count()
        }
    }

    fun part2(input: List<String>): Int {
        val inputArray = input.map { it.toCharArray() }.toTypedArray()
        val startingPosition = inputArray.guardPosition()
        val guardPath = inputArray.findGuardPath()

        when (guardPath) {
            is Either.Left -> throw guardPath.value
            is Either.Right -> {
                val possibleObstructions = guardPath.value.distinct() - startingPosition

                val count = possibleObstructions.count { possibleObstruction ->
                    inputArray[possibleObstruction.y][possibleObstruction.x] = OBSTRUCTION

                    val newGuardPath = inputArray.findGuardPath()

                    inputArray[possibleObstruction.y][possibleObstruction.x] = EMPTY

                    when (newGuardPath) {
                        is Either.Right -> false
                        is Either.Left -> true
                    }
                }

                return count
            }
        }
    }

    private fun Array<CharArray>.guardPosition(): Point {
        this.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == GUARD) {
                    return Point(x, y)
                }
            }
        }
        error("Guard not found")
    }

    private fun Array<CharArray>.findGuardPath(): Either<IllegalStateException, List<Point>> {
        var currentPosition = this.guardPosition()
        var currentDirection = Direction.fromChar(GUARD)
        val visitedPositions = mutableListOf<Pair<Point, Direction>>(currentPosition to currentDirection)

        while (true) {
            val newPosition = when (currentDirection) {
                UP -> currentPosition.copy(y = currentPosition.y - 1)
                DOWN -> currentPosition.copy(y = currentPosition.y + 1)
                LEFT -> currentPosition.copy(x = currentPosition.x - 1)
                RIGHT -> currentPosition.copy(x = currentPosition.x + 1)
            }


            val cellValue = this.getOrNull(newPosition.y)?.getOrNull(newPosition.x)

            if (cellValue == null) {
                break
            } else if (cellValue == EMPTY || cellValue == GUARD) {
                currentPosition = newPosition
                val pair = currentPosition to currentDirection

                if (visitedPositions.contains(pair)) {
                    return IllegalStateException("Duplicate $pair").left()
                }

                visitedPositions += pair
            } else if (cellValue == OBSTRUCTION) {
                currentDirection = currentDirection.turnRight()
            }
        }

        return visitedPositions.map { it.first }.distinct().right()
    }

    private enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        fun turnRight() = when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }

        companion object {
            fun fromChar(char: Char) = when (char) {
                '^' -> UP
                'v' -> DOWN
                '<' -> LEFT
                '>' -> RIGHT
                else -> throw error("Invalid direction: $char")
            }
        }
    }
}
