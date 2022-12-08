package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.PowerSet
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day24 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day24").map { it.toInt() }

            println("Part One: ${solve(input)}")
            println("Part Two: ${solve(input, true)}")
        }
    }

    fun solve(input: List<Int>, isPartTwo: Boolean = false): Long {
        val groupWeight = input.sum() / if (isPartTwo) 4 else 3
        val list = PowerSet(input.toSet()).filter { it.sum() == groupWeight }
        val minSize = list.minByOrNull { it.size }?.size
        val filteredList = list.filter { it.size == minSize }
        val quantumEntanglementList = mutableListOf<Long>()
        filteredList.forEach { set ->
            var quantumEntanglement = 1L
            set.forEach { quantumEntanglement *= it }
            quantumEntanglementList.add(quantumEntanglement)
        }

        return quantumEntanglementList.minOrNull() ?: 0
    }
}
