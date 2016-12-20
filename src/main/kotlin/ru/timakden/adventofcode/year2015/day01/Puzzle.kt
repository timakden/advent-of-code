package ru.timakden.adventofcode.year2015.day01

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: String): Int {
    return (input.count { it == '(' } - input.count { it == ')' })
}

fun solvePartTwo(input: String): Int {
    var floor = 0

    input.forEachIndexed { i, c ->
        when (c) {
            '(' -> floor++
            ')' -> floor--
        }

        if (floor == -1) {
            return i + 1
        }
    }

    return 0
}
