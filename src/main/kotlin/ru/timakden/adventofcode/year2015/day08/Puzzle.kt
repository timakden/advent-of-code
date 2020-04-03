package ru.timakden.adventofcode.year2015.day08

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>) = input.sumBy {
    it.length - (it.replace("\\\\\\W|\\\\x\\w{2}".toRegex(), "*").length - 2)
}

fun solvePartTwo(input: List<String>) = input.sumBy {
    it.replace("^\"|\"$".toRegex(), "***")
        .replace("\\\\{2}|\\\\\"".toRegex(), "****")
        .replace("\\\\x".toRegex(), "***")
        .length - it.length
}
