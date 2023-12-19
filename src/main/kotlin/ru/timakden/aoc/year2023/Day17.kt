package ru.timakden.aoc.year2023

import arrow.core.partially1
import arrow.core.partially2
import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.Point.Companion.DOWN
import ru.timakden.aoc.util.Point.Companion.LEFT
import ru.timakden.aoc.util.Point.Companion.RIGHT
import ru.timakden.aoc.util.Point.Companion.UP
import ru.timakden.aoc.util.dijkstra
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 17: Clumsy Crucible](https://adventofcode.com/2023/day/17).
 */
object Day17 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day17")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = solve(input)

    fun part2(input: List<String>) = solve(input, 4, 10)

    private data class State(val position: Point, val dir: Point, val sameDirMoves: Int)

    private fun neighbors(
        grid: List<List<Int>>,
        state: State,
        minMoves: Int,
        maxMoves: Int
    ): List<State> {
        return buildList {
            for (dir in listOf(UP, DOWN, LEFT, RIGHT)) {
                val newPosition = state.position + dir
                val movesSoFar = if (dir == state.dir) state.sameDirMoves + 1 else 1

                val (a, b) = newPosition
                if (a !in grid.indices || b !in grid[0].indices) continue
                if (movesSoFar > maxMoves) continue
                if (dir.x * -1 == state.dir.x && dir.y * -1 == state.dir.y) continue
                if (dir != state.dir && state.sameDirMoves < minMoves) continue

                add(State(newPosition, dir, movesSoFar))
            }
        }
    }

    private fun startingPoint(point: Point): State = State(Point(0, 0), point, 0)

    private fun solve(s: List<String>, minMoves: Int = 1, maxMoves: Int = 3): UInt {
        val grid = s.map { it.map { char -> char.toString().toInt() } }
        val startingPoints = listOf(UP, DOWN, LEFT, RIGHT).map { startingPoint(it) }
        val neighbors = ::neighbors.partially1(grid).partially2(minMoves).partially2(maxMoves)

        val bestDistance = dijkstra(startingPoints, neighbors) { _, nextNode ->
            grid[nextNode.position.x][nextNode.position.y].toUInt()
        }
        val result = bestDistance.filter { (k, _) -> k.position == Point(grid.indices.last, grid[0].indices.last) }
        return result.filterKeys { it.sameDirMoves in minMoves..maxMoves }.minOf { (_, value) -> value }
    }
}
