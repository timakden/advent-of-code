package ru.timakden.adventofcode.year2015.day15

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): Int {
    val ingredients = mutableListOf<Ingredient>()
    var highest = Int.MIN_VALUE

    input.forEach {
        val split = mutableListOf<String>()
        val regex = "\\w+:|-?\\d".toRegex()

        regex.findAll(it).forEach { split.add(it.value) }

        ingredients.add(
            Ingredient(
                split[0].removeSuffix(":"), split[1].toInt(), split[2].toInt(), split[3].toInt(),
                split[4].toInt(), split[5].toInt()
            )
        )
    }

    var capacity: Int
    var durability: Int
    var flavor: Int
    var texture: Int
    var calories: Int
    var total: Int

    (1..100).forEach { i ->
        (1..100 - i).forEach { j ->
            (1..100 - i - j).forEach { k ->
                for (l in 1..100 - i - j - k) {
                    if (i + j + k + l == 100) {
                        capacity = ingredients[0].capacity * i + ingredients[1].capacity * j +
                                ingredients[2].capacity * k + ingredients[3].capacity * l

                        durability = ingredients[0].durability * i + ingredients[1].durability * j +
                                ingredients[2].durability * k + ingredients[3].durability * l

                        flavor = ingredients[0].flavor * i + ingredients[1].flavor * j +
                                ingredients[2].flavor * k + ingredients[3].flavor * l

                        texture = ingredients[0].texture * i + ingredients[1].texture * j +
                                ingredients[2].texture * k + ingredients[3].texture * l

                        calories = ingredients[0].calories * i + ingredients[1].calories * j +
                                ingredients[2].calories * k + ingredients[3].calories * l

                        if (capacity > 0 && durability > 0 && flavor > 0 && texture > 0) {
                            total = capacity * durability * flavor * texture
                        } else total = 0

                        when {
                            partTwo -> if (total > highest && calories == 500) highest = total
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
    var name: String, var capacity: Int, var durability: Int, var flavor: Int,
    var texture: Int, var calories: Int
)
