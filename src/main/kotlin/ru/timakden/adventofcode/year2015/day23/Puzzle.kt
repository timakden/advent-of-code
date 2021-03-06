package ru.timakden.adventofcode.year2015.day23

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)[1]}")
        println("Part Two: ${solve(input, PART_TWO)[1]}")
    }
}

fun solve(input: List<String>, part: Constants.Part): IntArray {
    var a = if (part == PART_TWO) 1 else 0
    var b = 0
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
                currentPosition += if (numberToTest % 2 == 0) getOffset(instruction) else 1
            }
            "jio" -> {
                val numberToTest = if (instruction[4] == 'a') a else b
                currentPosition += if (numberToTest == 1) getOffset(instruction) else 1
            }
        }
    }

    return intArrayOf(a, b)
}

private fun getOffset(instruction: String) =
    instruction.substring(instruction.indexOfAny(charArrayOf('+', '-'))).toInt()
