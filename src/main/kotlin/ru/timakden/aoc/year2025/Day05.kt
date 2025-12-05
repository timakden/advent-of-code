package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 5: Cafeteria](https://adventofcode.com/2025/day/5).
 */
object Day05 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day05")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val ranges = mutableListOf<LongRange>()
        val ingredients = mutableListOf<Long>()
        input.forEach { line ->
            if (line.isNotBlank()) {
                if (line.contains('-')) {
                    val (first, second) = line.split('-')
                    ranges += first.toLong()..second.toLong()
                } else {
                    ingredients += line.toLong()
                }
            }
        }

        return ingredients.count { ingredient -> mergeRanges(ranges).any { ingredient in it } }
    }

    fun part2(input: List<String>): Long {
        val ranges = mutableListOf<LongRange>()
        input.forEach { line ->
            if (line.isNotBlank() && line.contains('-')) {
                val (first, second) = line.split('-')
                ranges += first.toLong()..second.toLong()
            }
        }

        return mergeRanges(ranges).sumOf { it.last - it.first + 1 }
    }

    private fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
        if (ranges.isEmpty()) return emptyList()

        val sortedRanges = ranges.sortedBy { it.first }
        val merged = mutableListOf<LongRange>()
        var current = sortedRanges.first()

        for (i in 1 until sortedRanges.size) {
            val next = sortedRanges[i]
            // Check if ranges overlap or are adjacent (e.g. 1..5 and 6..10 can merge to 1..10)
            if (next.first <= current.last + 1) {
                current = current.first..maxOf(current.last, next.last)
            } else {
                merged += current
                current = next
            }
        }
        merged += current
        return merged
    }
}
