package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 20: Grove Positioning System](https://adventofcode.com/2022/day/20).
 */
object Day20 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day20")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        return mix(input, 1, 1)
    }

    fun part2(input: List<String>): Long {
        return mix(input, 10, 811589153)
    }

    private fun mix(input: List<String>, rounds: Int, multiplier: Int): Long {
        val numbers = input
            .map { it.toLong() }
            .map { it * multiplier }
            .mapIndexed { index, value -> Number(index, value) }
            .toMutableList()
        val processOrder = numbers.toList()

        repeat(rounds) {
            for (number in processOrder) {
                val index = numbers.indexOf(number)
                numbers.remove(number)
                numbers.add(Math.floorMod(number.value + index, numbers.size), number)
            }
        }

        val indexIterator = iterator {
            val zeroIndex = numbers.indexOfFirst { it.value == 0L }
            val indexes = numbers.indices.toList()

            while (true) {
                yieldAll(indexes.subList(zeroIndex + 1, numbers.lastIndex + 1) + indexes.subList(0, zeroIndex + 1))
            }
        }

        return (1..3000).sumOf {
            val index = indexIterator.next()
            if (it % 1000 == 0) numbers[index].value else 0
        }
    }

    private data class Number(val index: Int, val value: Long)
}
