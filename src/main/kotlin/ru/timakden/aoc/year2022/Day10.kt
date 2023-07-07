package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day10")
            println("Part One: ${part1(input)}")
            println("Part Two:")
            part2(input)
        }
    }

    fun part1(input: List<String>): Int {
        var cycle = 0
        var x = 1
        var signalStrength = 0
        val cycles = listOf(20, 60, 100, 140, 180, 220)

        fun finishCycle() {
            if (++cycle in cycles) signalStrength += x * cycle
        }

        input.forEach { instruction ->
            if (instruction == "noop") finishCycle() else {
                finishCycle()
                finishCycle()
                x += instruction.substringAfter(' ').toInt()
            }
        }

        return signalStrength
    }

    fun part2(input: List<String>) {
        var cycle = 0
        var x = 1
        val crt = List(6) { MutableList(40) { '.' } }

        fun finishCycle() {
            val row = cycle / 40
            val column = cycle - row * 40

            if (column in (x - 1)..(x + 1)) crt[row][column] = '#'

            cycle++
        }

        input.forEach { instruction ->
            if (instruction == "noop") finishCycle() else {
                finishCycle()
                finishCycle()
                x += instruction.substringAfter(' ').toInt()
            }
        }

        crt.forEach { row ->
            row.forEach { print(it) }
            println()
        }
    }
}
