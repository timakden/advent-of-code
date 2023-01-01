package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day20 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day20").single().toInt()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: Int): Int {
        val presentsPerElf = 10

        val houses = IntArray(1000000)

        (1..houses.lastIndex).forEach {
            var i = it
            while (i <= houses.lastIndex) {
                houses[i] += presentsPerElf * it
                i += it
            }
        }

        return houses.indexOfFirst { it >= input }
    }

    fun part2(input: Int): Int {
        val presentsPerElf = 11

        val houses = IntArray(1000000)

        (1..houses.lastIndex).forEach {
            var count = 0
            var i = it
            while (i <= houses.lastIndex) {
                houses[i] += presentsPerElf * it
                if (++count == 50) break

                i += it
            }
        }

        return houses.indexOfFirst { it >= input }
    }
}
