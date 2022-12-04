package ru.timakden.aoc.year2022.day02

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.year2022.day02.Shape.Companion.canDefeat
import ru.timakden.aoc.year2022.day02.Shape.Companion.getShapeByCondition
import ru.timakden.aoc.year2022.day02.Shape.Companion.toShape
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: String): Int {
    return input.split("\\n".toRegex()).sumOf { round ->
        val (opponent, player) = round.split("\\s+".toRegex()).let { it.first().toShape() to it.last().toShape() }

        player.score + when {
            opponent == player -> DRAW
            player.canDefeat(opponent) -> WIN
            else -> DEFEAT
        }
    }
}

fun solvePartTwo(input: String): Int {
    return input.split("\\n".toRegex()).sumOf { round ->
        val (opponent, player) = round.split("\\s+".toRegex()).let {
            val shape = it.first().toShape()
            shape to shape.getShapeByCondition(it.last())
        }

        player.score + when {
            opponent == player -> DRAW
            player.canDefeat(opponent) -> WIN
            else -> DEFEAT
        }
    }
}

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun String.toShape(): Shape {
            return when (this) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCISSORS
                "X" -> ROCK
                "Y" -> PAPER
                "Z" -> SCISSORS
                else -> throw IllegalArgumentException()
            }
        }

        fun Shape.canDefeat(other: Shape) = this == ROCK && other == SCISSORS ||
                this == PAPER && other == ROCK ||
                this == SCISSORS && other == PAPER

        fun Shape.getShapeByCondition(condition: String) = when (condition) {
            "X" -> this.getLosingShape()
            "Y" -> this
            "Z" -> this.getWinningShape()
            else -> throw IllegalArgumentException()
        }

        private fun Shape.getWinningShape() = when (this) {
            ROCK -> PAPER
            PAPER -> SCISSORS
            SCISSORS -> ROCK
        }

        private fun Shape.getLosingShape() = when (this) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        }
    }
}

private const val DRAW = 3
private const val WIN = 6
private const val DEFEAT = 0
