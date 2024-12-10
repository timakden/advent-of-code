package ru.timakden.aoc.year2024

import arrow.core.partially1
import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.Point.Companion.DOWN
import ru.timakden.aoc.util.Point.Companion.LEFT
import ru.timakden.aoc.util.Point.Companion.RIGHT
import ru.timakden.aoc.util.Point.Companion.UP
import ru.timakden.aoc.util.dijkstra
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 10: Hoof It](https://adventofcode.com/2024/day/10).
 */
object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day10")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val grid = input.map { it.map { char -> char.digitToInt() } }
        return grid.trailheads().sumOf { trailhead ->
            val neighbors = ::neighbors.partially1(grid)
            dijkstra(listOf(trailhead), neighbors) { _, _ -> 1u }
                .filterValues { it == 9u }
                .count()
        }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.map { char -> char.digitToInt() } }
        return grid.trailheads().sumOf { trailhead ->
            val neighbors = ::neighbors.partially1(grid)
            val distances = dijkstra(listOf(trailhead), neighbors) { _, _ -> 1u }
            countTrailRating(trailhead to 0u, distances)
        }
    }

    private fun List<List<Int>>.trailheads(): List<Point> {
        val trailheads = mutableListOf<Point>()
        forEachIndexed { y, row ->
            row.forEachIndexed { x, i ->
                if (i == 0) trailheads += Point(x, y)
            }
        }
        return trailheads
    }

    private fun neighbors(grid: List<List<Int>>, currentPoint: Point) = buildList {
        for (dir in listOf(DOWN, LEFT, RIGHT, UP)) {
            val nextPoint = currentPoint + dir
            val (x, y) = nextPoint
            if (y !in grid.indices || x !in grid[0].indices) continue

            val currentValue = grid[currentPoint.y][currentPoint.x]
            val nextValue = grid[y][x]

            if (nextValue - currentValue == 1) add(nextPoint)
        }
    }

    private fun countTrailRating(currentPoint: Pair<Point, UInt>, points: Map<Point, UInt>): Int {
        val remainingPoints = points.filter { (key, value) ->
            (value - currentPoint.second == 1u) && key.distanceTo(currentPoint.first) == 1
        }

        if (remainingPoints.isEmpty()) {
            return if (currentPoint.second == 9u) 1 else 0
        }

        return remainingPoints.entries.sumOf { countTrailRating(it.key to it.value, points) }
    }
}
