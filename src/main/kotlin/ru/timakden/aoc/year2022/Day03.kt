package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Rucksack Reorganization](https://adventofcode.com/2022/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day03")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.sumOf { s ->
        val (firstCompartment, secondCompartment) = s.chunked(s.length / 2).map { it.toSet() }
        val sameItems = firstCompartment intersect secondCompartment
        sameItems.sumOf { priorities[it] ?: 0 }
    }

    fun part2(input: List<String>) = input.chunked(3).sumOf { s ->
        val (first, second, third) = s.map { it.toSet() }
        val sameItems = first intersect second intersect third
        sameItems.sumOf { c -> priorities[c] ?: 0 }
    }

    private val priorities = (('a'..'z').zip(1..27) + ('A'..'Z').zip(27..52)).toMap()
}
