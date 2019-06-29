package ru.timakden.adventofcode.year2015.day13

import ru.timakden.adventofcode.Permutations
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
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

        happiness[name1 + name2] = happinessUnits

        if (partTwo) {
            with(happiness) {
                put(name1 + "me", 0)
                put("me$name1", 0)
            }
        }
    }

    val seatingArrangements = Permutations.of(names.toList())

    seatingArrangements.forEach {
        val list = it.toList()
        var happinessChange = list.indices
            .filter { i -> i != list.lastIndex }
            .sumBy { i -> (happiness[list[i] + list[i + 1]] ?: 0) + (happiness[list[i + 1] + list[i]] ?: 0) }

        happinessChange += (happiness[list[0] + list[list.lastIndex]] ?: 0) + (happiness[list[list.lastIndex] + list[0]]
            ?: 0)

        if (happinessChange > optimalHappinessChange) {
            optimalHappinessChange = happinessChange
        }
    }

    return optimalHappinessChange
}
