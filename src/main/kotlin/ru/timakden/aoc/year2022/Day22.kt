package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day22.Facing.*
import ru.timakden.aoc.year2022.Day22.Side.*
import ru.timakden.aoc.year2022.Day22.Side.Companion.toSide

/**
 * [Day 22: Monkey Map](https://adventofcode.com/2022/day/22).
 */
object Day22 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day22")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val delimiterIndex = input.indexOfFirst { it.isBlank() }
        val map = BoardMap(input.subList(0, delimiterIndex))
        "\\d+|[LR]".toRegex().findAll(input[delimiterIndex + 1]).map { it.value }.forEach {
            when (it) {
                "L" -> map.turnCounterclockwise()
                "R" -> map.turnClockwise()
                else -> for (i in 0..<it.toInt()) if (!map.move()) break
            }
        }
        return map.password
    }

    fun part2(input: List<String>): Int {
        val delimiterIndex = input.indexOfFirst { it.isBlank() }
        val map = BoardMap(input.subList(0, delimiterIndex))
        "\\d+|[LR]".toRegex().findAll(input[delimiterIndex + 1]).map { it.value }.forEach {
            when (it) {
                "L" -> map.turnCounterclockwise()
                "R" -> map.turnClockwise()
                else -> for (i in 0..<it.toInt()) if (!map.moveCube()) break
            }
        }
        return map.password
    }

    private data class BoardMap(val map: List<String>) {
        val height = map.size
        val width = map.maxOf { it.length }
        var position = Point(map.first().indexOfFirst { it == '.' }, 0)
        var facing = RIGHT
        private val allowedTiles = listOf('.', '#')

        fun turnClockwise() {
            facing = when (facing) {
                DOWN -> LEFT
                LEFT -> UP
                RIGHT -> DOWN
                UP -> RIGHT
            }
        }

        fun turnCounterclockwise() {
            facing = when (facing) {
                DOWN -> RIGHT
                LEFT -> DOWN
                RIGHT -> UP
                UP -> LEFT
            }
        }

        private fun wrap(): Point {
            when (facing) {
                DOWN -> {
                    var row = position.y
                    while (true) {
                        if (row == 0) return Point(position.x, 0)

                        map[row].getOrNull(position.x)?.let {
                            if (it !in allowedTiles) return Point(position.x, row + 1)
                        } ?: return Point(position.x, row + 1)

                        row--
                    }
                }

                LEFT -> {
                    var column = position.x
                    while (true) {
                        if (column == width - 1) return Point(width - 1, position.y)

                        map[position.y].getOrNull(column)?.let {
                            if (it !in allowedTiles) return Point(column - 1, position.y)
                        } ?: return Point(column - 1, position.y)

                        column++
                    }
                }

                RIGHT -> {
                    var column = position.x
                    while (true) {
                        if (column == 0) return Point(0, position.y)

                        map[position.y].getOrNull(column)?.let {
                            if (it !in allowedTiles) return Point(column + 1, position.y)
                        } ?: return Point(column + 1, position.y)

                        column--
                    }
                }

                UP -> {
                    var row = position.y
                    while (true) {
                        if (row == height - 1) return Point(position.x, height - 1)

                        map[row].getOrNull(position.x)?.let {
                            if (it !in allowedTiles) return Point(position.x, row - 1)
                        } ?: return Point(position.x, row - 1)

                        row++
                    }
                }
            }
        }

        fun wrapCube(): Pair<Point, Facing> {
            var nextFacing = facing
            val side = position.toSide()
            var nextPosition = position

            if (side == A && facing == UP) {
                nextFacing = RIGHT
                nextPosition = Point(0, 3 * 50 + position.x - 50)
            } else if (side == A && facing == LEFT) {
                nextFacing = RIGHT
                nextPosition = Point(0, 2 * 50 + (50 - position.y - 1))
            } else if (side == B && facing == UP) {
                nextFacing = UP
                nextPosition = Point(position.x - 100, 199)
            } else if (side == B && facing == RIGHT) {
                nextFacing = LEFT
                nextPosition = Point(99, (50 - position.y) + 2 * 50 - 1)
            } else if (side == B && facing == DOWN) {
                nextFacing = LEFT
                nextPosition = Point(99, 50 + (position.x - 2 * 50))
            } else if (side == C && facing == RIGHT) {
                nextFacing = UP
                nextPosition = Point((position.y - 50) + 2 * 50, 49)
            } else if (side == C && facing == LEFT) {
                nextFacing = DOWN
                nextPosition = Point(position.y - 50, 100)
            } else if (side == E && facing == LEFT) {
                nextFacing = RIGHT
                nextPosition = Point(50, 50 - (position.y - 2 * 50) - 1)
            } else if (side == E && facing == UP) {
                nextFacing = RIGHT
                nextPosition = Point(50, 50 + position.x)
            } else if (side == D && facing == DOWN) {
                nextFacing = LEFT
                nextPosition = Point(49, 3 * 50 + (position.x - 50))
            } else if (side == D && facing == RIGHT) {
                nextFacing = LEFT
                nextPosition = Point(149, 50 - (position.y - 50 * 2) - 1)
            } else if (side == F && facing == RIGHT) {
                nextFacing = UP
                nextPosition = Point((position.y - 3 * 50) + 50, 149)
            } else if (side == F && facing == LEFT) {
                nextFacing = DOWN
                nextPosition = Point(50 + (position.y - 3 * 50), 0)
            } else if (side == F && facing == DOWN) {
                nextFacing = DOWN
                nextPosition = Point(position.x + 100, 0)
            }

            return nextPosition to nextFacing
        }

        private fun nextPosition() = when (facing) {
            DOWN -> runCatching {
                if (map[position.y + 1][position.x] in allowedTiles)
                    Point(position.x, position.y + 1)
                else wrap()
            }.getOrDefault(wrap())

            LEFT -> runCatching {
                if (map[position.y][position.x - 1] in allowedTiles)
                    Point(position.x - 1, position.y)
                else wrap()
            }.getOrDefault(wrap())

            RIGHT -> runCatching {
                if (map[position.y][position.x + 1] in allowedTiles)
                    Point(position.x + 1, position.y)
                else wrap()
            }.getOrDefault(wrap())

            UP -> runCatching {
                if (map[position.y - 1][position.x] in allowedTiles)
                    Point(position.x, position.y - 1)
                else wrap()
            }.getOrDefault(wrap())
        }

        private fun nextPositionCube() = when (facing) {
            DOWN -> runCatching {
                if (map[position.y + 1][position.x] in allowedTiles)
                    Point(position.x, position.y + 1) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            LEFT -> runCatching {
                if (map[position.y][position.x - 1] in allowedTiles)
                    Point(position.x - 1, position.y) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            RIGHT -> runCatching {
                if (map[position.y][position.x + 1] in allowedTiles)
                    Point(position.x + 1, position.y) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            UP -> runCatching {
                if (map[position.y - 1][position.x] in allowedTiles)
                    Point(position.x, position.y - 1) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())
        }

        fun move(): Boolean {
            val nextPosition = nextPosition()
            if (map[nextPosition.y][nextPosition.x] == '.') {
                position = nextPosition
                return true
            }
            return false
        }

        fun moveCube(): Boolean {
            val (nextPosition, nextFacing) = nextPositionCube()
            if (map[nextPosition.y][nextPosition.x] == '.') {
                position = nextPosition
                facing = nextFacing
                return true
            }
            return false
        }

        val password
            get() = (position.y + 1) * 1000 + (position.x + 1) * 4 + facing.value
    }

    private enum class Facing(val value: Int) { DOWN(1), LEFT(2), RIGHT(0), UP(3) }

    private enum class Side {
        A, B, C, D, E, F;

        companion object {
            fun Point.toSide(): Side {
                val row = this.y
                val column = this.x
                return if (row in 0..49 && column in 50..99) A
                else if (row in 0..49 && column in 100..149) B
                else if (row in 50..99 && column in 50..99) C
                else if (row in 100..149 && column in 50..99) D
                else if (row in 100..149 && column in 0..49) E
                else if (row in 150..199 && column in 0..49) F
                else error("Invalid position $this")
            }
        }
    }
}
