package ru.timakden.adventofcode.year2015.day19

import ru.timakden.adventofcode.measure
import java.util.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(replacements, molecule)}")
        println("Part Two: ${solvePartTwo(replacements, molecule)}")
    }
}

fun solvePartOne(replacements: List<String>, molecule: String): Int {
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

fun solvePartTwo(replacements: List<String>, molecule: String): Int {
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
