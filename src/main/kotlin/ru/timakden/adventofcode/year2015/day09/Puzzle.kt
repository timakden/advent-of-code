package ru.timakden.adventofcode.year2015.day09

import ru.timakden.adventofcode.listPermutations
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): Int {
    var result = if (partTwo) Int.MIN_VALUE else Int.MAX_VALUE
    val locations = mutableSetOf<String>()
    val distances = mutableMapOf<String, Int>()

    input.apply {
        mapTo(locations, { it.substringBefore(" to ") })
        mapTo(locations, { it.substringAfter(" to ").substringBefore(" = ") })

        associateByTo(distances,
                { it.substringBefore(" = ").replace(" to ", "") },
                { it.substringAfter(" = ").toInt() })

        associateByTo(distances,
                { it.substringAfter(" to ").substringBefore(" = ") + it.substringBefore(" to ") },
                { it.substringAfter(" = ").toInt() })
    }

    val routes = listPermutations(locations.toMutableList())

    routes.forEach { route ->
        val length = route.indices
                .filter { it != route.size - 1 }
                .sumBy { distances[route[it] + route[it + 1]] ?: 0 }

        when {
            partTwo -> if (length > result) result = length
            !partTwo -> if (length < result) result = length
        }
    }
    return result
}
