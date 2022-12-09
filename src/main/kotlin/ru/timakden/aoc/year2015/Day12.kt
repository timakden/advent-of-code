package ru.timakden.aoc.year2015

import kotlinx.serialization.json.*
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day12 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day12").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String) = sumNumbers(Json.parseToJsonElement(input))
    fun part2(input: String) = sumNumbers(Json.parseToJsonElement(input), true)

    private fun sumNumbers(json: JsonElement, isPartTwo: Boolean = false): Int {
        var sum = 0
        when (json) {
            is JsonObject -> {
                json.values.forEach { value ->
                    if (isPartTwo && (value as? JsonPrimitive)?.content == "red") return 0
                    value.let { sum += sumNumbers(it, isPartTwo) }
                }
                return sum
            }

            is JsonArray -> {
                json.filterNotNull().forEach { sum += sumNumbers(it, isPartTwo) }
                return sum
            }

            is JsonPrimitive -> return json.intOrNull ?: 0

            else -> return 0
        }
    }
}