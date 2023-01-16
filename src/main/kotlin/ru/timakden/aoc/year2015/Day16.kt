package ru.timakden.aoc.year2015

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import arrow.core.tail
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2015.Day16.Aunt.Compound.*
import kotlin.time.ExperimentalTime

object Day16 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day16")

            println("Part One: ${solve(input).map { it.name }}")
            println("Part Two: ${solve(input, true).map { it.name }}")
        }
    }

    fun solve(input: List<String>, isPartTwo: Boolean = false): Option<Aunt> {
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
        val regex = "\\w+:*\\s\\d+".toRegex()

        val aunts = input.map { inputString ->
            val compounds = regex.findAll(inputString).map { it.value }
            val name = compounds.first()
            val compoundsMap = compounds.tail()
                .map { compound -> compound.split(": ").let { it.first() to it.last().toInt() } }
                .toMap()

            Aunt(name, compoundsMap)
        }

        aunts.forEach { aunt ->
            var auntFound = true

            with(aunt) {
                compounds[AKITAS]?.let { auntFound = it == checkNotNull(auntToFind.compounds[AKITAS]) }
                compounds[CARS]?.let { auntFound = auntFound && (it == checkNotNull(auntToFind.compounds[CARS])) }
                compounds[CATS]?.let {
                    val cats = checkNotNull(auntToFind.compounds[CATS])
                    val result = when {
                        isPartTwo -> it > cats
                        else -> it == cats
                    }
                    auntFound = auntFound && result
                }
                compounds[CHILDREN]?.let {
                    auntFound = auntFound && (it == checkNotNull(auntToFind.compounds[CHILDREN]))
                }
                compounds[GOLDFISH]?.let {
                    val goldfish = checkNotNull(auntToFind.compounds[GOLDFISH])
                    val result = when {
                        isPartTwo -> it < goldfish
                        else -> it == goldfish
                    }
                    auntFound = auntFound && result
                }
                compounds[PERFUMES]?.let {
                    auntFound = auntFound && (it == checkNotNull(auntToFind.compounds[PERFUMES]))
                }
                compounds[POMERANIANS]?.let {
                    val pomeranians = checkNotNull(auntToFind.compounds[POMERANIANS])
                    val result = when {
                        isPartTwo -> it < pomeranians
                        else -> it == pomeranians
                    }
                    auntFound = auntFound && result
                }
                compounds[SAMOYEDS]?.let {
                    auntFound = auntFound && (it == checkNotNull(auntToFind.compounds[SAMOYEDS]))
                }
                compounds[TREES]?.let {
                    val trees = checkNotNull(auntToFind.compounds[TREES])
                    val result = when {
                        isPartTwo -> it > trees
                        else -> it == trees
                    }
                    auntFound = auntFound && result
                }
                compounds[VIZSLAS]?.let {
                    auntFound = auntFound && (it == checkNotNull(auntToFind.compounds[VIZSLAS]))
                }
            }

            if (auntFound) return aunt.some()
        }

        return none()
    }

    class Aunt(val name: String, compounds: Map<String, Int>) {
        val compounds: MutableMap<Compound, Int> = mutableMapOf()

        init {
            compounds.forEach { (key, value) ->
                when (key) {
                    "children" -> this.compounds[CHILDREN] = value
                    "cats" -> this.compounds[CATS] = value
                    "samoyeds" -> this.compounds[SAMOYEDS] = value
                    "pomeranians" -> this.compounds[POMERANIANS] = value
                    "akitas" -> this.compounds[AKITAS] = value
                    "vizslas" -> this.compounds[VIZSLAS] = value
                    "goldfish" -> this.compounds[GOLDFISH] = value
                    "trees" -> this.compounds[TREES] = value
                    "cars" -> this.compounds[CARS] = value
                    "perfumes" -> this.compounds[PERFUMES] = value
                }
            }
        }

        enum class Compound {
            AKITAS,
            CARS,
            CATS,
            CHILDREN,
            GOLDFISH,
            PERFUMES,
            POMERANIANS,
            SAMOYEDS,
            TREES,
            VIZSLAS
        }
    }
}
