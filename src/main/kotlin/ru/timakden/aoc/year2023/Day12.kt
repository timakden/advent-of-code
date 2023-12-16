package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 12: Hot Springs](https://adventofcode.com/2023/day/12).
 */
object Day12 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day12")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) =
        input.map { parseRow(it) }.sumOf { (springs, damagedSprings) ->
            countArrangements(springs, damagedSprings)
        }

    fun part2(input: List<String>) =
        input.map { parseRow(it) }.map { unfold(it) }.sumOf { (springs, damagedSprings) ->
            countArrangements(springs, damagedSprings)
        }

    private fun countArrangements(
        springs: String,
        damagedSprings: List<Int>,
        cache: MutableMap<Pair<String, List<Int>>, Long> = mutableMapOf()
    ): Long {
        val key = springs to damagedSprings

        cache[key]?.let { return it }

        if (springs.isEmpty()) return if (damagedSprings.isEmpty()) 1 else 0

        return when (springs.first()) {
            '.' -> countArrangements(springs.dropWhile { it == '.' }, damagedSprings, cache)

            '?' -> countArrangements(springs.substring(1), damagedSprings, cache) +
                    countArrangements("#${springs.substring(1)}", damagedSprings, cache)

            '#' -> when {
                damagedSprings.isEmpty() -> 0
                else -> {
                    val thisDamage = damagedSprings.first()
                    val remainingDamage = damagedSprings.drop(1)
                    if (thisDamage <= springs.length && springs.take(thisDamage).none { it == '.' }) {
                        when {
                            thisDamage == springs.length -> if (remainingDamage.isEmpty()) 1 else 0
                            springs[thisDamage] == '#' -> 0
                            else -> countArrangements(springs.drop(thisDamage + 1), remainingDamage, cache)
                        }
                    } else 0
                }
            }

            else -> error("Invalid springs: $springs")
        }.apply {
            cache[key] = this
        }
    }

    private fun unfold(input: Pair<String, List<Int>>): Pair<String, List<Int>> =
        (0..4).joinToString("?") { input.first } to (0..4).flatMap { input.second }

    private fun parseRow(input: String): Pair<String, List<Int>> {
        val (springs, damagedSprings) = input.split(" ")
        return springs to damagedSprings.split(",").map { it.toInt() }
    }
}
