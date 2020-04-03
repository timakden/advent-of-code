package ru.timakden.adventofcode.year2015.day12

import com.google.gson.Gson
import ru.timakden.adventofcode.Constants
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

// TODO: Remove Gson dependency
fun solve(input: String, part: Constants.Part) = sumNumbers(Gson().fromJson(input, Any::class.java), part)

private fun sumNumbers(any: Any, part: Constants.Part): Int {
    var sum = 0

    when (any) {
        is Map<*, *> -> {
            any.values.forEach { value ->
                if (part == PART_TWO && value == "red") return 0

                value?.let { sum += sumNumbers(it, part) }
            }
            return sum
        }
        is List<*> -> {
            any.filterNotNull().forEach { sum += sumNumbers(it, part) }
            return sum
        }
        is Number -> return any.toInt()
        else -> return 0
    }
}
