package ru.timakden.aoc.year2022.day04

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>) = input.sumOf { str ->
    val (first, second) = str.split(",").let { it.first() to it.last() }

    val firstRange = first.split("-").let { IntRange(it.first().toInt(), it.last().toInt()) }

    val secondRange = second.split("-").let { IntRange(it.first().toInt(), it.last().toInt()) }

    if (firstRange.first <= secondRange.first && firstRange.last >= secondRange.last ||
        secondRange.first <= firstRange.first && secondRange.last >= firstRange.last
    ) 1L else 0L
}

fun solvePartTwo(input: List<String>) = input.sumOf { str ->
    val (first, second) = str.split(",").let { it.first() to it.last() }

    val firstRange = first.split("-").let { IntRange(it.first().toInt(), it.last().toInt()) }

    val secondRange = second.split("-").let { IntRange(it.first().toInt(), it.last().toInt()) }

    if (firstRange.intersect(secondRange).isNotEmpty()) 1L else 0L
}
