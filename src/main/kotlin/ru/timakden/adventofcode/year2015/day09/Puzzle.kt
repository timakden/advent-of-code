package ru.timakden.adventofcode.year2015.day09

import ru.timakden.adventofcode.Permutations
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
}

fun solve(input: List<String>, partTwo: Boolean): Int {
    var result = if (partTwo) Int.MIN_VALUE else Int.MAX_VALUE
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
            .sumBy { distances[list[it] + list[it + 1]] ?: 0 }

        when {
            partTwo -> if (length > result) result = length
            else -> if (length < result) result = length
        }
    }
    return result
}
