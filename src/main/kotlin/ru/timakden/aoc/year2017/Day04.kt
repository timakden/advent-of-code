package ru.timakden.aoc.year2017

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 4: High-Entropy Passphrases](https://adventofcode.com/2017/day/4).
 */
object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2017/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        fun String.isValid() = this.split(" ").let { word -> word.distinct().size == word.size }

        return input.count { it.isValid() }
    }

    fun part2(input: List<String>): Int {
        fun String.isValid(): Boolean {
            val words = this.split(" ")

            for (i in 0..<words.lastIndex) {
                val first = words[i].toCharArray().sortedArray()
                for (j in i + 1..words.lastIndex) {
                    val second = words[j].toCharArray().sortedArray()
                    if (first.contentEquals(second)) return false
                }
            }
            return true
        }

        return input.count { it.isValid() }
    }
}
