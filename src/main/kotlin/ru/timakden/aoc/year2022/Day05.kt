package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day05 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day05")
            val initialStacks = listOf(
                ArrayDeque(listOf('J', 'H', 'P', 'M', 'S', 'F', 'N', 'V')),
                ArrayDeque(listOf('S', 'R', 'L', 'M', 'J', 'D', 'Q')),
                ArrayDeque(listOf('N', 'Q', 'D', 'H', 'C', 'S', 'W', 'B')),
                ArrayDeque(listOf('R', 'S', 'C', 'L')),
                ArrayDeque(listOf('M', 'V', 'T', 'P', 'F', 'B')),
                ArrayDeque(listOf('T', 'R', 'Q', 'N', 'C')),
                ArrayDeque(listOf('G', 'V', 'R')),
                ArrayDeque(listOf('C', 'Z', 'S', 'P', 'D', 'L', 'R')),
                ArrayDeque(listOf('D', 'S', 'J', 'V', 'G', 'P', 'B', 'F'))
            )
            println("Part One: ${part1(initialStacks, input)}")
            println("Part Two: ${part2(initialStacks, input)}")
        }
    }

    fun part1(initialStacks: List<ArrayDeque<Char>>, input: List<String>): String {
        val stacks = mutableListOf<ArrayDeque<Char>>()
        initialStacks.forEach { stacks.add(ArrayDeque(it)) }

        input.forEach { instruction ->
            val (count, source, destination) = "\\d+".toRegex().findAll(instruction).toList().map { it.value.toInt() }

            repeat(count) {
                val char = stacks[source - 1].removeLast()
                stacks[destination - 1].addLast(char)
            }
        }

        return buildString { stacks.forEach { append(it.last()) } }
    }

    fun part2(initialStacks: List<ArrayDeque<Char>>, input: List<String>): String {
        val stacks = mutableListOf<ArrayDeque<Char>>()
        initialStacks.forEach { stacks.add(ArrayDeque(it)) }

        input.forEach { instruction ->
            val (count, source, destination) = "\\d+".toRegex().findAll(instruction).toList().map { it.value.toInt() }

            val chars = mutableListOf<Char>()
            repeat(count) {
                chars.add(stacks[source - 1].removeLast())
            }
            stacks[destination - 1].addAll(chars.reversed())
        }

        return buildString { stacks.forEach { append(it.last()) } }
    }
}
