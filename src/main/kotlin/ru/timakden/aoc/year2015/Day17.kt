package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day17 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day17").map { it.toInt() }
            val containers = getContainers(input, 150)

            println("Part One: ${part1(containers)}")
            println("Part Two: ${part2(containers)}")
        }
    }

    fun part1(containers: List<List<Int>>) = containers.size

    fun part2(containers: List<List<Int>>): Int {
        val minSize = containers.minBy { it.size }.size
        return containers.count { it.size == minSize }
    }

    fun getContainers(input: List<Int>, litersToStore: Int): List<List<Int>> {
        val containers = mutableListOf<List<Int>>()

        (1..(1 shl input.size)).forEach { i ->
            val list = mutableListOf<Int>()

            input.indices.forEach { if (i shr it and 1 > 0) list += input[it] }

            if (list.sum() == litersToStore) containers += list
        }

        return containers
    }
}
