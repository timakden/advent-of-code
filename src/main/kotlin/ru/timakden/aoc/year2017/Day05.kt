package ru.timakden.aoc.year2017

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 5: A Maze of Twisty Trampolines, All Alike](https://adventofcode.com/2017/day/5).
 */
object Day05 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2017/Day05")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val instructions = input.map { it.toInt() }.toMutableList()
        var index = 0
        var step = 0

        while (true) {
            if (index !in instructions.indices) break

            val instruction = instructions[index]
            instructions[index] += 1
            index += instruction
            step++
        }

        return step
    }

    fun part2(input: List<String>): Int {
        val instructions = input.map { it.toInt() }.toMutableList()
        var index = 0
        var step = 0

        while (true) {
            if (index !in instructions.indices) break

            val instruction = instructions[index]
            instructions[index] = if (instruction >= 3) instruction - 1 else instruction + 1
            index += instruction
            step++
        }

        return step
    }
}
