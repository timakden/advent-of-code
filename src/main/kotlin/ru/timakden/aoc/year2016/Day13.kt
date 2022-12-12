package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.Coordinate
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

typealias Maze = MutableMap<Coordinate, Char>

object Day13 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day13").single().toInt()
            val maze = createMaze(10, input)
            println()
        }
    }

    private fun createMaze(maxCoordinate: Int, input: Int): Maze {
        val maze = mutableMapOf<Coordinate, Char>()

        repeat(maxCoordinate) { x ->
            repeat(maxCoordinate) { y ->
                val coordinate = x to y
                val cellType = calculateCoordinateType(coordinate, input)
                maze[y to x] = cellType
            }
        }

        return maze
    }

    private fun calculateCoordinateType(coordinate: Coordinate, input: Int): Char {
        val (x, y) = coordinate
        val number = x * x + 3 * x + 2 * x * y + y + y * y + input
        val binary = Integer.toBinaryString(number)
        val numberOfBits = binary.count { it == '1' }
        return if (numberOfBits % 2 == 0) '.' else '#'
    }

    private fun printMaze(maze: Maze) {

    }
}
