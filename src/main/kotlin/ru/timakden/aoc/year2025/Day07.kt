package ru.timakden.aoc.year2025

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 7: Laboratories](https://adventofcode.com/2025/day/7).
 */
object Day07 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2025/Day07")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val tachyonManifold = input.map { it.toCharArray() }.toTypedArray()
        var beamColumns = setOf(tachyonManifold.first().indexOf('S'))
        var splitCount = 0
        for (i in 1..<tachyonManifold.size) {
            val nextBeamColumns = hashSetOf<Int>()
            for (j in beamColumns) {
                if (tachyonManifold[i][j] == '^') {
                    splitCount++
                    nextBeamColumns += j - 1
                    nextBeamColumns += j + 1
                } else {
                    nextBeamColumns += j
                }
            }
            beamColumns = nextBeamColumns
        }
        return splitCount
    }

    fun part2(input: List<String>): Long {
        val tachyonManifold = input.map { it.toCharArray() }.toTypedArray()
        var beamColumns = mapOf(tachyonManifold[0].indexOf('S') to 1L)
        for (i in 1..<tachyonManifold.size) {
            val nextBeamColumns = hashMapOf<Int, Long>()
            for ((j, t) in beamColumns) {
                if (tachyonManifold[i][j] == '^') {
                    nextBeamColumns[j - 1] = nextBeamColumns.getOrDefault(j - 1, 0) + t
                    nextBeamColumns[j + 1] = nextBeamColumns.getOrDefault(j + 1, 0) + t
                } else {
                    nextBeamColumns[j] = nextBeamColumns.getOrDefault(j, 0) + t
                }
            }
            beamColumns = nextBeamColumns
        }
        return beamColumns.values.sum()
    }
}
