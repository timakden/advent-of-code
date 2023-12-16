package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 23: Opening the Turing Lock](https://adventofcode.com/2015/day/23).
 */
object Day23 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day23")
            println("Part One: ${solve(input).second}")
            println("Part Two: ${solve(input, true).second}")
        }
    }

    fun solve(input: List<String>, isPartTwo: Boolean = false): Pair<Long, Long> {
        var a = if (isPartTwo) 1L else 0L
        var b = 0L
        var currentPosition = 0

        while (currentPosition in 0..input.lastIndex) {
            val instruction = input[currentPosition]

            when (instruction.take(3)) {
                "hlf" -> {
                    if (instruction.endsWith("a")) a /= 2 else b /= 2
                    currentPosition++
                }

                "tpl" -> {
                    if (instruction.endsWith("a")) a *= 3 else b *= 3
                    currentPosition++
                }

                "inc" -> {
                    if (instruction.endsWith("a")) a++ else b++
                    currentPosition++
                }

                "jmp" -> currentPosition += getOffset(instruction)
                "jie" -> {
                    val numberToTest = if (instruction[4] == 'a') a else b
                    currentPosition += if (numberToTest % 2L == 0L) getOffset(instruction) else 1
                }

                "jio" -> {
                    val numberToTest = if (instruction[4] == 'a') a else b
                    currentPosition += if (numberToTest == 1L) getOffset(instruction) else 1
                }
            }
        }

        return a to b
    }

    private fun getOffset(instruction: String) =
        instruction.substring(instruction.indexOfAny(charArrayOf('+', '-'))).toInt()
}
