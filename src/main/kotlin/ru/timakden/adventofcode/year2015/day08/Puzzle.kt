package ru.timakden.adventofcode.year2015.day08

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: List<String>) = input.sumBy { it ->
    it.length - (it.replace("\\\\\\W|\\\\x\\w{2}".toRegex(), "*").length - 2)
}

fun solvePartTwo(input: List<String>) = input.sumBy { it ->
    it.replace("^\"|\"$".toRegex(), "***").replace("\\\\{2}|\\\\\"".toRegex(), "****")
            .replace("\\\\x".toRegex(), "***").length - it.length
}
