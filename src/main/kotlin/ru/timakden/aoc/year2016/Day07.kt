package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 7: Internet Protocol Version 7](https://adventofcode.com/2016/day/7).
 */
object Day07 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day07")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.count { isSupportTls(it) }

    fun part2(input: List<String>) = input.count { isSupportSsl(it) }

    private fun isSupportTls(ip: String): Boolean {
        val firstRequirement = "(\\w)(?!\\1)(\\w)\\2\\1".toRegex().containsMatchIn(ip)
        val secondRequirement = !"\\[\\w*(\\w)(?!\\1)(\\w)\\2\\1\\w*]".toRegex().containsMatchIn(ip)
        return firstRequirement && secondRequirement
    }

    private fun isSupportSsl(ip: String): Boolean {
        val bracketRanges = getBracketRanges(ip)
        var result = false
        (0..ip.lastIndex - 2).forEach { outer ->
            if (ip[outer] == ip[outer + 2] && ip[outer] != ip[outer + 1]) {
                val aba = ip.substring(outer, outer + 3)
                val expectedBab = aba[1].toString() + aba[0].toString() + aba[1].toString()

                if (expectedBab in ip) {
                    (0..ip.lastIndex - 2).forEach { inner ->
                        val bab = ip.substring(inner, inner + 3)
                        if (bab == expectedBab) {
                            var abaNotInBrackets = true
                            var babInBrackets = false

                            bracketRanges.forEach { range ->
                                if (outer in range) abaNotInBrackets = false
                                if (inner in range) babInBrackets = true
                            }

                            result = result || abaNotInBrackets && babInBrackets
                        }
                    }
                }
            }
        }

        return result
    }

    private fun getBracketRanges(ip: String): List<IntRange> {
        val openingBrackets = mutableListOf<Int>()
        val closingBrackets = mutableListOf<Int>()

        ip.forEachIndexed { index, c ->
            if (c == '[') openingBrackets += index
            if (c == ']') closingBrackets += index
        }

        return openingBrackets.mapIndexed { index, i -> i..closingBrackets[index] }
    }
}
