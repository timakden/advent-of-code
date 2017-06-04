package ru.timakden.adventofcode.year2016.day09

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input.filterNot(Char::isWhitespace))}")
        println("Part Two: ${solvePartTwo(input.filterNot(Char::isWhitespace))}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: String, count: Long = 0L): Long {
    if (input.isNullOrEmpty()) return count
    if (input.first() == '(') {
        val markerIndex = input.indexOf(')')
        val marker = input.substring(1, markerIndex)
        val length = marker.substringBefore('x').toInt()
        val times = marker.substringAfter('x').toInt()
        return solvePartOne(input.substring(markerIndex + length + 1), count + times * length)
    } else return solvePartOne(input.substring(1), count + 1)
}

fun solvePartTwo(input: String, count: Long = 0L): Long {
    if (input.isNullOrEmpty()) return count
    if (input.first() == '(') {
        val markerIndex = input.indexOf(')')
        val marker = input.substring(1, markerIndex)
        val length = marker.substringBefore('x').toInt()
        val times = marker.substringAfter('x').toInt()
        return solvePartTwo(input.substring(markerIndex + length + 1),
                count + times * solvePartTwo(input.substring(markerIndex + 1, markerIndex + length + 1)))
    } else return solvePartTwo(input.substring(1), count + 1)
}
