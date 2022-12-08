package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2016.Day01.Direction.*
import kotlin.math.abs
import kotlin.time.ExperimentalTime

object Day01 {
    @JvmStatic
    @ExperimentalTime
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

        val instructions = input.split(", ")

        instructions.forEach { instruction ->
            val numberOfBlocks = instruction.substring(1).toInt()

            direction = if (instruction.startsWith('L')) direction.turnLeft() else direction.turnRight()
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
        val coordinates = mutableListOf<Pair<Int, Int>>()

        val instructions = input.split(", ")

        instructions.forEach { instruction ->
            val numberOfBlocks = instruction.substring(1).toInt()

            direction = if (instruction.startsWith('L')) direction.turnLeft() else direction.turnRight()

            repeat(numberOfBlocks) {
                when (direction) {
                    NORTH -> x++
                    SOUTH -> x--
                    EAST -> y--
                    WEST -> y++
                }

                if (coordinates.contains(x to y)) return abs(x) + abs(y)

                coordinates.add(x to y)
            }
        }

        return 0
    }

    private enum class Direction {
        NORTH, SOUTH, EAST, WEST;

        private fun turn(turnTo: Char) = if (turnTo.equals('L', true)) {
            when (this) {
                NORTH -> WEST
                SOUTH -> EAST
                EAST -> NORTH
                WEST -> SOUTH
            }
        } else {
            when (this) {
                NORTH -> EAST
                SOUTH -> WEST
                EAST -> SOUTH
                WEST -> NORTH
            }
        }

        fun turnLeft() = turn('L')

        fun turnRight() = turn('R')
    }
}
