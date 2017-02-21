package ru.timakden.adventofcode.year2015.day24

import com.google.common.collect.Sets
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<Int>, partTwo: Boolean): Long {
    val groupWeight = input.sum() / if (partTwo) 4 else 3
    val powerSet = Sets.powerSet(input.toSet())
    val list = powerSet.filter { it.sum() == groupWeight }
    val minSize = list.minBy { it.size }?.size
    val filteredList = list.filter { it.size == minSize }
    val quantumEntanglementList = mutableListOf<Long>()
    filteredList.forEach {
        var quantumEntanglement: Long = 1
        it.forEach { quantumEntanglement *= it }
        quantumEntanglementList.add(quantumEntanglement)
    }

    return quantumEntanglementList.min() ?: 0
}
