package ru.timakden.adventofcode.year2015.day18

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, 100, PART_ONE)}")
        println("Part Two: ${solve(input, 100, PART_TWO)}")
    }
}

fun solve(input: List<String>, numberOfSteps: Int, part: Constants.Part): Int {
    var matrix = input.map { it.toCharArray() }.toTypedArray()

    repeat(numberOfSteps) { matrix = performStep(matrix, part) }

    return matrix.sumBy { it.count { ch -> ch == '#' } }
}

private fun performStep(matrix: Array<CharArray>, part: Constants.Part): Array<CharArray> {
    val size = matrix.size
    val newMatrix = Array(size) { CharArray(size) }

    matrix.forEachIndexed { i, chars ->
        chars.forEachIndexed { j, c ->
            val neighbours = mutableListOf<Char>()

            for (k in (i - 1)..(i + 1)) {
                for (l in (j - 1)..(j + 1)) {
                    if (k == i && l == j) continue // skip the current "cell"

                    neighbours += runCatching { matrix[k][l] }.fold({ it }, { '.' })
                }
            }

            val count = neighbours.count { it == '#' }

            newMatrix[i][j] = when (c) {
                '#' -> if (count in 2..3) '#' else '.'
                else -> if (count == 3) '#' else '.'
            }

            if (part == PART_TWO && (i == 0 || i == matrix.lastIndex) && (j == 0 || j == chars.lastIndex))
                newMatrix[i][j] = '#'
        }
    }
    return newMatrix
}
