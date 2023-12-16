package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day09.Direction.*
import ru.timakden.aoc.year2022.Day09.Direction.Companion.toDirection
import kotlin.math.absoluteValue

/**
 * [Day 9: Rope Bridge](https://adventofcode.com/2022/day/9).
 */
object Day09 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day09")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = performMotions(MutableList(2) { Point(0, 0) }, input)

    fun part2(input: List<String>) = performMotions(MutableList(10) { Point(0, 0) }, input)

    private fun performMotions(rope: MutableList<Point>, input: List<String>): Int {
        val visitedPositions = mutableSetOf<Point>().apply { add(Point(0, 0)) }

        input.forEach { instruction ->
            val (direction, steps) = instruction.split(' ').let { it.first().toDirection() to it.last().toInt() }

            repeat(steps) {
                rope[0] = moveHead(direction, rope.first())

                (1..rope.lastIndex).forEach { i ->
                    if (needMoveTail(rope[i], rope[i - 1])) {
                        rope[i] = moveTail(rope[i], rope[i - 1])
                        if (i == rope.lastIndex) visitedPositions += rope[i]
                    }
                }
            }
        }

        return visitedPositions.count()
    }

    private fun moveHead(direction: Direction, position: Point) = when (direction) {
        DOWN -> Point(position.x, position.y - 1)
        LEFT -> Point(position.x - 1, position.y)
        RIGHT -> Point(position.x + 1, position.y)
        UP -> Point(position.x, position.y + 1)
    }

    private fun needMoveTail(tail: Point, head: Point) =
        (tail.x - head.x).absoluteValue > 1 || (tail.y - head.y).absoluteValue > 1

    private fun moveTail(tail: Point, head: Point): Point {
        val x = when {
            tail.x == head.x -> tail.x
            tail.x < head.x -> tail.x + 1
            else -> tail.x - 1
        }

        val y = when {
            tail.y == head.y -> tail.y
            tail.y < head.y -> tail.y + 1
            else -> tail.y - 1
        }

        return Point(x, y)
    }

    private enum class Direction {
        DOWN, LEFT, RIGHT, UP;

        companion object {
            fun String.toDirection() = when (this) {
                "D" -> DOWN
                "L" -> LEFT
                "R" -> RIGHT
                "U" -> UP
                else -> error("Unsupported direction")
            }
        }
    }
}
