package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 6: Wait For It](https://adventofcode.com/2023/day/6).
 */
object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val times = Regex("\\d+").findAll(input[0]).map { it.value.toLong() }.toList()
        val recordDistances = Regex("\\d+").findAll(input[1]).map { it.value.toLong() }.toList()
        val races = times.zip(recordDistances).map { (time, recordDistance) -> Race(time, recordDistance) }

        return races.map { race ->
            var waysToWin = 0
            for (holdingTime in 1..<race.time) {
                val boatDistance = (race.time - holdingTime) * holdingTime
                if (boatDistance > race.recordDistance) waysToWin++
            }
            waysToWin
        }.fold(1) { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        val time = Regex("\\d+").findAll(input[0]).map { it.value }.joinToString("").toLong()
        val recordDistance = Regex("\\d+").findAll(input[1]).map { it.value }.joinToString("").toLong()
        val race = Race(time, recordDistance)
        var waysToWin = 0

        for (holdingTime in 1..<race.time) {
            val boatDistance = (race.time - holdingTime) * holdingTime
            if (boatDistance > race.recordDistance) waysToWin++
        }

        return waysToWin
    }

    private data class Race(val time: Long, val recordDistance: Long)
}
