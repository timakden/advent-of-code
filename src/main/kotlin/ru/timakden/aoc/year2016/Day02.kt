package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 2: Bathroom Security](https://adventofcode.com/2016/day/2).
 */
object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): String {
        var point = Point(1, 1)

        return input.map {
            point = goToTheNextPointPart1(point, it)
            keypadPart1[point.y][point.x]
        }.joinToString("")
    }

    fun part2(input: List<String>): String {
        var point = Point(0, 2)

        return input.map {
            point = goToTheNextPointPart2(point, it)
            keypadPart2[point.y][point.x]
        }.joinToString(separator = "")
    }

    private val keypadPart1 = arrayOf(
        charArrayOf('1', '2', '3'),
        charArrayOf('4', '5', '6'),
        charArrayOf('7', '8', '9')
    )

    private val keypadPart2 = arrayOf(
        charArrayOf(' ', ' ', '1', ' ', ' '),
        charArrayOf(' ', '2', '3', '4', ' '),
        charArrayOf('5', '6', '7', '8', '9'),
        charArrayOf(' ', 'A', 'B', 'C', ' '),
        charArrayOf(' ', ' ', 'D', ' ', ' ')
    )

    private fun goToTheNextPointPart1(point: Point, instruction: String): Point {
        var (x, y) = point

        instruction.forEach {
            when (it) {
                'U' -> if (y != 0) y--
                'D' -> if (y != 2) y++
                'R' -> if (x != 2) x++
                'L' -> if (x != 0) x--
            }
        }

        return Point(x, y)
    }

    private fun goToTheNextPointPart2(point: Point, instruction: String): Point {
        var (x, y) = point

        instruction.forEach {
            when (it) {
                'U' -> if (!(y == 0 || keypadPart2[y - 1][x] == ' ')) y--
                'D' -> if (y != 4 && keypadPart2[y + 1][x] != ' ') y++
                'R' -> if (x != 4 && keypadPart2[y][x + 1] != ' ') x++
                'L' -> if (!(x == 0 || keypadPart2[y][x - 1] == ' ')) x--
            }
        }

        return Point(x, y)
    }
}
