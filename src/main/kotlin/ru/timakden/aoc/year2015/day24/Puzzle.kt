package ru.timakden.aoc.year2015.day24

import ru.timakden.aoc.util.Constants
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.PowerSet
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<Int>, part: Constants.Part): Long {
    val groupWeight = input.sum() / if (part == PART_TWO) 4 else 3
    val list = PowerSet(input.toSet()).filter { it.sum() == groupWeight }
    val minSize = list.minByOrNull { it.size }?.size
    val filteredList = list.filter { it.size == minSize }
    val quantumEntanglementList = mutableListOf<Long>()
    filteredList.forEach { set ->
        var quantumEntanglement: Long = 1
        set.forEach { quantumEntanglement *= it }
        quantumEntanglementList.add(quantumEntanglement)
    }

    return quantumEntanglementList.minOrNull() ?: 0
}
