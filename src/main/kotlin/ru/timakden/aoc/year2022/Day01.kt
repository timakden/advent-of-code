package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day01 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = countCalories(input).max()

    fun part2(input: List<String>) = countCalories(input).sortedDescending().take(3).sum()

    private fun countCalories(input: List<String>): List<Int> {
        return input.fold(mutableListOf(mutableListOf<String>())) { acc, item ->
            if (item.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(item)
            }
            acc
        }.map { list -> list.sumOf { it.toInt() } }
    }
}
