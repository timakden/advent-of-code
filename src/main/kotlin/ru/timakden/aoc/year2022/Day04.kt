package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day04 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf { str ->
        val (first, second) = str.split(',').map { range ->
            range.split('-').let { it.first().toInt()..it.last().toInt() }
        }

        if (first.first <= second.first && first.last >= second.last ||
            second.first <= first.first && second.last >= first.last
        ) 1L else 0L
    }

    fun part2(input: List<String>) = input.sumOf { str ->
        val (first, second) = str.split(',').map { range ->
            range.split('-').let { it.first().toInt()..it.last().toInt() }
        }

        if ((first intersect second).isNotEmpty()) 1L else 0L
    }
}
