package ru.timakden.adventofcode.year2016.day06

import ru.timakden.adventofcode.Constants.Part
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Part): String {
    var errorCorrectedMessage = ""

    (0..input[0].lastIndex).forEach { i ->
        val map = mutableMapOf<Char, Int>()
        input.forEach {
            var count = map[it[i]] ?: 0
            map[it[i]] = ++count
        }

        val entry = if (part == PART_TWO) map.minByOrNull { it.value } else map.maxByOrNull { it.value }
        entry?.let {
            errorCorrectedMessage += it.key.toString()
        }
    }

    return errorCorrectedMessage
}
