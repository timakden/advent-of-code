package ru.timakden.aoc.year2023

import arrow.core.fold
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

/**
 * [Day 19: Aplenty](https://adventofcode.com/2023/day/19).
 */
object Day19 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day19")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val emptyLineIndex = input.indexOf("")
        val workflows = input.subList(0, emptyLineIndex).map { Workflow.fromString(it) }
        val parts = input.subList(emptyLineIndex + 1, input.size).map { Part.fromString(it) }
        val accepted = mutableListOf<Part>()

        parts.forEach { part ->
            var workflow = workflows.find { it.name == "in" }
            while (workflow != null) {
                val rules = workflow.rules
                for (rule in rules) {
                    if (rule.contains('<') || rule.contains('>')) {
                        val condition = rule[1]
                        val value = rule.substringAfter(condition).substringBefore(':').toInt()
                        val destination = rule.substringAfter(':')
                        val partRating = part.ratingByCategory[rule.first()] ?: error("Invalid category name")
                        if (condition == '<' && partRating < value || condition == '>' && partRating > value) {
                            if (destination == "A") {
                                accepted += part
                                workflow = null
                                break
                            } else if (destination == "R") {
                                workflow = null
                                break
                            } else {
                                workflow = workflows.find { it.name == destination }
                                break
                            }
                        }
                    } else if (rule == "A") {
                        accepted += part
                        workflow = null
                    } else if (rule == "R") {
                        workflow = null
                    } else {
                        workflow = workflows.find { it.name == rule }
                    }
                }
            }
        }

        return accepted.fold(0) { acc, part -> acc + part.totalRating }
    }

    fun part2(input: List<String>): Long {
        val emptyLineIndex = input.indexOf("")
        val workflows = input.subList(0, emptyLineIndex).map { Workflow.fromString(it) }
        val accepted = mutableListOf<PartRange>()

        fun processWorkflow(workflow: Workflow, partRange: PartRange) {
            val rules = workflow.rules
            var currentPart = partRange
            for (rule in rules) {
                if (rule.contains('<') || rule.contains('>')) {
                    val categoryName = rule.first()
                    val currentRange = currentPart.ratingByCategory[categoryName] ?: error("Invalid category name")
                    val condition = rule[1]
                    val value = rule.substringAfter(condition).substringBefore(':').toInt()
                    val destination = rule.substringAfter(':')
                    var truePart: PartRange by Delegates.notNull()
                    var falsePart: PartRange by Delegates.notNull()

                    when (condition) {
                        '<' -> {
                            val trueRatings = mutableMapOf<Char, LongRange>().apply {
                                putAll(currentPart.ratingByCategory)
                                put(categoryName, currentRange.first..min((value - 1).toLong(), currentRange.last))
                            }

                            val falseRatings = mutableMapOf<Char, LongRange>().apply {
                                putAll(currentPart.ratingByCategory)
                                put(categoryName, max(value.toLong(), currentRange.first)..currentRange.last)
                            }
                            truePart = PartRange(trueRatings)
                            falsePart = PartRange(falseRatings)
                        }

                        '>' -> {
                            val trueRatings = mutableMapOf<Char, LongRange>().apply {
                                putAll(currentPart.ratingByCategory)
                                put(categoryName, max((value + 1).toLong(), currentRange.first)..currentRange.last)
                            }

                            val falseRatings = mutableMapOf<Char, LongRange>().apply {
                                putAll(currentPart.ratingByCategory)
                                put(categoryName, currentRange.first..min(value.toLong(), currentRange.last))
                            }
                            truePart = PartRange(trueRatings)
                            falsePart = PartRange(falseRatings)
                        }

                        else -> error("Unknown condition $condition")
                    }

                    if (truePart.ratingByCategory[categoryName]?.isEmpty() == false) {
                        when (destination) {
                            "A" -> accepted += truePart
                            "R" -> {}
                            else -> {
                                val nextWorkflow = checkNotNull(workflows.find { it.name == destination })
                                processWorkflow(nextWorkflow, truePart)
                            }
                        }
                    }

                    if (falsePart.ratingByCategory[categoryName]?.isEmpty() != false) break

                    currentPart = falsePart

                } else if (rule == "A") {
                    accepted += currentPart
                } else if (rule == "R") {
                    // Nothing to do
                } else {
                    val nextWorkflow = checkNotNull(workflows.find { it.name == rule })
                    processWorkflow(nextWorkflow, currentPart)
                }
            }
        }

        val workflow = checkNotNull(workflows.find { it.name == "in" })
        val partRange = PartRange()

        processWorkflow(workflow, partRange)

        return accepted.sumOf { it.totalRating }
    }

    private data class Workflow(val name: String, val rules: List<String>) {
        companion object {
            fun fromString(input: String): Workflow {
                val name = input.substringBefore("{")
                val rules = input.substringAfter("{").substringBefore("}").split(",")
                return Workflow(name, rules)
            }
        }
    }

    private data class Part(val ratingByCategory: Map<Char, Int>) {
        val totalRating: Int by lazy { ratingByCategory.fold(0) { acc, entry -> acc + entry.value } }

        companion object {
            fun fromString(input: String): Part {
                val regex = Regex("""\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)}""")
                val matchResult = regex.find(input)
                val (x, m, a, s) = matchResult!!.destructured
                val ratings = mapOf(
                    'x' to x.toInt(),
                    'm' to m.toInt(),
                    'a' to a.toInt(),
                    's' to s.toInt()
                )
                return Part(ratings)
            }
        }
    }

    private data class PartRange(
        val ratingByCategory: Map<Char, LongRange> = mapOf(
            'x' to 1L..4000L,
            'm' to 1L..4000L,
            'a' to 1L..4000L,
            's' to 1L..4000L
        )
    ) {
        val totalRating: Long by lazy {
            ratingByCategory.fold(1) { acc, entry ->
                val range = entry.value
                acc * (range.last - range.first + 1)
            }
        }
    }
}
