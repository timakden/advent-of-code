package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 17: No Such Thing as Too Much](https://adventofcode.com/2015/day/17).
 */
object Day17 {
    @JvmStatic
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

    fun getContainers(input: List<Int>, litersToStore: Int) = (1..(1 shl input.size)).map { i ->
        input.indices.filter { (i shr it and 1 > 0) }.map { input[it] }
    }.filter { it.sum() == litersToStore }
}
