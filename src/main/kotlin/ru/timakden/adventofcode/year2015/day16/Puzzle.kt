package ru.timakden.adventofcode.year2015.day16

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)?.name}")
        println("Part Two: ${solve(input, PART_TWO)?.name}")
    }
}

fun solve(input: List<String>, part: Constants.Part): Aunt? {
    val auntToFind = Aunt("Sue", 3, 7, 2, 3, 0, 0, 5, 3, 2, 1)

    val aunts = input.map { inputString ->
        val split = inputString.split(":\\s|,\\s".toRegex())

        Aunt(name = split[0]).apply {
            (1..5 step 2).forEach {
                val value = split[it + 1].toInt()
                when (split[it]) {
                    "children" -> children = value
                    "cats" -> cats = value
                    "samoyeds" -> samoyeds = value
                    "pomeranians" -> pomeranians = value
                    "akitas" -> akitas = value
                    "vizslas" -> vizslas = value
                    "goldfish" -> goldfish = value
                    "trees" -> trees = value
                    "cars" -> cars = value
                    "perfumes" -> perfumes = value
                }
            }
        }
    }

    aunts.forEach { aunt ->
        var auntFound = true

        with(aunt) {
            akitas?.let {
                auntFound = it == auntToFind.akitas!!
            }

            cars?.let {
                auntFound = auntFound && (it == auntToFind.cars!!)
            }

            cats?.let {
                auntFound = auntFound && (if (part == PART_TWO) it > auntToFind.cats!! else it == auntToFind.cats!!)
            }

            children?.let {
                auntFound = auntFound && (it == auntToFind.children!!)
            }

            goldfish?.let {
                auntFound = auntFound && (if (part == PART_TWO) it < auntToFind.goldfish!! else it == auntToFind.goldfish!!)
            }

            perfumes?.let {
                auntFound = auntFound && (it == auntToFind.perfumes!!)
            }

            pomeranians?.let {
                auntFound = auntFound &&
                        (if (part == PART_TWO) it < auntToFind.pomeranians!! else it == auntToFind.pomeranians!!)
            }

            samoyeds?.let {
                auntFound = auntFound && (it == auntToFind.samoyeds!!)
            }

            trees?.let {
                auntFound = auntFound and (if (part == PART_TWO) it > auntToFind.trees!! else it == auntToFind.trees!!)
            }

            vizslas?.let {
                auntFound = auntFound && (it == auntToFind.vizslas!!)
            }
        }

        if (auntFound) return aunt
    }

    return null
}

data class Aunt(
    var name: String? = null, var children: Int? = null, var cats: Int? = null,
    var samoyeds: Int? = null, var pomeranians: Int? = null, var akitas: Int? = null,
    var vizslas: Int? = null, var goldfish: Int? = null, var trees: Int? = null,
    var cars: Int? = null, var perfumes: Int? = null
)
