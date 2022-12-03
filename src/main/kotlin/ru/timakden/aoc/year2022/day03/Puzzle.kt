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
    val chunks = s.chunked(s.length / 2)
    val firstCompartment = chunks.first().asIterable().toSet()
    val secondCompartment = chunks.last().asIterable().toSet()
    val sameItems = firstCompartment.intersect(secondCompartment)
    sameItems.sumOf { priorities[it] ?: 0 }
}

fun solvePartTwo(input: List<String>) = input.chunked(3).sumOf {
    val first = it[0].asIterable().toSet()
    val second = it[1].asIterable().toSet()
    val third = it[2].asIterable().toSet()
    val sameItems = first.intersect(second).intersect(third)
    sameItems.sumOf { priorities[it] ?: 0 }
}

private val priorities = (('a'..'z').zip(1..27) + ('A'..'Z').zip(27..52)).toMap()
