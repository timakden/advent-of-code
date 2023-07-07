package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.Permutations
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day13 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day13")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val happiness = mutableMapOf<String, Int>()
        var optimalHappinessChange = Int.MIN_VALUE
        val names = sortedSetOf<String>()

        input.forEach {
            val split = it.split(' ')

            val name1 = split[0]
            val name2 = split[10].substring(0, split[10].lastIndex)
            var happinessUnits = split[3].toInt()

            if (split[2] == "lose") happinessUnits = -happinessUnits

            names += name1
            names += name2

            happiness[name1 + name2] = happinessUnits
        }

        val seatingArrangements = Permutations.of(names.toList())

        seatingArrangements.forEach {
            val list = it.toList()
            var happinessChange = list.indices
                .filter { i -> i != list.lastIndex }
                .sumOf { i -> (happiness[list[i] + list[i + 1]] ?: 0) + (happiness[list[i + 1] + list[i]] ?: 0) }

            happinessChange += (happiness[list[0] + list[list.lastIndex]] ?: 0) +
                    (happiness[list[list.lastIndex] + list[0]] ?: 0)

            if (happinessChange > optimalHappinessChange) optimalHappinessChange = happinessChange
        }

        return optimalHappinessChange
    }

    fun part2(input: List<String>): Int {
        val happiness = mutableMapOf<String, Int>()
        var optimalHappinessChange = Int.MIN_VALUE
        val names = sortedSetOf<String>().apply { add("me") }

        input.forEach {
            val split = it.split(' ')

            val name1 = split[0]
            val name2 = split[10].substring(0, split[10].lastIndex)
            var happinessUnits = split[3].toInt()

            if (split[2] == "lose") happinessUnits = -happinessUnits

            names += name1
            names += name2

            happiness[name1 + name2] = happinessUnits
            happiness["${name1}me"] = 0
            happiness["me$name1"] = 0
        }

        val seatingArrangements = Permutations.of(names.toList())

        seatingArrangements.forEach {
            val list = it.toList()
            var happinessChange = list.indices
                .filter { i -> i != list.lastIndex }
                .sumOf { i -> (happiness[list[i] + list[i + 1]] ?: 0) + (happiness[list[i + 1] + list[i]] ?: 0) }

            happinessChange += (happiness[list[0] + list[list.lastIndex]] ?: 0) +
                    (happiness[list[list.lastIndex] + list[0]] ?: 0)

            if (happinessChange > optimalHappinessChange) optimalHappinessChange = happinessChange
        }

        return optimalHappinessChange
    }
}
