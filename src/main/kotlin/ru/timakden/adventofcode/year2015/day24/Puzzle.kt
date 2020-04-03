package ru.timakden.adventofcode.year2015.day24

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.PowerSet
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        val deferredPartOne = GlobalScope.async { solve(input, PART_ONE) }
        val deferredPartTwo = GlobalScope.async { solve(input, PART_TWO) }

        runBlocking {
            println("Part One: ${deferredPartOne.await()}")
            println("Part Two: ${deferredPartTwo.await()}")
        }
    }
}

fun solve(input: List<Int>, part: Constants.Part): Long {
    val groupWeight = input.sum() / if (part == PART_TWO) 4 else 3
    val powerSet = PowerSet.of(input.toSet())
    val list = powerSet.filter { it.sum() == groupWeight }
    val minSize = list.minBy { it.size }?.size
    val filteredList = list.filter { it.size == minSize }
    val quantumEntanglementList = mutableListOf<Long>()
    filteredList.forEach { set ->
        var quantumEntanglement: Long = 1
        set.forEach { quantumEntanglement *= it }
        quantumEntanglementList.add(quantumEntanglement)
    }

    return quantumEntanglementList.min() ?: 0
}
