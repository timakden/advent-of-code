package ru.timakden.adventofcode.year2015.day25

import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input)}")
    }
}

fun solve(input: Pair<Int, Int>): Long {
    val iterations = (1..input.second).sum() + 0.until(input.first - 1).sumBy { input.second + it }
    var code = 20151125L
    repeat(iterations - 1) {
        code = generateNextCode(code)
    }
    return code
}

fun generateNextCode(previousCode: Long) = (previousCode * 252533) % 33554393
