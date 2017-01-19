package ru.timakden.adventofcode.year2015.day16

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)?.name}")
        println("Part Two: ${solve(input, true)?.name}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): Aunt? {
    val auntToFind = Aunt(children = 3, cats = 7, samoyeds = 2, pomeranians = 3, akitas = 0, vizslas = 0,
            goldfish = 5, trees = 3, cars = 2, perfumes = 1)

    val aunts = mutableListOf<Aunt>()

    input.forEach {
        val split = it.split(":\\s|,\\s".toRegex())

        val aunt = Aunt(name = split[0])

        with(aunt) {
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

        aunts.add(aunt)
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
                auntFound = auntFound && (if (partTwo) it > auntToFind.cats!! else it == auntToFind.cats!!)
            }

            children?.let {
                auntFound = auntFound && (it == auntToFind.children!!)
            }

            goldfish?.let {
                auntFound = auntFound && (if (partTwo) it < auntToFind.goldfish!! else it == auntToFind.goldfish!!)
            }

            perfumes?.let {
                auntFound = auntFound && (it == auntToFind.perfumes!!)
            }

            pomeranians?.let {
                auntFound = auntFound && (if (partTwo) it < auntToFind.pomeranians!! else it == auntToFind.pomeranians!!)
            }

            samoyeds?.let {
                auntFound = auntFound && (it == auntToFind.samoyeds!!)
            }

            trees?.let {
                auntFound = auntFound and (if (partTwo) it > auntToFind.trees!! else it == auntToFind.trees!!)
            }

            vizslas?.let {
                auntFound = auntFound && (it == auntToFind.vizslas!!)
            }
        }

        if (auntFound) return aunt
    }

    return null
}

data class Aunt(var name: String? = null, var children: Int? = null, var cats: Int? = null,
                var samoyeds: Int? = null, var pomeranians: Int? = null, var akitas: Int? = null,
                var vizslas: Int? = null, var goldfish: Int? = null, var trees: Int? = null,
                var cars: Int? = null, var perfumes: Int? = null)
