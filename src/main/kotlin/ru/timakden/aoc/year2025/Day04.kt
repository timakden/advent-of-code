package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 4: Printing Department](https://adventofcode.com/2025/day/4).
 */
object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        var accessibleRolls = 0
        val matrix = input.map { it.toCharArray() }.toTypedArray()

        for (rowIndex in matrix.indices) {
            for (columnIndex in matrix[rowIndex].indices) {
                if (matrix[rowIndex][columnIndex] == '.') continue

                var neighbors = 0

                outer@ for (i in (rowIndex - 1)..(rowIndex + 1)) {
                    for (j in (columnIndex - 1)..(columnIndex + 1)) {
                        if (i == rowIndex && j == columnIndex) continue // skip the current "cell"

                        runCatching { matrix[i][j] }.onSuccess {
                            if (it == '@') neighbors++
                            if (neighbors >= 4) break@outer
                        }
                    }
                }

                if (neighbors < 4) accessibleRolls++
            }
        }

        return accessibleRolls
    }

    fun part2(input: List<String>): Int {
        var totalRemovedRolls = 0
        val matrix = input.map { it.toCharArray() }.toTypedArray()

        while (true) {
            var removedRolls = 0

            for (rowIndex in matrix.indices) {
                for (columnIndex in matrix[rowIndex].indices) {
                    if (matrix[rowIndex][columnIndex] == '.') continue

                    var neighbors = 0

                    outer@ for (i in (rowIndex - 1)..(rowIndex + 1)) {
                        for (j in (columnIndex - 1)..(columnIndex + 1)) {
                            if (i == rowIndex && j == columnIndex) continue // skip the current "cell"

                            runCatching { matrix[i][j] }.onSuccess {
                                if (it == '@') neighbors++
                                if (neighbors >= 4) break@outer
                            }
                        }
                    }

                    if (neighbors < 4) {
                        matrix[rowIndex][columnIndex] = '.'
                        removedRolls++
                    }
                }
            }

            totalRemovedRolls += removedRolls

            if (removedRolls == 0) break
        }

        return totalRemovedRolls
    }
}
