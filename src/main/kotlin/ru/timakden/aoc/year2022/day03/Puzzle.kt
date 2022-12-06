package ru.timakden.aoc.year2022.day03

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>) = input.sumOf { s ->
    val (firstCompartment, secondCompartment) = s.chunked(s.length / 2).map { it.toSet() }
    val sameItems = firstCompartment intersect secondCompartment
    sameItems.sumOf { priorities[it] ?: 0 }
}

fun solvePartTwo(input: List<String>) = input.chunked(3).sumOf { s ->
    val (first, second, third) = s.map { it.toSet() }
    val sameItems = first intersect second intersect third
    sameItems.sumOf { c -> priorities[c] ?: 0 }
}

private val priorities = (('a'..'z').zip(1..27) + ('A'..'Z').zip(27..52)).toMap()
