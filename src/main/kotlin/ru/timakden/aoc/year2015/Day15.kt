package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day15 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day15")
            println("Part One: ${solve(input)}")
            println("Part Two: ${solve(input, true)}")
        }
    }

    fun solve(input: List<String>, isPartTwo: Boolean = false): Int {
        var highest = Int.MIN_VALUE

        val regex = "\\w+:|-?\\d".toRegex()
        val ingredients = input.map { Ingredient(regex.findAll(it).map { matchResult -> matchResult.value }.toList()) }

        var capacity: Int
        var durability: Int
        var flavor: Int
        var texture: Int
        var calories: Int
        var total: Int

        // TODO: Переделать
        (1..100).forEach { i ->
            (1..100 - i).forEach { j ->
                (1..100 - i - j).forEach { k ->
                    for (l in 1..100 - i - j - k) {
                        if (i + j + k + l == 100) {
                            capacity = ingredients.map { it.capacity }
                                .zip(listOf(i, j, k, l)).sumOf { it.first * it.second }

                            durability = ingredients.map { it.durability }
                                .zip(listOf(i, j, k, l)).sumOf { it.first * it.second }

                            flavor = ingredients.map { it.flavor }
                                .zip(listOf(i, j, k, l)).sumOf { it.first * it.second }

                            texture = ingredients.map { it.texture }
                                .zip(listOf(i, j, k, l)).sumOf { it.first * it.second }

                            calories = ingredients.map { it.calories }
                                .zip(listOf(i, j, k, l)).sumOf { it.first * it.second }


                            total = if (listOf(capacity, durability, flavor, texture).all { it > 0 })
                                listOf(capacity, durability, flavor, texture).fold(1) { acc, i -> acc * i }
                            else 0

                            when (isPartTwo) {
                                true -> if (total > highest && calories == 500) highest = total
                                else -> if (total > highest) highest = total
                            }
                        } else if (i + j + k + l > 100) break
                    }
                }
            }
        }

        return highest
    }

    data class Ingredient(
        var name: String,
        var capacity: Int,
        var durability: Int,
        var flavor: Int,
        var texture: Int,
        var calories: Int
    ) {
        constructor(split: List<String>) : this(
            split[0].removeSuffix(":"),
            split[1].toInt(),
            split[2].toInt(),
            split[3].toInt(),
            split[4].toInt(),
            split[5].toInt()
        )
    }
}
