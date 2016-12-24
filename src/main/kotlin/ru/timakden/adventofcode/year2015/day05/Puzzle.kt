package ru.timakden.adventofcode.year2015.day05

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${input.count(::isNicePartOne)}")
        println("Part Two: ${input.count(::isNicePartTwo)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun isNicePartOne(s: String): Boolean {
    val regex1 = ".*[aeiou].*[aeiou].*[aeiou].*".toRegex()
    val regex2 = "(\\w)\\1".toRegex()
    val regex3 = "ab|cd|pq|xy".toRegex()

    return regex1.containsMatchIn(s) && regex2.containsMatchIn(s) && !regex3.containsMatchIn(s)
}

fun isNicePartTwo(s: String): Boolean {
    val regex1 = "(\\w{2})\\w*\\1".toRegex()
    val regex2 = "(\\w)\\w\\1".toRegex()

    return regex1.containsMatchIn(s) && regex2.containsMatchIn(s)
}
