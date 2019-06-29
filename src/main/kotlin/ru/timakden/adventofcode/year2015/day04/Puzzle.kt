package ru.timakden.adventofcode.year2015.day04

import ru.timakden.adventofcode.md5Hex
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
}

fun solve(input: String, partTwo: Boolean): Long {
    var count = 0L
    var encoded: String

    while (true) {
        encoded = md5Hex(input + count.toString())
        if (encoded.startsWith(if (partTwo) "000000" else "00000")) break
        count++
    }

    return count
}
