package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 3: Squares With Three Sides](https://adventofcode.com/2016/day/3).
 */
object Day03 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day03")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.count { line ->
        isTrianglePossible("\\d+".toRegex().findAll(line).map { it.value }.toList())
    }

    fun part2(input: List<String>): Int {
        var triangles = 0
        (0..input.lastIndex step 3).forEach { i ->
            val list = mutableListOf<List<String>>()

            (0..2).forEach { j -> list += "\\d+".toRegex().findAll(input[i + j]).map { it.value }.toList() }

            repeat((0..2).filter { isTrianglePossible(listOf(list[0][it], list[1][it], list[2][it])) }.size) {
                triangles++
            }
        }
        return triangles
    }

    private fun isTrianglePossible(sides: List<String>): Boolean {
        val (side1, side2, side3) = sides.map { it.toInt() }

        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1
    }
}
