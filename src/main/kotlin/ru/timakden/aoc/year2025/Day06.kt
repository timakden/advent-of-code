package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.properties.Delegates

/**
 * [Day 6: Trash Compactor](https://adventofcode.com/2025/day/6).
 */
object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val worksheet = input.map { it.trim().split("\\s+".toRegex()) }
        var grandTotal = 0L

        for (column in worksheet.first().indices) {
            val operation = worksheet[worksheet.lastIndex][column]
            val numbers = mutableListOf<Long>()
            for (row in 0..<worksheet.lastIndex) {
                numbers += worksheet[row][column].toLong()
            }

            grandTotal += when (operation) {
                "+" -> numbers.reduce { acc, l -> acc + l }
                "*" -> numbers.reduce { acc, l -> acc * l }
                else -> 0L
            }
        }

        return grandTotal
    }

    fun part2(input: List<String>): Long {
        val separatorIndices = input.separatorIndices()
        val width = input.first().length
        var grandTotal = 0L
        val numbers = mutableListOf<Long>()
        var operation: Char by Delegates.notNull()

        for (column in 0..width) {
            if (column in separatorIndices.plus(width)) {
                grandTotal += when (operation) {
                    '+' -> numbers.reduce { acc, l -> acc + l }
                    '*' -> numbers.reduce { acc, l -> acc * l }
                    else -> 0L
                }
                numbers.clear()
            } else {
                val currentNumber = buildString {
                    for (row in input.indices) {
                        val charAtPos = runCatching { input[row][column] }.getOrNull() ?: continue

                        if (charAtPos == '+' || charAtPos == '*') {
                            operation = charAtPos
                        } else {
                            append(charAtPos)
                        }
                    }
                }

                runCatching { currentNumber.trim().toLong() }.onSuccess { numbers += it }
            }
        }
        return grandTotal
    }

    private fun List<String>.separatorIndices(): Set<Int> {
        val spaces = mutableListOf<Int>()
        this.forEach { row ->
            row.forEachIndexed { columnIndex, ch ->
                if (ch == ' ') spaces += columnIndex
            }
        }

        return (spaces.groupingBy { it }.eachCount().filter { it.value == size }.keys)
    }
}
