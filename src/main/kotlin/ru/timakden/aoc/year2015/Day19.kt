package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.util.*
import kotlin.time.ExperimentalTime

object Day19 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day19")
            val replacements = input.dropLast(2)
            val molecule = input.last()

            println("Part One: ${part1(replacements, molecule)}")
            println("Part Two: ${part2(replacements, molecule)}")
        }
    }

    fun part1(replacements: List<String>, molecule: String): Int {
        val regex = "\\s=>\\s".toRegex()
        val distinctMolecules = mutableSetOf<String>()
        val reversedReplacements = replacements.associateBy({ it.split(regex)[1] }, { it.split(regex)[0] })

        reversedReplacements.forEach { (key, value) ->
            var index = -1

            do {
                index = molecule.indexOf(value, index + 1)
                if (index != -1) {
                    distinctMolecules.add(molecule.replaceRange(index, index + value.length, key))
                }
            } while (index != -1)
        }

        return distinctMolecules.size
    }

    fun part2(replacements: List<String>, molecule: String): Int {
        var count: Int
        val regex = "\\s=>\\s".toRegex()
        val reversedReplacements = replacements.associateBy({ it.split(regex)[1] }, { it.split(regex)[0] })

        do {
            count = findMolecule(0, molecule, reversedReplacements)
        } while (count == -1)

        return count
    }

    private tailrec fun findMolecule(depth: Int, molecule: String, reversedReplacements: Map<String, String>): Int {
        if (molecule == "e") return depth
        else {
            var originalMolecule = molecule
            val keys = reversedReplacements.keys.toMutableList()
            var replaced = false
            while (!replaced) {
                val toReplace = keys.removeAt(Random().nextInt(keys.size))
                if (molecule.contains(toReplace)) {
                    val before = originalMolecule.substringBefore(toReplace)
                    val after = originalMolecule.substringAfter(toReplace)
                    originalMolecule = before + reversedReplacements[toReplace] + after
                    replaced = true
                }
                if (keys.isEmpty()) return -1
            }
            return findMolecule(depth + 1, originalMolecule, reversedReplacements)
        }
    }
}
