package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day06 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day06")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val regex = "\\s(?!on|off)".toRegex()
        val lightsGrid = List(1000) { MutableList(1000) { false } }

        input.forEach {
            val list = it.split(regex)
            val action = list[0]

            val coordinates1 = list[1].split(',')
            val coordinates2 = list[3].split(',')

            val pair1 = coordinates1[0].toInt() to coordinates1[1].toInt()
            val pair2 = coordinates2[0].toInt() to coordinates2[1].toInt()

            var i = pair1.first

            while (i <= pair2.first) {
                var j = pair1.second

                while (j <= pair2.second) {
                    when (action) {
                        "toggle" -> lightsGrid[i][j] = !lightsGrid[i][j]
                        "turn on" -> lightsGrid[i][j] = true
                        "turn off" -> lightsGrid[i][j] = false
                    }
                    j++
                }
                i++
            }
        }

        return lightsGrid.sumOf { booleans -> booleans.count { it } }
    }

    fun part2(input: List<String>): Int {
        val regex = "\\s(?!on|off)".toRegex()
        val brightnessGrid = List(1000) { MutableList(1000) { 0 } }

        input.forEach {
            val list = it.split(regex)
            val action = list[0]

            val coordinates1 = list[1].split(',')
            val coordinates2 = list[3].split(',')

            val pair1 = coordinates1[0].toInt() to coordinates1[1].toInt()
            val pair2 = coordinates2[0].toInt() to coordinates2[1].toInt()

            var i = pair1.first

            while (i <= pair2.first) {
                var j = pair1.second

                while (j <= pair2.second) {
                    when (action) {
                        "toggle" -> brightnessGrid[i][j] += 2
                        "turn on" -> brightnessGrid[i][j]++
                        "turn off" -> if (brightnessGrid[i][j] > 0) brightnessGrid[i][j]--
                    }
                    j++
                }
                i++
            }
        }

        return brightnessGrid.sumOf { ints -> ints.sum() }
    }
}
