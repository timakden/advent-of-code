package ru.timakden.aoc.year2015.day15

import ru.timakden.aoc.util.Constants
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Constants.Part): Int {
    var highest = Int.MIN_VALUE

    val ingredients = input.map {
        val regex = "\\w+:|-?\\d".toRegex()
        val split = regex.findAll(it).map { matchResult -> matchResult.value }.toList()
        Ingredient(split)
    }

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

                        when (part) {
                            PART_TWO -> if (total > highest && calories == 500) highest = total
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
