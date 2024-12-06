package ru.timakden.aoc.year2024

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.util.*

/**
 * [Day 5: Print Queue](https://adventofcode.com/2024/day/5).
 */
object Day05 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day05")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val rules = input.getRules()
        val updates = input.getUpdates()

        val result = updates.filter { update ->
            update.all { pageNumber ->
                val firstIndex = update.indexOf(pageNumber)
                val filteredRules = rules.filter { it.first == pageNumber }
                filteredRules.all { rule ->
                    val secondIndex = update.indexOf(rule.second)
                    if (secondIndex == -1) true
                    else firstIndex < secondIndex
                }
            }
        }

        return result.sumOf { it[it.size / 2] }
    }

    fun part2(input: List<String>): Int {
        val rules = input.getRules()
        val updates = input.getUpdates()

        val invalidUpdates = updates.filter { update ->
            update.any { pageNumber ->
                val firstIndex = update.indexOf(pageNumber)
                val filteredRules = rules.filter { it.first == pageNumber }
                !filteredRules.all { rule ->
                    val secondIndex = update.indexOf(rule.second)
                    if (secondIndex == -1) true
                    else firstIndex < secondIndex
                }
            }
        }.map { it.toMutableList() }

        invalidUpdates.forEach { update ->
            while (true) {
                val valid = update.all { pageNumber ->
                    val firstIndex = update.indexOf(pageNumber)
                    val filteredRules = rules.filter { it.first == pageNumber }
                    filteredRules.all { rule ->
                        val secondIndex = update.indexOf(rule.second)
                        if (secondIndex == -1) true
                        else if (firstIndex > secondIndex) {
                            Collections.swap(update, firstIndex, secondIndex)
                            false
                        } else firstIndex < secondIndex
                    }
                }

                if (valid) break
            }
        }

        return invalidUpdates.sumOf { it[it.size / 2] }
    }

    private fun List<String>.getRules() = this.subList(0, this.indexOf("")).map { rule ->
        val (first, second) = rule.split("|").map { it.toInt() }
        first to second
    }

    private fun List<String>.getUpdates() = this.subList(this.indexOf("") + 1, this.size).map { update ->
        update.split(",").map { it.toInt() }
    }
}
