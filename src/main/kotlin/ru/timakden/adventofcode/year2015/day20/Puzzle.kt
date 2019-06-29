package ru.timakden.adventofcode.year2015.day20

import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
}

fun solve(input: Int, partTwo: Boolean): Int {
    val presentsPerElf = if (partTwo) 11 else 10

    val houses = IntArray(1000000)

    (1..houses.lastIndex).forEach {
        var count = 0
        var i = it
        while (i <= houses.lastIndex) {
            houses[i] += presentsPerElf * it
            if (partTwo && ++count == 50) break

            i += it
        }
    }

    return houses.indexOfFirst { it >= input }
}
