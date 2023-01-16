package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day02.Outcome.*
import ru.timakden.aoc.year2022.Day02.Shape.Companion.canDefeat
import ru.timakden.aoc.year2022.Day02.Shape.Companion.getShapeByCondition
import ru.timakden.aoc.year2022.Day02.Shape.Companion.toShape
import kotlin.time.ExperimentalTime

object Day02 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { round ->
            val (opponent, player) = round.split("\\s+".toRegex()).let { it.first().toShape() to it.last().toShape() }

            player.score + when {
                opponent == player -> DRAW.score
                player.canDefeat(opponent) -> WIN.score
                else -> DEFEAT.score
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { round ->
            val (opponent, player) = round.split("\\s+".toRegex()).let {
                val shape = it.first().toShape()
                shape to shape.getShapeByCondition(it.last())
            }

            player.score + when {
                opponent == player -> DRAW.score
                player.canDefeat(opponent) -> WIN.score
                else -> DEFEAT.score
            }
        }
    }

    private enum class Shape(val score: Int) {
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
                    else -> error("Unsupported shape")
                }
            }

            fun Shape.canDefeat(other: Shape) = this == ROCK && other == SCISSORS ||
                    this == PAPER && other == ROCK ||
                    this == SCISSORS && other == PAPER

            fun Shape.getShapeByCondition(condition: String) = when (condition) {
                "X" -> this.getLosingShape()
                "Y" -> this
                "Z" -> this.getWinningShape()
                else -> error("Unsupported shape")
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

    private enum class Outcome(val score: Int) {
        DEFEAT(0),
        DRAW(3),
        WIN(6)
    }
}
