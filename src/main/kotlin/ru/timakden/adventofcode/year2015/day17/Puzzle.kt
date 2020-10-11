package ru.timakden.adventofcode.year2015.day17

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        val containers = getContainers(input, 150)
        println("Part One: ${solve(containers, PART_ONE)}")
        println("Part Two: ${solve(containers, PART_TWO)}")
    }
}

fun solve(containers: List<List<Int>>, part: Constants.Part): Int {
    if (part == PART_TWO) {
        val minSize = containers.minByOrNull { it.size }?.size
        return containers.count { it.size == minSize }
    }
    return containers.size
}

fun getContainers(input: List<Int>, litersToStore: Int): List<List<Int>> {
    val containers = mutableListOf<List<Int>>()

    for (i in 1..(1 shl input.size)) {
        val list = mutableListOf<Int>()

        input.indices.forEach { if (i shr it and 1 > 0) list += input[it] }

        if (list.sum() == litersToStore) containers += list
    }

    return containers
}
