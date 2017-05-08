package ru.timakden.adventofcode.year2016.day06

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): String {
    var errorCorrectedMessage = ""

    (0..input[0].lastIndex).forEach { i ->
        val map = mutableMapOf<Char, Int>()
        input.forEach {
            var count = map[it[i]] ?: 0
            map[it[i]] = ++count
        }

        val entry = if (partTwo) map.minBy { it.value } else map.maxBy { it.value }
        entry?.let {
            errorCorrectedMessage += it.key.toString()
        }
    }

    return errorCorrectedMessage
}
