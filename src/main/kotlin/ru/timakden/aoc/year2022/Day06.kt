package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day06").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String) = countCharacters(input, 4)

    fun part2(input: String) = countCharacters(input, 14)

    private fun countCharacters(input: String, distinctCharacters: Int): Int {
        input.indices.forEach { i ->
            if (i >= distinctCharacters - 1) {
                if (input.substring((i - (distinctCharacters - 1))..i).toSet().size == distinctCharacters) {
                    return i + 1
                }
            }
        }

        return 0
    }
}
