package ru.timakden.adventofcode.year2015.day02

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: List<String>): Int {
    var wrappingPaper = 0
    input.forEach {
        val dimensions = mutableListOf<Int>()
        it.split("x").forEach { dimensions.add(it.toInt()) }
        val areas = listOf(2 * dimensions[0] * dimensions[1], 2 * dimensions[1] * dimensions[2],
                2 * dimensions[0] * dimensions[2])

        wrappingPaper += areas.sum() + (areas.min() ?: 0) / 2
    }

    return wrappingPaper
}

fun solvePartTwo(input: List<String>): Int {
    var ribbon = 0
    input.forEach {
        val dimensions = mutableListOf<Int>()
        it.split("x").forEach { dimensions.add(it.toInt()) }
        dimensions.sort()

        ribbon += 2 * dimensions[0] + 2 * dimensions[1] + dimensions[0] * dimensions[1] * dimensions[2]
    }

    return ribbon
}
