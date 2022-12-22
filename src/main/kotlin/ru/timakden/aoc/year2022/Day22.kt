package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day22.Facing.*
import ru.timakden.aoc.year2022.Day22.Side.*
import ru.timakden.aoc.year2022.Day22.Side.Companion.toSide
import kotlin.time.ExperimentalTime


object Day22 {
    @JvmStatic
    @ExperimentalTime
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
                else -> for (i in 0 until it.toInt()) if (!map.move()) break
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
                else -> for (i in 0 until it.toInt()) if (!map.moveCube()) break
            }
        }
        return map.password
    }

    private data class BoardMap(val map: List<String>) {
        val height = map.size
        val width = map.maxOf { it.length }
        var position = 0 to map.first().indexOfFirst { it == '.' }
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

        private fun wrap(): Pair<Int, Int> {
            when (facing) {
                DOWN -> {
                    var row = position.first
                    while (true) {
                        if (row == 0) return 0 to position.second

                        map[row].getOrNull(position.second)?.let {
                            if (it !in allowedTiles) return row + 1 to position.second
                        } ?: return row + 1 to position.second

                        row--
                    }
                }

                LEFT -> {
                    var column = position.second
                    while (true) {
                        if (column == width - 1) return position.first to width - 1

                        map[position.first].getOrNull(column)?.let {
                            if (it !in allowedTiles) return position.first to column - 1
                        } ?: return position.first to column - 1

                        column++
                    }
                }

                RIGHT -> {
                    var column = position.second
                    while (true) {
                        if (column == 0) return position.first to 0

                        map[position.first].getOrNull(column)?.let {
                            if (it !in allowedTiles) return position.first to column + 1
                        } ?: return position.first to column + 1

                        column--
                    }
                }

                UP -> {
                    var row = position.first
                    while (true) {
                        if (row == height - 1) return height - 1 to position.second

                        map[row].getOrNull(position.second)?.let {
                            if (it !in allowedTiles) return row - 1 to position.second
                        } ?: return row - 1 to position.second

                        row++
                    }
                }
            }
        }

        fun wrapCube(): Pair<Pair<Int, Int>, Facing> {
            var nextFacing = facing
            val side = position.toSide()
            var nextPosition = position

            if (side == A && facing == UP) {
                nextFacing = RIGHT
                nextPosition = 3 * 50 + position.second - 50 to 0
            } else if (side == A && facing == LEFT) {
                nextFacing = RIGHT
                nextPosition = 2 * 50 + (50 - position.first - 1) to 0
            } else if (side == B && facing == UP) {
                nextFacing = UP
                nextPosition = 199 to position.second - 100
            } else if (side == B && facing == RIGHT) {
                nextFacing = LEFT
                nextPosition = (50 - position.first) + 2 * 50 - 1 to 99
            } else if (side == B && facing == DOWN) {
                nextFacing = LEFT
                nextPosition = 50 + (position.second - 2 * 50) to 99
            } else if (side == C && facing == RIGHT) {
                nextFacing = UP
                nextPosition = 49 to (position.first - 50) + 2 * 50
            } else if (side == C && facing == LEFT) {
                nextFacing = DOWN
                nextPosition = 100 to position.first - 50
            } else if (side == E && facing == LEFT) {
                nextFacing = RIGHT
                nextPosition = 50 - (position.first - 2 * 50) - 1 to 50
            } else if (side == E && facing == UP) {
                nextFacing = RIGHT
                nextPosition = 50 + position.second to 50
            } else if (side == D && facing == DOWN) {
                nextFacing = LEFT
                nextPosition = 3 * 50 + (position.second - 50) to 49
            } else if (side == D && facing == RIGHT) {
                nextFacing = LEFT
                nextPosition = 50 - (position.first - 50 * 2) - 1 to 149
            } else if (side == F && facing == RIGHT) {
                nextFacing = UP
                nextPosition = 149 to (position.first - 3 * 50) + 50
            } else if (side == F && facing == LEFT) {
                nextFacing = DOWN
                nextPosition = 0 to 50 + (position.first - 3 * 50)
            } else if (side == F && facing == DOWN) {
                nextFacing = DOWN
                nextPosition = 0 to position.second + 100
            }

            return nextPosition to nextFacing
        }

        private fun nextPosition() = when (facing) {
            DOWN -> kotlin.runCatching {
                if (map[position.first + 1][position.second] in allowedTiles)
                    position.first + 1 to position.second
                else wrap()
            }.getOrDefault(wrap())

            LEFT -> kotlin.runCatching {
                if (map[position.first][position.second - 1] in allowedTiles)
                    position.first to position.second - 1
                else wrap()
            }.getOrDefault(wrap())

            RIGHT -> kotlin.runCatching {
                if (map[position.first][position.second + 1] in allowedTiles)
                    position.first to position.second + 1
                else wrap()
            }.getOrDefault(wrap())

            UP -> kotlin.runCatching {
                if (map[position.first - 1][position.second] in allowedTiles)
                    position.first - 1 to position.second
                else wrap()
            }.getOrDefault(wrap())
        }

        private fun nextPositionCube() = when (facing) {
            DOWN -> kotlin.runCatching {
                if (map[position.first + 1][position.second] in allowedTiles)
                    (position.first + 1 to position.second) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            LEFT -> kotlin.runCatching {
                if (map[position.first][position.second - 1] in allowedTiles)
                    (position.first to position.second - 1) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            RIGHT -> kotlin.runCatching {
                if (map[position.first][position.second + 1] in allowedTiles)
                    (position.first to position.second + 1) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())

            UP -> kotlin.runCatching {
                if (map[position.first - 1][position.second] in allowedTiles)
                    (position.first - 1 to position.second) to facing
                else wrapCube()
            }.getOrDefault(wrapCube())
        }

        fun move(): Boolean {
            val nextPosition = nextPosition()
            if (map[nextPosition.first][nextPosition.second] == '.') {
                position = nextPosition
                return true
            }
            return false
        }

        fun moveCube(): Boolean {
            val (nextPosition, nextFacing) = nextPositionCube()
            if (map[nextPosition.first][nextPosition.second] == '.') {
                position = nextPosition
                facing = nextFacing
                return true
            }
            return false
        }

        val password
            get() = (position.first + 1) * 1000 + (position.second + 1) * 4 + facing.value
    }

    private enum class Facing(val value: Int) { DOWN(1), LEFT(2), RIGHT(0), UP(3) }

    private enum class Side {
        A, B, C, D, E, F;

        companion object {
            fun Pair<Int, Int>.toSide(): Side {
                val row = this.first
                val column = this.second
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
