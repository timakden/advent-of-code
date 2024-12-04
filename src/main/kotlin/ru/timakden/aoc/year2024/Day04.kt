package ru.timakden.aoc.year2024

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2024.Day04.Direction.*

/**
 * [Day 4: Ceres Search](https://adventofcode.com/2024/day/4).
 */
object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        var count = 0

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, letter ->
                if (letter == 'X') {
                    val currentPoint = Point(x, y)
                    Direction.entries.forEach { direction ->
                        val mPoint = findNextLetter('M', currentPoint, direction, input)
                        if (mPoint != null) {
                            val aPoint = findNextLetter('A', mPoint, direction, input)
                            if (aPoint != null) {
                                val sPoint = findNextLetter('S', aPoint, direction, input)
                                if (sPoint != null) {
                                    count++
                                }
                            }
                        }
                    }
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val mas = listOf("MAS", "SAM")

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, letter ->
                if (letter == 'A') {
                    val str1 = buildString {
                        append(input.getOrNull(y - 1)?.getOrNull(x - 1))
                        append(letter)
                        append(input.getOrNull(y + 1)?.getOrNull(x + 1))
                    }
                    val str2 = buildString {
                        append(input.getOrNull(y - 1)?.getOrNull(x + 1))
                        append(letter)
                        append(input.getOrNull(y + 1)?.getOrNull(x - 1))
                    }

                    if (str1 in mas && str2 in mas) {
                        count++
                    }
                }
            }
        }

        return count
    }

    private fun findNextLetter(
        letterToFind: Char,
        currentPoint: Point,
        searchDirection: Direction,
        input: List<String>
    ): Point? {
        val pointToCheck = when (searchDirection) {
            UP -> currentPoint.moveUp()
            DOWN -> currentPoint.moveDown()
            LEFT -> currentPoint.moveLeft()
            RIGHT -> currentPoint.moveRight()
            DIAGONAL_LEFT_UP -> currentPoint.moveUpLeft()
            DIAGONAL_LEFT_DOWN -> currentPoint.moveDownLeft()
            DIAGONAL_RIGHT_UP -> currentPoint.moveUpRight()
            DIAGONAL_RIGHT_DOWN -> currentPoint.moveDownRight()
        }

        input.getOrNull(pointToCheck.y)?.getOrNull(pointToCheck.x)?.let {
            if (it == letterToFind) return pointToCheck
        }
        return null
    }

    private enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DIAGONAL_LEFT_UP,
        DIAGONAL_LEFT_DOWN,
        DIAGONAL_RIGHT_UP,
        DIAGONAL_RIGHT_DOWN
    }
}
