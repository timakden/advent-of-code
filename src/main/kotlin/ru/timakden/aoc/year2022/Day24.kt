package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

/**
 * [Day 24: Blizzard Basin](https://adventofcode.com/2022/day/24).
 */
object Day24 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day24")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val walls = mutableSetOf<Vector>()
        val blizzards = mutableListOf<Pair<Vector, Char>>()
        val tiles = mutableSetOf<Vector>()
        var y = 0
        var start = Vector(0, 0)
        var goal = Vector(0, 0)
        input.forEach { line ->
            line.indices.forEach { i ->
                when (line[i]) {
                    '#' -> walls += Vector(i, y)
                    '>', '<', '^', 'v' -> {
                        blizzards += Vector(i, y) to line[i]
                        tiles += Vector(i, y)
                    }

                    '.' -> {
                        tiles += Vector(i, y)
                        if (y == 0) start = Vector(i, y)
                        if (y == input.lastIndex) goal = Vector(i, y)
                    }
                }
            }
            ++y
        }
        return traverse(start, goal, walls, blizzards, tiles)
    }

    fun part2(input: List<String>): Int {
        val walls = mutableSetOf<Vector>()
        val blizzards = mutableListOf<Pair<Vector, Char>>()
        val tiles = mutableSetOf<Vector>()
        var y = 0
        var start = Vector(0, 0)
        var goal = Vector(0, 0)
        input.forEach { line ->
            line.indices.forEach { i ->
                when (line[i]) {
                    '#' -> walls += Vector(i, y)
                    '>', '<', '^', 'v' -> {
                        blizzards += Vector(i, y) to line[i]
                        tiles += Vector(i, y)
                    }

                    '.' -> {
                        tiles += Vector(i, y)
                        if (y == 0) start = Vector(i, y)
                        if (y == input.lastIndex) goal = Vector(i, y)
                    }
                }
            }
            ++y
        }

        return traverse(start, goal, walls, blizzards, tiles) + traverse(goal, start, walls, blizzards, tiles) +
                traverse(start, goal, walls, blizzards, tiles)
    }

    private fun traverse(
        start: Vector,
        goal: Vector,
        walls: MutableSet<Vector>,
        blizzards: MutableList<Pair<Vector, Char>>,
        tiles: MutableSet<Vector>
    ): Int {
        var time = 0
        val players = mutableSetOf<Vector>()
        players += start
        val ans: Int
        outer@ while (true) {
            ++time
            blizzards.indices.forEach { i ->
                var mov = when (blizzards[i].second) {
                    '>' -> Vector(1, 0)
                    '<' -> Vector(-1, 0)
                    'v' -> Vector(0, 1)
                    else -> Vector(0, -1)
                }
                if (blizzards[i].first + mov !in walls) {
                    blizzards[i] = blizzards[i].first + mov to blizzards[i].second
                } else {
                    mov = -mov
                    do {
                        blizzards[i] = blizzards[i].first + mov to blizzards[i].second
                    } while (blizzards[i].first + mov !in walls)
                }
            }

            val newPlayers = mutableSetOf<Vector>()
            players.forEach { player ->
                listOf(Vector(1, 0), Vector(-1, 0), Vector(0, -1), Vector(0, 1), Vector(0, 0)).forEach { mov ->
                    if (player + mov !in walls &&
                        player + mov in tiles &&
                        !blizzards.any { p -> p.first == player + mov }
                    ) {
                        newPlayers += player + mov
                    }
                }
            }

            players.clear()
            val sortedPlayers = newPlayers.sortedBy { p -> abs(p.x - goal.x) + abs(p.y + goal.y) }.toList()
            for (i in sortedPlayers.indices) {
                if (i >= 2000)
                    break
                if (sortedPlayers[i].x == goal.x && sortedPlayers[i].y == goal.y) {
                    ans = time
                    break@outer
                }
                players += sortedPlayers[i]
            }
        }
        return ans
    }

    data class Vector(var x: Int, var y: Int) {
        operator fun plus(b: Vector) = Vector(x + b.x, y + b.y)
        operator fun minus(b: Vector) = Vector(x - b.x, y - b.y)
        operator fun times(c: Int) = Vector(x * c, y * c)
        operator fun unaryMinus() = Vector(-x, -y)
    }
}
