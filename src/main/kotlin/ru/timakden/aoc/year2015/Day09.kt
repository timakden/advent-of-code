package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.Permutations
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day09 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day09")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        var result = Int.MAX_VALUE
        val locations = mutableSetOf<String>()
        val distances = mutableMapOf<String, Int>()

        input.apply {
            mapTo(locations) { it.substringBefore(" to ") }
            mapTo(locations) { it.substringAfter(" to ").substringBefore(" = ") }

            associateByTo(distances,
                { it.substringBefore(" = ").replace(" to ", "") },
                { it.substringAfter(" = ").toInt() })

            associateByTo(distances,
                { it.substringAfter(" to ").substringBefore(" = ") + it.substringBefore(" to ") },
                { it.substringAfter(" = ").toInt() })
        }

        val routes = Permutations.of(locations)

        routes.forEach { route ->
            val list = route.toList()
            val length = list.indices
                .filter { it != list.lastIndex }
                .sumOf { distances[list[it] + list[it + 1]] ?: 0 }

            if (length < result) result = length
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = Int.MIN_VALUE
        val locations = mutableSetOf<String>()
        val distances = mutableMapOf<String, Int>()

        input.apply {
            mapTo(locations) { it.substringBefore(" to ") }
            mapTo(locations) { it.substringAfter(" to ").substringBefore(" = ") }

            associateByTo(distances,
                { it.substringBefore(" = ").replace(" to ", "") },
                { it.substringAfter(" = ").toInt() })

            associateByTo(distances,
                { it.substringAfter(" to ").substringBefore(" = ") + it.substringBefore(" to ") },
                { it.substringAfter(" = ").toInt() })
        }

        val routes = Permutations.of(locations)

        routes.forEach { route ->
            val list = route.toList()
            val length = list.indices
                .filter { it != list.lastIndex }
                .sumOf { distances[list[it] + list[it + 1]] ?: 0 }

            if (length > result) result = length
        }
        return result
    }
}
