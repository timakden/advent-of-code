package ru.timakden.aoc.year2022.day01

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: String) = input.split("\\n\\n".toRegex())
    .maxOfOrNull { str -> str.split("\\n".toRegex()).sumOf { it.toInt() } }

fun solvePartTwo(input: String) = input.split("\\n\\n".toRegex())
    .map { str -> str.split("\\n".toRegex()).sumOf { it.toInt() } }
    .sortedDescending()
    .take(3)
    .sum()
