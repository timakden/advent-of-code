package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day09.Direction.*
import ru.timakden.aoc.year2022.Day09.Direction.Companion.toDirection
import kotlin.math.absoluteValue

object Day09 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day09")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = performMotions(MutableList(2) { 0 to 0 }, input)

    fun part2(input: List<String>) = performMotions(MutableList(10) { 0 to 0 }, input)

    private fun performMotions(rope: MutableList<Pair<Int, Int>>, input: List<String>): Int {
        val visitedPositions = mutableSetOf<Pair<Int, Int>>().apply { add(0 to 0) }

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

    private fun moveHead(direction: Direction, position: Pair<Int, Int>) = when (direction) {
        DOWN -> position.first to (position.second - 1)
        LEFT -> (position.first - 1) to position.second
        RIGHT -> (position.first + 1) to position.second
        UP -> position.first to (position.second + 1)
    }

    private fun needMoveTail(tail: Pair<Int, Int>, head: Pair<Int, Int>) =
        (tail.first - head.first).absoluteValue > 1 || (tail.second - head.second).absoluteValue > 1

    private fun moveTail(tail: Pair<Int, Int>, head: Pair<Int, Int>): Pair<Int, Int> {
        val x = when {
            tail.first == head.first -> tail.first
            tail.first < head.first -> tail.first + 1
            else -> tail.first - 1
        }

        val y = when {
            tail.second == head.second -> tail.second
            tail.second < head.second -> tail.second + 1
            else -> tail.second - 1
        }

        return x to y
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
