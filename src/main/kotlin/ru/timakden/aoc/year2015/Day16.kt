package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day16 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day16")

            println("Part One: ${solve(input)?.name}")
            println("Part Two: ${solve(input, true)?.name}")
        }
    }

    fun solve(input: List<String>, isPartTwo: Boolean = false): Aunt? {
        val auntToFind = Aunt(
            "Sue", mapOf(
                "children" to 3,
                "cats" to 7,
                "samoyeds" to 2,
                "pomeranians" to 3,
                "akitas" to 0,
                "vizslas" to 0,
                "goldfish" to 5,
                "trees" to 3,
                "cars" to 2,
                "perfumes" to 1
            )
        )

        val aunts = input.map { inputString ->
            val compounds = "\\w+:*\\s\\d+".toRegex().findAll(inputString).map { it.value }
            val name = compounds.first()
            val compoundsMap = compounds.drop(1)
                .map { compound -> compound.split(": ").let { it.first() to it.last().toInt() } }
                .toMap()

            Aunt(name, compoundsMap)
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
                    auntFound =
                        auntFound && (if (isPartTwo) it > auntToFind.cats!! else it == auntToFind.cats!!)
                }

                children?.let {
                    auntFound = auntFound && (it == auntToFind.children!!)
                }

                goldfish?.let {
                    auntFound =
                        auntFound && (if (isPartTwo) it < auntToFind.goldfish!! else it == auntToFind.goldfish!!)
                }

                perfumes?.let {
                    auntFound = auntFound && (it == auntToFind.perfumes!!)
                }

                pomeranians?.let {
                    auntFound = auntFound &&
                            (if (isPartTwo) it < auntToFind.pomeranians!! else it == auntToFind.pomeranians!!)
                }

                samoyeds?.let {
                    auntFound = auntFound && (it == auntToFind.samoyeds!!)
                }

                trees?.let {
                    auntFound =
                        auntFound and (if (isPartTwo) it > auntToFind.trees!! else it == auntToFind.trees!!)
                }

                vizslas?.let {
                    auntFound = auntFound && (it == auntToFind.vizslas!!)
                }
            }

            if (auntFound) return aunt
        }

        return null
    }

    data class Aunt(val name: String) {
        var children: Int? = null
        var cats: Int? = null
        var samoyeds: Int? = null
        var pomeranians: Int? = null
        var akitas: Int? = null
        var vizslas: Int? = null
        var goldfish: Int? = null
        var trees: Int? = null
        var cars: Int? = null
        var perfumes: Int? = null

        constructor(name: String, compounds: Map<String, Int>) : this(name) {
            compounds.forEach { (key, value) ->
                when (key) {
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
}
