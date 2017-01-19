package ru.timakden.adventofcode.year2015.day12

import com.google.gson.Gson
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: String, partTwo: Boolean) = sumNumbers(Gson().fromJson(input, Any::class.java), partTwo)

private fun sumNumbers(any: Any, partTwo: Boolean): Int {
    var sum = 0

    if (any is Map<*, *>) {
        any.forEach {
            if (partTwo && it.value == "red") return 0

            it.value?.let { sum += sumNumbers(it, partTwo) }
        }
        return sum
    } else if (any is List<*>) {
        any.filterNotNull().forEach { sum += sumNumbers(it, partTwo) }
        return sum
    } else if (any is Number) return any.toInt()

    return 0
}
