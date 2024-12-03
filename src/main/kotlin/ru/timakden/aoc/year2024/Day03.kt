package ru.timakden.aoc.year2024

import arrow.core.compareTo
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Mull It Over](https://adventofcode.com/2024/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day03")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val mulRegex = "mul\\(\\d+,\\d+\\)".toRegex()
        val numberRegex = "\\d+".toRegex()
        return input.sumOf { line ->
            mulRegex.findAll(line).map { mul ->
                numberRegex.findAll(mul.value).map { it.value.toInt() }.fold(1) { acc, i -> acc * i }
            }.sum()
        }
    }

    fun part2(input: List<String>): Int {
        val instructionRegex = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)".toRegex()
        val numberRegex = "\\d+".toRegex()
        var mulEnabled = true
        var result = 0

        input.forEach { line ->
            instructionRegex.findAll(line).associate {
                it.range to it.value
            }.toSortedMap { o1, o2 ->
                o1.compareTo(o2)
            }.forEach { (_, instruction) ->
                if (instruction == "don't()") mulEnabled = false
                else if (instruction == "do()") mulEnabled = true
                else if (instruction.startsWith("mul") && mulEnabled) {
                    result += numberRegex.findAll(instruction).map { it.value.toInt() }.fold(1) { acc, i -> acc * i }
                }
            }
        }

        return result
    }
}
