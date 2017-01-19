package ru.timakden.adventofcode.year2015.day13

import ru.timakden.adventofcode.permutations
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): Int {
    val happiness = mutableMapOf<String, Int>()
    var optimalHappinessChange = Int.MIN_VALUE
    val names = sortedSetOf<String>()

    if (partTwo) names.add("me")

    input.forEach {
        val split = it.split("\\s".toRegex())

        val name1 = split[0]
        val name2 = split[10].substring(0, split[10].lastIndex)
        var happinessUnits = split[3].toInt()

        if (split[2] == "lose") happinessUnits = -happinessUnits

        with(names) {
            add(name1)
            add(name2)
        }

        happiness.put(name1 + name2, happinessUnits)

        if (partTwo) {
            with(happiness) {
                put(name1 + "me", 0)
                put("me" + name1, 0)
            }
        }
    }

    val seatingArrangements = names.toMutableList().permutations()

    seatingArrangements.forEach {
        var happinessChange = it.indices
                .filter { i -> i != it.lastIndex }
                .sumBy { i -> (happiness[it[i] + it[i + 1]] ?: 0) + (happiness[it[i + 1] + it[i]] ?: 0) }

        happinessChange += (happiness[it[0] + it[it.lastIndex]] ?: 0) + (happiness[it[it.lastIndex] + it[0]] ?: 0)

        if (happinessChange > optimalHappinessChange) optimalHappinessChange = happinessChange
    }

    return optimalHappinessChange
}
