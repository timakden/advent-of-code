package ru.timakden.aoc.year2015.day10

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, 40).length}")
        println("Part Two: ${solve(input, 50).length}")
    }
}

fun solve(s: String, numberOfRuns: Int): String {
    val regex = "(\\d)\\1*".toRegex()

    val sequence = generateSequence(s) {
        buildString {
            regex.findAll(it).forEach { matchResult ->
                append(matchResult.value.length)
                append(matchResult.value[0])
            }
        }
    }

    return sequence.elementAt(numberOfRuns)
}
