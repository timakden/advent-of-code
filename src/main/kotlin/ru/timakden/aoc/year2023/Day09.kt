package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2023.Day09.Direction.BACKWARDS
import ru.timakden.aoc.year2023.Day09.Direction.FORWARDS

object Day09 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day09")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        return input.sumOf { s ->
            val sequences = extractSequencesFromInput(s)

            for (i in sequences.lastIndex downTo 0) {
                val nextSequence = sequences.getOrNull(i + 1)
                sequences[i] = extrapolateSequence(sequences[i], nextSequence, FORWARDS)
            }

            sequences.first().last()
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { s ->
            val sequences = extractSequencesFromInput(s)

            for (i in sequences.lastIndex downTo 0) {
                val nextSequence = sequences.getOrNull(i + 1)
                sequences[i] = extrapolateSequence(sequences[i], nextSequence, BACKWARDS)
            }

            sequences.first().first()
        }
    }

    private fun extractSequencesFromInput(s: String): MutableList<List<Long>> {
        val sequences = mutableListOf<List<Long>>()
        var sequence = s.split(" ").map { it.toLong() }
        sequences += sequence

        while (sequence.any { it != 0L }) {
            sequence = generateNextSequence(sequence)
            sequences += sequence
        }

        return sequences
    }

    private fun generateNextSequence(sequence: List<Long>) = sequence.windowed(2).map { it.last() - it.first() }

    private fun extrapolateSequence(
        sequence: List<Long>,
        nextSequence: List<Long>?,
        direction: Direction
    ): List<Long> {
        return when (direction) {
            FORWARDS -> nextSequence?.let { sequence + (sequence.last() + it.last()) } ?: (sequence + 0L)
            else -> nextSequence?.let { listOf(sequence.first() - it.first()) + sequence } ?: (listOf(0L) + sequence)
        }
    }

    private enum class Direction {
        FORWARDS, BACKWARDS
    }
}
