package ru.timakden.aoc.year2016.day13

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

typealias Coordinate = Pair<Int, Int>
typealias Maze = MutableMap<Coordinate, Char>

@ExperimentalTime
fun main() {
    measure {
        val maze = createMaze(10, input)
        println()
    }
}

private fun createMaze(maxCoordinate: Int, input: Int): Maze {
    val maze = mutableMapOf<Coordinate, Char>()

    repeat(maxCoordinate) { x ->
        repeat(maxCoordinate) { y ->
            val coordinate = x to y
            val cellType = calculateCoordinateType(coordinate)
            maze[y to x] = cellType
        }
    }

    return maze
}

private fun calculateCoordinateType(coordinate: Coordinate): Char {
    val (x, y) = coordinate
    val number = x * x + 3 * x + 2 * x * y + y + y * y + input
    val binary = Integer.toBinaryString(number)
    val numberOfBits = binary.count { it == '1' }
    return if (numberOfBits % 2 == 0) '.' else '#'
}

private fun printMaze(maze: Maze) {

}
