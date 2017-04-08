package ru.timakden.adventofcode.year2015.day05

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: List<String>) = input.count(::isStringNicePartOne)
fun solvePartTwo(input: List<String>) = input.count(::isStringNicePartTwo)

private fun isStringNicePartOne(s: String): Boolean {
    val regex1 = "(.*[aeiou]){3}".toRegex()
    val regex2 = "(.)\\1".toRegex()
    val regex3 = "ab|cd|pq|xy".toRegex()

    return regex1.containsMatchIn(s) && regex2.containsMatchIn(s) && !regex3.containsMatchIn(s)
}

private fun isStringNicePartTwo(s: String): Boolean {
    val regex1 = "(.{2}).*\\1".toRegex()
    val regex2 = "(.).\\1".toRegex()

    return regex1.containsMatchIn(s) && regex2.containsMatchIn(s)
}
