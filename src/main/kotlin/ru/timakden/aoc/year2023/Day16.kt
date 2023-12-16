package ru.timakden.aoc.year2023

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2023.Day16.Direction.*

object Day16 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day16")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = traverseGrid(
        point = Point(),
        direction = RIGHT,
        grid = input.map { it.toCharArray() }.toTypedArray<CharArray>(),
        visited = mutableSetOf(RIGHT to Point())
    ).map { it.second }.distinct().count()

    fun part2(input: List<String>): Int {
        val rows = input.size
        val columns = input[0].length
        val grid = input.map { it.toCharArray() }.toTypedArray()

        check(rows == columns)

        return runBlocking {
            val deferredResults = listOf(DOWN, LEFT, RIGHT, UP).flatMap { direction ->
                (0..<rows).map {
                    async(Dispatchers.Default) {
                        val point = when (direction) {
                            DOWN -> Point(it, 0)
                            LEFT -> Point(grid.lastIndex, it)
                            RIGHT -> Point(0, it)
                            UP -> Point(0, grid.lastIndex)
                        }
                        traverseGrid(
                            point = point,
                            direction = direction,
                            grid = grid,
                            visited = mutableSetOf(direction to point)
                        ).map { it.second }.distinct().count()
                    }
                }
            }

            deferredResults.awaitAll().max()
        }
    }

    private fun traverseGrid(
        point: Point,
        direction: Direction,
        grid: Array<CharArray>,
        visited: MutableSet<Pair<Direction, Point>>
    ): Set<Pair<Direction, Point>> {
        val gridTraverser =
            DeepRecursiveFunction<Pair<Direction, Point>, Set<Pair<Direction, Point>>> { data ->
                val p = data.second
                val d = data.first

                when (grid[p.y][p.x]) {
                    '\\' -> {
                        val nextVisited = when (d) {
                            DOWN -> RIGHT to p.move(Point(1, 0))
                            LEFT -> UP to p.move(Point(0, -1))
                            RIGHT -> DOWN to p.move(Point(0, 1))
                            UP -> LEFT to p.move(Point(-1, 0))
                        }
                        val (nextDirection, nextPoint) = nextVisited

                        if (nextPoint.isValid(grid) && nextVisited !in visited) {
                            visited += nextVisited
                            callRecursive(nextDirection to nextPoint)
                        } else {
                            visited
                        }
                    }

                    '/' -> {
                        val nextVisited = when (d) {
                            DOWN -> LEFT to p.move(Point(-1, 0))
                            LEFT -> DOWN to p.move(Point(0, 1))
                            RIGHT -> UP to p.move(Point(0, -1))
                            UP -> RIGHT to p.move(Point(1, 0))
                        }
                        val (nextDirection, nextPoint) = nextVisited

                        if (nextPoint.isValid(grid) && nextVisited !in visited) {
                            visited += nextVisited
                            callRecursive(nextDirection to nextPoint)
                        } else {
                            visited
                        }
                    }

                    '|' -> {
                        if (d == DOWN || d == UP) {
                            val vector = if (d == DOWN) Point(0, 1) else Point(0, -1)
                            val nextPoint = p.move(vector)
                            val nextVisited = d to nextPoint

                            if (nextPoint.isValid(grid) && nextVisited !in visited) {
                                visited += nextVisited
                                callRecursive(d to nextPoint)
                            } else {
                                visited
                            }
                        } else {
                            visited += callRecursive(UP to p)
                            visited += callRecursive(DOWN to p)
                            visited
                        }
                    }

                    '-' -> {
                        if (d == LEFT || d == RIGHT) {
                            val vector = if (d == LEFT) Point(-1, 0) else Point(1, 0)
                            val nextPoint = p.move(vector)
                            val nextVisited = d to nextPoint

                            if (nextPoint.isValid(grid) && nextVisited !in visited) {
                                visited += nextVisited
                                callRecursive(d to nextPoint)
                            } else {
                                visited
                            }
                        } else {
                            visited += callRecursive(LEFT to p)
                            visited += callRecursive(RIGHT to p)
                            visited
                        }
                    }

                    else -> {
                        val nextPoint = when (d) {
                            DOWN -> p.move(Point(0, 1))
                            LEFT -> p.move(Point(-1, 0))
                            RIGHT -> p.move(Point(1, 0))
                            UP -> p.move(Point(0, -1))
                        }
                        val nextVisited = d to nextPoint

                        if (nextPoint.isValid(grid) && nextVisited !in visited) {
                            visited += nextVisited
                            callRecursive(d to nextPoint)
                        } else {
                            visited
                        }
                    }
                }
            }

        return gridTraverser(direction to point)
    }

    /**
     * Checks if the current Point is valid within the given grid.
     *
     * @param grid The grid represented as a 2D char array.
     * @return `true` if the Point is valid, `false` otherwise.
     */
    private fun Point.isValid(grid: Array<CharArray>) = x in grid[0].indices && y in grid.indices

    private enum class Direction {
        DOWN, LEFT, RIGHT, UP
    }
}
