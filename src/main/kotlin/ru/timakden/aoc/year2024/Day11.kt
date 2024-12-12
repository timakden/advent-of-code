package ru.timakden.aoc.year2024

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 11: Plutonian Pebbles](https://adventofcode.com/2024/day/11).
 */
object Day11 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day11").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String) = input.split(" ").map { it.toLong() }.sumOf { count(25, 0, it) }

    fun part2(input: String): Long {
        cache.clear()
        return input.split(" ").map { it.toLong() }.sumOf { count(75, 0, it) }
    }

    private val cache = hashMapOf<Pair<Int, Long>, Long>()

    private fun count(limit: Int, rounds: Int, stone: Long): Long {
        if (rounds == limit) return 1
        cache[rounds to stone]?.let { return it }
        cache[rounds to stone] = if (stone == 0L) {
            count(limit, rounds + 1, 1)
        } else if (stone.toString().length % 2 == 0) {
            val stone1 = stone.toString().substring(0, stone.toString().length / 2)
            val stone2 = stone.toString().substring(stone.toString().length / 2)
            count(limit, rounds + 1, stone1.toLong()) + count(limit, rounds + 1, stone2.toLong())
        } else count(limit, rounds + 1, stone * 2024)
        return cache[rounds to stone]!!
    }
}
