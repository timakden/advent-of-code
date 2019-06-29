package ru.timakden.adventofcode.year2015.day24

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.timakden.adventofcode.PowerSet
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        val deferredPartOne = GlobalScope.async { solve(input, false) }
        val deferredPartTwo = GlobalScope.async { solve(input, true) }

        runBlocking {
            println("Part One: ${deferredPartOne.await()}")
            println("Part Two: ${deferredPartTwo.await()}")
        }
    }
}

fun solve(input: List<Int>, partTwo: Boolean): Long {
    val groupWeight = input.sum() / if (partTwo) 4 else 3
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
