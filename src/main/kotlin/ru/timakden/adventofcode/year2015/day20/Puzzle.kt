package ru.timakden.adventofcode.year2015.day20

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: Int, partTwo: Boolean): Int {
    val presentsPerElf = if (partTwo) 11 else 10

    val houses = IntArray(1000000)

    (1..houses.lastIndex).forEach {
        var count = 0
        var i = it
        while (i <= houses.lastIndex) {
            houses[i] += presentsPerElf * it
            if (partTwo && ++count == 50) {
                break
            }

            i += it
        }
    }

    var index = houses.indexOfFirst { it == input }

    if (index == -1) {
        index = houses.indexOfFirst { it > input }
    }

    return index
}
