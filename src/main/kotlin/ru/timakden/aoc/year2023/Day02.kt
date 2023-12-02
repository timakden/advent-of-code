package ru.timakden.aoc.year2023

import arrow.core.fold
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day02 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day02")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val limits = mapOf("blue" to 14, "green" to 13, "red" to 12)
        return input.filter { s ->
            val gameId = s.substringAfter("Game ").substringBefore(":").toInt()
            s.substringAfter(": ").split("; ").forEach { result ->
                result.split(", ").forEach { cube ->
                    val color = cube.substringAfter(" ")
                    val count = cube.substringBefore(" ").toInt()
                    if (checkNotNull(limits[color]) < count) return@filter false
                }
            }
            true
        }.sumOf { it.substringAfter("Game ").substringBefore(":").toInt() }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { s ->
            val minimumRequiredCubes = mutableMapOf("blue" to 0, "green" to 0, "red" to 0)
            s.substringAfter(": ").split("; ").forEach { result ->
                result.split(", ").forEach { cube ->
                    val color = cube.substringAfter(" ")
                    val count = cube.substringBefore(" ").toInt()
                    if (checkNotNull(minimumRequiredCubes[color]) < count) minimumRequiredCubes[color] = count
                }
            }
            minimumRequiredCubes.fold(1L) { acc, entry -> entry.value * acc }
        }
    }
}
