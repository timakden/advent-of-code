package ru.timakden.adventofcode.year2015.day10

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, 40).length}")
        println("Part Two: ${solve(input, 50).length}")
    }
    println("Elapsed time: $elapsedTime ms")
}

private fun generateNewSequence(s: String): String {
    val regex = "(\\d)\\1*".toRegex()
    val stringBuilder = StringBuilder()

    regex.findAll(s).forEach { result ->
        stringBuilder.append(result.value.length)
                .append(result.value[0])
    }

    return stringBuilder.toString()
}

fun solve(s: String, numberOfRuns: Int): String {
    if (numberOfRuns > 1) {
        return solve(generateNewSequence(s), numberOfRuns - 1)
    }

    return generateNewSequence(s)
}
