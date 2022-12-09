package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day02 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): String {
        var point = Pair(1, 1)
        val list = mutableListOf<Char>()

        input.forEach {
            point = goToTheNextPointPart1(point, it)

            list.add(keypadPart1[point.first][point.second])
        }

        return list.joinToString("")
    }

    fun part2(input: List<String>): String {
        var point = Pair(2, 0)
        val list = mutableListOf<Char>()

        input.forEach {
            point = goToTheNextPointPart2(point, it)
            list.add(keypadPart2[point.first][point.second])
        }

        return list.joinToString("")
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

    private fun goToTheNextPointPart1(point: Pair<Int, Int>, instruction: String): Pair<Int, Int> {
        var x = point.second
        var y = point.first

        instruction.forEach {
            when (it) {
                'U' -> if (y != 0) y--
                'D' -> if (y != 2) y++
                'R' -> if (x != 2) x++
                'L' -> if (x != 0) x--
            }
        }

        return y to x
    }

    private fun goToTheNextPointPart2(point: Pair<Int, Int>, instruction: String): Pair<Int, Int> {
        var x = point.second
        var y = point.first

        instruction.forEach {
            when (it) {
                'U' -> if (!(y == 0 || keypadPart2[y - 1][x] == ' ')) y--
                'D' -> if (y != 4 && keypadPart2[y + 1][x] != ' ') y++
                'R' -> if (x != 4 && keypadPart2[y][x + 1] != ' ') x++
                'L' -> if (!(x == 0 || keypadPart2[y][x - 1] == ' ')) x--
            }
        }

        return y to x
    }
}