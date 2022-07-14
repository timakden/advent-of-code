package ru.timakden.aoc.year2015.day09

import ru.timakden.aoc.util.Constants
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.Permutations
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Constants.Part): Int {
    var result = if (part == PART_TWO) Int.MIN_VALUE else Int.MAX_VALUE
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
            .filter { it != list.size - 1 }
            .sumOf { distances[list[it] + list[it + 1]] ?: 0 }

        when (part) {
            PART_ONE -> if (length < result) result = length
            PART_TWO -> if (length > result) result = length
        }
    }
    return result
}
