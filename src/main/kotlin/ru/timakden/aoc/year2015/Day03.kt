package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day03 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day03").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): Int {
        var x = 0
        var y = 0
        val coordinates = mutableSetOf(0 to 0)

        input.forEach {
            when (it) {
                '^' -> y++
                'v' -> y--
                '<' -> x--
                '>' -> x++
            }

            coordinates += x to y
        }

        return coordinates.size
    }

    fun part2(input: String): Int {
        var santaX = 0
        var santaY = 0
        var robotX = 0
        var robotY = 0
        var counter = 0
        val coordinates = mutableSetOf(0 to 0)

        input.forEach {
            val isCounterEven = counter % 2 == 0

            when (it) {
                '^' -> if (isCounterEven) santaY++ else robotY++
                'v' -> if (isCounterEven) santaY-- else robotY--
                '<' -> if (isCounterEven) santaX-- else robotX--
                '>' -> if (isCounterEven) santaX++ else robotX++
            }

            coordinates += when {
                isCounterEven -> santaX to santaY
                else -> robotX to robotY
            }

            counter++
        }

        return coordinates.size
    }
}
