package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.isLetter
import ru.timakden.aoc.util.isNumber
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 7: Some Assembly Required](https://adventofcode.com/2015/day/7).
 */
object Day07 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day07").toMutableList()
            val aPart1 = solve(input, listOf("a"))["a"]
            println("Part One: $aPart1")
            input[input.indexOfFirst { "\\d+\\s->\\sb".toRegex().matches(it) }] = "$aPart1 -> b"
            println("Part Two: ${solve(input, listOf("a"))["a"]}")
        }
    }

    fun solve(input: List<String>, wiresToReturn: List<String>): Map<String, UShort> {
        val regex1 = "\\s->\\s".toRegex()
        val regex2 = "\\s(?!or|and|lshift|rshift)".toRegex()
        val map = mutableMapOf<String, UShort>()
        while (map.size != input.size) {
            input.forEach { line ->
                val expressions = line.split(regex1)
                val leftPart = expressions[0].split(regex2)

                when (leftPart.size) {
                    1 -> {
                        // example: 44430 -> b
                        if (leftPart[0].isNumber()) map[expressions[1]] = leftPart[0].toUShort()
                        else if (leftPart[0].isLetter()) map[leftPart[0]]?.let { map[expressions[1]] = it }
                    }

                    2 -> {
                        // example: NOT di -> dj
                        if (leftPart[1].isNumber()) map[expressions[1]] = leftPart[1].toUShort().inv()
                        else if (leftPart[1].isLetter()) map[leftPart[1]]?.let { map[expressions[1]] = it.inv() }
                    }

                    3 -> {
                        // example: dd OR do -> dp
                        val val1 = if (leftPart[0].isNumber()) leftPart[0].toUShort()
                        else if (leftPart[0].isLetter()) map[leftPart[0]]
                        else null

                        val val2 = if (leftPart[2].isNumber()) leftPart[2].toUShort()
                        else if (leftPart[2].isLetter()) map[leftPart[2]]
                        else null

                        if (val1 != null && val2 != null) {
                            when (leftPart[1]) {
                                "AND" -> map[expressions[1]] = val1 and val2
                                "OR" -> map[expressions[1]] = val1 or val2
                                "LSHIFT" -> map[expressions[1]] = val1 shl val2
                                "RSHIFT" -> map[expressions[1]] = val1 shr val2
                            }
                        }
                    }
                }
            }
        }

        return map.filter { it.key in wiresToReturn }
    }

    private infix fun UShort.shl(shift: UShort): UShort = (this.toInt() shl shift.toInt()).toUShort()

    private infix fun UShort.shr(shift: UShort): UShort = (this.toInt() shr shift.toInt()).toUShort()
}
