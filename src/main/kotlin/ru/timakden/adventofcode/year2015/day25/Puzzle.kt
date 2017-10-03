package ru.timakden.adventofcode.year2015.day25

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: Pair<Int, Int>): Long {
    val iterations = (1..input.second).sum() + 0.until(input.first - 1).sumBy { input.second + it }
    var code = 20151125L
    (1 until iterations).forEach { code = generateNextCode(code) }
    return code
}

fun generateNextCode(previousCode: Long) = (previousCode * 252533) % 33554393
