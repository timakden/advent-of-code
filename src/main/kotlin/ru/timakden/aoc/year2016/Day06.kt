package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day06 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): String {
        var errorCorrectedMessage = ""

        (0..input[0].lastIndex).forEach { i ->
            val map = mutableMapOf<Char, Int>()
            input.forEach {
                var count = map[it[i]] ?: 0
                map[it[i]] = ++count
            }

            map.maxByOrNull { it.value }?.let {
                errorCorrectedMessage += it.key.toString()
            }
        }

        return errorCorrectedMessage
    }

    fun part2(input: List<String>): String {
        var errorCorrectedMessage = ""

        (0..input[0].lastIndex).forEach { i ->
            val map = mutableMapOf<Char, Int>()
            input.forEach {
                var count = map[it[i]] ?: 0
                map[it[i]] = ++count
            }

            map.minByOrNull { it.value }?.let {
                errorCorrectedMessage += it.key.toString()
            }
        }

        return errorCorrectedMessage
    }
}
