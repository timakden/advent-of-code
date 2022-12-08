package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day23 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day23")

            println("Part One: ${solve(input)[1]}")
            println("Part Two: ${solve(input, true)[1]}")
        }
    }

    fun solve(input: List<String>, isPartTwo: Boolean = false): LongArray {
        var a = if (isPartTwo) 1L else 0L
        var b = 0L
        var currentPosition = 0

        while (currentPosition >= 0 && currentPosition <= input.lastIndex) {
            val instruction = input[currentPosition]

            when (instruction.substring(0..2)) {
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

        return longArrayOf(a, b)
    }

    private fun getOffset(instruction: String) =
        instruction.substring(instruction.indexOfAny(charArrayOf('+', '-'))).toInt()
}
