package ru.timakden.aoc.year2015.day08

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>) = input.sumOf {
    it.length - (it.replace("\\\\\\W|\\\\x\\w{2}".toRegex(), "*").length - 2)
}

fun solvePartTwo(input: List<String>) = input.sumOf {
    it.replace("^\"|\"$".toRegex(), "***")
        .replace("\\\\{2}|\\\\\"".toRegex(), "****")
        .replace("\\\\x".toRegex(), "***")
        .length - it.length
}
