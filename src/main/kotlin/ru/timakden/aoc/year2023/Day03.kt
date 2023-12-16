package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Gear Ratios](https://adventofcode.com/2023/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day03")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val digits = "\\d+".toRegex()
        val partNumbers = mutableListOf<Int>()
        input.forEachIndexed { row, s ->
            val numbers = digits.findAll(s)
                .map { it.range to it.value.toInt() }
                .filter { (range, _) ->
                    val adjacentCells = (range.first - 1..range.last + 1).flatMap {
                        listOf(row - 1 to it, row to it, row + 1 to it)
                    }

                    adjacentCells.forEach { (x, y) ->
                        runCatching {
                            if (!input[x][y].isDigit() && input[x][y] != '.') return@filter true
                        }
                    }
                    false
                }

            partNumbers += numbers.map { it.second }
        }

        return partNumbers.sum()
    }

    fun part2(input: List<String>): Int {
        val digits = "\\d+".toRegex()
        val numbersWithCoordinates = input.flatMapIndexed { row, s ->
            digits.findAll(s).map { (row to it.range) to it.value.toInt() }
        }.toMap()
        val gearRatios = mutableListOf<Int>()

        input.forEachIndexed { row, s ->
            val asteriskIndexes = s.mapIndexedNotNull { index: Int, c: Char -> if (c == '*') index else null }

            asteriskIndexes.forEach { index ->
                val adjacentNumbers = numbersWithCoordinates.filterKeys { (x, range) ->
                    x in row - 1..row + 1 && range.any { it in index - 1..index + 1 }
                }

                if (adjacentNumbers.values.size == 2) {
                    gearRatios += adjacentNumbers.values.fold(1) { acc, i -> i * acc }
                }
            }


        }

        return gearRatios.sum()
    }
}
