package ru.timakden.adventofcode.year2016.day01

import ru.timakden.adventofcode.measure
import kotlin.math.abs
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: String): Int {
    var x = 0
    var y = 0
    var direction = Direction.NORTH

    val instructions = input.split(", ")

    instructions.forEach { instruction ->
        val numberOfBlocks = instruction.substring(1).toInt()

        direction = if (instruction.startsWith('L')) direction.turnLeft() else direction.turnRight()
        when (direction) {
            Direction.NORTH -> x += numberOfBlocks
            Direction.SOUTH -> x -= numberOfBlocks
            Direction.EAST -> y -= numberOfBlocks
            Direction.WEST -> y += numberOfBlocks
        }
    }

    return abs(x) + abs(y)
}

fun solvePartTwo(input: String): Int {
    var x = 0
    var y = 0
    var direction = Direction.NORTH
    val coordinates = mutableListOf<Pair<Int, Int>>()

    val instructions = input.split(", ")

    instructions.forEach { instruction ->
        val numberOfBlocks = instruction.substring(1).toInt()

        direction = if (instruction.startsWith('L')) direction.turnLeft() else direction.turnRight()

        repeat(numberOfBlocks) {
            when (direction) {
                Direction.NORTH -> x++
                Direction.SOUTH -> x--
                Direction.EAST -> y--
                Direction.WEST -> y++
            }

            if (coordinates.contains(x to y)) return abs(x) + abs(y)

            coordinates.add(x to y)
        }
    }

    return 0
}

private enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    fun turn(turnTo: Char) = if (turnTo.equals('L', true)) {
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
