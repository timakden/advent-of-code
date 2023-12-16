package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2023.Day14.Direction.*

/**
 * [Day 14: Parabolic Reflector Dish](https://adventofcode.com/2023/day/14).
 */
object Day14 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day14")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val rows = input.size
        val columns: Int = input.first().length
        val platform = input.map { it.toCharArray() }.toTypedArray()
        tiltPlatform(platform, NORTH, columns, rows)
        return calculateTotalLoad(platform)
    }

    fun part2(input: List<String>): Int {
        val rows = input.size
        val columns: Int = input.first().length
        val platform = input.map { it.toCharArray() }.toTypedArray()
        val wrapperToIndex = HashMap<ArrayWrapper, Int>()
        val indexToWrapper = HashMap<Int, ArrayWrapper>()
        var counter = 0
        val target = 1000000000

        while (true) {
            val wrapper = ArrayWrapper(Array(rows) { i -> platform[i].copyOf() })
            val start = wrapperToIndex.put(wrapper, counter)
            if (start != null) {
                val counterDifference = counter - start
                val previousWrapper = indexToWrapper[start + (target - start) % counterDifference]!!
                for (i in 0..<rows)
                    for (j in 0..<columns)
                        platform[i][j] = previousWrapper.array[i][j]
                break
            }
            indexToWrapper[counter] = wrapper
            counter++

            tiltPlatform(platform, NORTH, columns, rows)
            tiltPlatform(platform, WEST, columns, rows)
            tiltPlatform(platform, SOUTH, columns, rows)
            tiltPlatform(platform, EAST, columns, rows)
        }
        return calculateTotalLoad(platform)
    }

    private class ArrayWrapper(val array: Array<CharArray>) {
        override fun equals(other: Any?): Boolean = other is ArrayWrapper && array.contentDeepEquals(other.array)
        override fun hashCode(): Int = array.contentDeepHashCode()
    }

    private fun tiltPlatform(
        platform: Array<CharArray>,
        direction: Direction,
        columns: Int,
        rows: Int
    ) {
        if (direction == NORTH || direction == SOUTH) {
            repeat(columns) { columnIndex ->
                val column = platform.map { it[columnIndex] }.toCharArray()
                tilt(column, direction)
                for (rowIndex in 0..platform.lastIndex) {
                    platform[rowIndex][columnIndex] = column[rowIndex]
                }
            }
        } else {
            repeat(rows) { rowIndex ->
                val row = platform[rowIndex]
                tilt(row, direction)
            }
        }
    }

    private fun tilt(
        chars: CharArray,
        direction: Direction
    ) {
        for (i in 0..<chars.lastIndex) {
            for (j in 0..<(chars.lastIndex - i)) {
                val canSwap = if (direction == NORTH || direction == WEST) {
                    chars[j] == '.' && chars[j + 1] == 'O'
                } else {
                    chars[j] == 'O' && chars[j + 1] == '.'
                }

                if (canSwap) {
                    val temp = chars[j]
                    chars[j] = chars[j + 1]
                    chars[j + 1] = temp
                }
            }
        }
    }

    private fun calculateTotalLoad(platform: Array<CharArray>): Int {
        var sum = 0
        for (i in platform.indices) {
            sum += platform[i].count { it == 'O' } * (platform.size - i)
        }
        return sum
    }

    private enum class Direction {
        EAST, NORTH, SOUTH, WEST
    }
}
