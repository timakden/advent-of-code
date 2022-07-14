package ru.timakden.aoc.year2015.day25

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input)}")
    }
}

fun solve(input: Pair<Int, Int>): Long {
    val iterations = (1..input.second).sum() + (0 until input.first - 1).sumOf { input.second + it }
    var code = 20151125L
    repeat(iterations - 1) {
        code = generateNextCode(code)
    }
    return code
}

fun generateNextCode(previousCode: Long) = (previousCode * 252533) % 33554393
