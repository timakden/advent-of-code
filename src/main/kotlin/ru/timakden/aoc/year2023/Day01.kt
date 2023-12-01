package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day01 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day01")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val digits = ('0'..'9').toList()
        return input.sumOf { s ->
            val first = s.first { it in digits }
            val second = s.last { it in digits }
            "$first$second".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.map { s ->
            val regex = "(?=(1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine))".toRegex()
            val found = regex.findAll(s).toList().map { it.groupValues }.map { it.last() }
            found.joinToString("") {
                when (it) {
                    "one" -> "1"
                    "two" -> "2"
                    "three" -> "3"
                    "four" -> "4"
                    "five" -> "5"
                    "six" -> "6"
                    "seven" -> "7"
                    "eight" -> "8"
                    "nine" -> "9"
                    else -> it
                }
            }
        }.sumOf { s ->
            val first = s.first()
            val second = s.last()
            "$first$second".toInt()
        }
    }
}
