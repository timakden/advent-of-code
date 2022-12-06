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
    val (first, second) = str.split(",").map { range ->
        range.split("-").let { it.first().toInt()..it.last().toInt() }
    }

    if (first.first <= second.first && first.last >= second.last ||
        second.first <= first.first && second.last >= first.last
    ) 1L else 0L
}

fun solvePartTwo(input: List<String>) = input.sumOf { str ->
    val (first, second) = str.split(",").map { range ->
        range.split("-").let { it.first().toInt()..it.last().toInt() }
    }

    if ((first intersect second).isNotEmpty()) 1L else 0L
}
