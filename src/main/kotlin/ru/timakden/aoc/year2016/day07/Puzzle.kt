package ru.timakden.aoc.year2016.day07

import ru.timakden.aoc.util.Constants.Part
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Part): Int =
    input.count { if (part == PART_TWO) isSupportSsl(it) else isSupportTls(it) }

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

            if (ip.contains(expectedBab)) {
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
        if (c == '[') openingBrackets.add(index)
        if (c == ']') closingBrackets.add(index)
    }

    val bracketRanges = mutableListOf<IntRange>()
    openingBrackets.forEachIndexed { index, i ->
        bracketRanges.add(IntRange(i, closingBrackets[index]))
    }

    return bracketRanges
}
