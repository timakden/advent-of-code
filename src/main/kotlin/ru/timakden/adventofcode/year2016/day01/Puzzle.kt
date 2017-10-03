package ru.timakden.adventofcode.year2016.day01

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
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

    return Math.abs(x) + Math.abs(y)
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

        (1..numberOfBlocks).forEach {
            when (direction) {
                Direction.NORTH -> x++
                Direction.SOUTH -> x--
                Direction.EAST -> y--
                Direction.WEST -> y++
            }

            if (coordinates.contains(Pair(x, y))) return Math.abs(x) + Math.abs(y)

            coordinates.add(Pair(x, y))
        }
    }

    return 0
}

private enum class Direction { NORTH, SOUTH, EAST, WEST }

private fun Direction.turnLeft(): Direction {
    return turn('L')
}

private fun Direction.turnRight(): Direction {
    return turn('R')
}

private fun Direction.turn(turnTo: Char): Direction {
    return if (turnTo == 'L') {
        when {
            this == Direction.NORTH -> Direction.WEST
            this == Direction.SOUTH -> Direction.EAST
            this == Direction.EAST -> Direction.NORTH
            else -> Direction.SOUTH
        }
    } else {
        when {
            this == Direction.NORTH -> Direction.EAST
            this == Direction.SOUTH -> Direction.WEST
            this == Direction.EAST -> Direction.SOUTH
            else -> Direction.NORTH
        }
    }
}
