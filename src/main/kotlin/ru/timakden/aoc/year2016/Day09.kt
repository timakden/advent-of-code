package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day09 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day09").single().filterNot { it.isWhitespace() }
            println("Part One: ${part1(input)}")
            println("Part One: ${part2(input)}")
        }
    }

    tailrec fun part1(input: String, count: Long = 0L): Long {
        if (input.isEmpty()) return count
        return if (input.first() == '(') {
            val markerIndex = input.indexOf(')')
            val marker = input.substring(1, markerIndex)
            val length = marker.substringBefore('x').toInt()
            val times = marker.substringAfter('x').toInt()
            part1(input.substring(markerIndex + length + 1), count + times * length)
        } else part1(input.substring(1), count + 1)
    }

    fun part2(input: String, count: Long = 0L): Long {
        if (input.isEmpty()) return count
        return if (input.first() == '(') {
            val markerIndex = input.indexOf(')')
            val marker = input.substring(1, markerIndex)
            val length = marker.substringBefore('x').toInt()
            val times = marker.substringAfter('x').toInt()
            part2(
                input.substring(markerIndex + length + 1),
                count + times * part2(input.substring(markerIndex + 1, markerIndex + length + 1))
            )
        } else part2(input.substring(1), count + 1)
    }
}
