package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day23.Direction.*
import ru.timakden.aoc.year2022.Day23.Elf.Companion.fillProposedPositions
import ru.timakden.aoc.year2022.Day23.Elf.Companion.move
import ru.timakden.aoc.year2022.Day23.Elf.Companion.toElves

/**
 * [Day 23: Unstable Diffusion](https://adventofcode.com/2022/day/23).
 */
object Day23 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day23")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val elves = input.toElves()
        val iterator = directionIterator()
        repeat(10) {
            elves.fillProposedPositions(iterator.next())
            elves.move()
        }

        val height = (elves.minOf { it.position.y }..elves.maxOf { it.position.y }).count()
        val width = (elves.minOf { it.position.x }..elves.maxOf { it.position.x }).count()

        return height * width - elves.count()
    }

    fun part2(input: List<String>): Int {
        val elves = input.toElves()
        var round = 1
        val iterator = directionIterator()

        while (true) {
            elves.fillProposedPositions(iterator.next())

            if (elves.all { it.proposedPositions.isEmpty() || it.proposedPositions.size == 4 }) break

            elves.move()
            round++
        }

        return round
    }

    private fun directionIterator() = iterator {
        while (true) {
            yieldAll(
                listOf(
                    listOf(NORTH, SOUTH, WEST, EAST),
                    listOf(SOUTH, WEST, EAST, NORTH),
                    listOf(WEST, EAST, NORTH, SOUTH),
                    listOf(EAST, NORTH, SOUTH, WEST)
                )
            )
        }
    }

    private data class Elf(var position: Point) {
        var proposedPositions: MutableList<Point> = mutableListOf()

        fun proposePosition(elves: List<Elf>, direction: Direction) = when (direction) {
            EAST -> {
                val adjacentPositions = listOf(
                    Point(position.x + 1, position.y),
                    Point(position.x + 1, position.y - 1),
                    Point(position.x + 1, position.y + 1),
                )
                if (elves.any { it.position in adjacentPositions }) null
                else Point(position.x + 1, position.y)
            }

            NORTH -> {
                val adjacentPositions = listOf(
                    Point(position.x, position.y - 1),
                    Point(position.x - 1, position.y - 1),
                    Point(position.x + 1, position.y - 1)
                )
                if (elves.any { it.position in adjacentPositions }) null
                else Point(position.x, position.y - 1)
            }

            SOUTH -> {
                val adjacentPositions = listOf(
                    Point(position.x, position.y + 1),
                    Point(position.x - 1, position.y + 1),
                    Point(position.x + 1, position.y + 1)
                )
                if (elves.any { it.position in adjacentPositions }) null
                else Point(position.x, position.y + 1)
            }

            WEST -> {
                val adjacentPositions = listOf(
                    Point(position.x - 1, position.y),
                    Point(position.x - 1, position.y - 1),
                    Point(position.x - 1, position.y + 1),
                )
                if (elves.any { it.position in adjacentPositions }) null
                else Point(position.x - 1, position.y)
            }
        }

        companion object {
            fun List<String>.toElves(): MutableList<Elf> {
                val elves = mutableListOf<Elf>()
                this.forEachIndexed { row, s ->
                    s.forEachIndexed { column, c ->
                        if (c == '#') elves += Elf(Point(column, row))
                    }
                }
                return elves
            }

            fun List<Elf>.fillProposedPositions(directions: List<Direction>) {
                this.forEach { elf ->
                    for (direction in directions) {
                        val proposedPosition = elf.proposePosition(this, direction)
                        if (proposedPosition != null) {
                            elf.proposedPositions += proposedPosition
                        }
                    }
                }
            }

            fun List<Elf>.move() {
                this.forEach { elf ->
                    if (elf.proposedPositions.size in 1..3) {
                        val proposedPosition = elf.proposedPositions.first()
                        val otherElves = this - elf

                        if (otherElves.none { it.proposedPositions.size in 1..3 && it.proposedPositions.first() == proposedPosition })
                            elf.position = proposedPosition
                    }
                }

                this.forEach { it.proposedPositions = mutableListOf() }
            }
        }
    }

    private enum class Direction { EAST, NORTH, SOUTH, WEST }
}
