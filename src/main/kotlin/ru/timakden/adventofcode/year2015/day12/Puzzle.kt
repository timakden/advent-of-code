package ru.timakden.adventofcode.year2015.day12

import com.google.gson.Gson
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
}

// TODO: Remove Gson dependency
fun solve(input: String, partTwo: Boolean) = sumNumbers(Gson().fromJson(input, Any::class.java), partTwo)

private fun sumNumbers(any: Any, partTwo: Boolean): Int {
    var sum = 0

    when (any) {
        is Map<*, *> -> {
            any.values.forEach { value ->
                if (partTwo && value == "red") return 0

                value?.let { sum += sumNumbers(it, partTwo) }
            }
            return sum
        }
        is List<*> -> {
            any.filterNotNull().forEach { sum += sumNumbers(it, partTwo) }
            return sum
        }
        is Number -> return any.toInt()
        else -> return 0
    }
}
