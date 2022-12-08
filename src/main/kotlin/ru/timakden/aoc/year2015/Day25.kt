package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day25 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = "\\d+".toRegex().findAll(readInput("year2015/Day25").single())
                .map { it.value }
                .let { it.first().toInt() to it.last().toInt() }

            println("Part One: ${solve(input)}")
        }
    }

    fun solve(input: Pair<Int, Int>): Long {
        val iterations = (1..input.second).sum() + (0 until input.first - 1).sumOf { input.second + it }
        var code = 20151125L
        repeat(iterations - 1) {
            code = generateNextCode(code)
        }
        return code
    }

    private fun generateNextCode(previousCode: Long) = (previousCode * 252533) % 33554393
}
