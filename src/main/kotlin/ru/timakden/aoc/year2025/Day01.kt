package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 1: Secret Entrance](https://adventofcode.com/2025/day/1).
 */
object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        var dial = 50
        var zeroes = 0

        input.forEach {
            val rotation = it.first()
            val distance = it.drop(1).toInt()

            repeat(distance) {
                if (rotation == 'L') {
                    dial--
                    if (dial < 0) dial = 99
                } else if (rotation == 'R') {
                    dial++
                    if (dial > 99) dial = 0
                }
            }

            if (dial == 0) zeroes++
        }

        return zeroes
    }

    fun part2(input: List<String>): Int {
        var dial = 50
        var zeroes = 0

        input.forEach { instruction ->
            val rotation = instruction.first()
            val distance = instruction.drop(1).toInt()

            repeat(distance) {
                if (rotation == 'L') {
                    dial--
                    if (dial == 0) zeroes++
                    if (dial < 0) dial = 99
                } else if (rotation == 'R') {
                    dial++
                    if (dial > 99) {
                        dial = 0
                        zeroes++
                    }
                }
            }
        }

        return zeroes
    }
}
