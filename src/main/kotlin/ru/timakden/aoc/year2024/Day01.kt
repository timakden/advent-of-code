package ru.timakden.aoc.year2024

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

/**
 * [Day 1: Historian Hysteria](https://adventofcode.com/2024/day/1).
 */
object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val leftList = input.map { line -> line.split("   ").map { it.toInt() }[0] }.sorted()
        val rightList = input.map { line -> line.split("   ").map { it.toInt() }[1] }.sorted()
        return leftList.zip(rightList).sumOf { (left, right) -> abs(left - right) }
    }

    fun part2(input: List<String>): Int {
        val leftList = input.map { line -> line.split("   ").map { it.toInt() }[0] }
        val rightList = input.map { line -> line.split("   ").map { it.toInt() }[1] }
        return leftList.fold(0) { acc, i -> acc + i * rightList.count { i == it } }
    }
}
