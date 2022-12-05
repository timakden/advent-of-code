package ru.timakden.aoc.year2022.day05

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(initialStacks, input)}")
        println("Part Two: ${solvePartTwo(initialStacks, input)}")
    }
}

fun solvePartOne(initialStacks: List<ArrayDeque<Char>>, input: List<String>): String {
    val stacks = mutableListOf<ArrayDeque<Char>>()
    initialStacks.forEach { stacks.add(ArrayDeque(it)) }

    input.forEach { instruction ->
        val count = instruction.substringAfter("move ").substringBefore(" from").toInt()
        val source = instruction.substringAfter("from ").substringBefore(" to").toInt()
        val destination = instruction.substringAfter("to ").toInt()

        repeat(count) {
            val char = stacks[source - 1].removeLast()
            stacks[destination - 1].addLast(char)
        }
    }

    return buildString { stacks.forEach { append(it.last()) } }
}

fun solvePartTwo(initialStacks: List<ArrayDeque<Char>>, input: List<String>): String {
    val stacks = mutableListOf<ArrayDeque<Char>>()
    initialStacks.forEach { stacks.add(ArrayDeque(it)) }

    input.forEach { instruction ->
        val count = instruction.substringAfter("move ").substringBefore(" from").toInt()
        val source = instruction.substringAfter("from ").substringBefore(" to").toInt()
        val destination = instruction.substringAfter("to ").toInt()

        val chars = mutableListOf<Char>()
        repeat(count) {
            chars.add(stacks[source - 1].removeLast())
        }
        stacks[destination - 1].addAll(chars.reversed())
    }

    return buildString { stacks.forEach { append(it.last()) } }
}
