package ru.timakden.adventofcode.year2015.day18

import arrow.core.Failure
import arrow.core.Success
import arrow.core.Try
import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solve(input, 100, false)}")
        println("Part Two: ${solve(input, 100, true)}")
    }
}

fun solve(input: List<String>, numberOfSteps: Int, partTwo: Boolean): Int {
    var matrix = input.map { it.toCharArray() }.toTypedArray()

    repeat(numberOfSteps) { matrix = performStep(matrix, partTwo) }

    return matrix.sumBy { it.count { ch -> ch == '#' } }
}

private fun performStep(matrix: Array<CharArray>, partTwo: Boolean): Array<CharArray> {
    val size = matrix.size
    val newMatrix = Array(size) { CharArray(size) }

    matrix.forEachIndexed { i, chars ->
        chars.forEachIndexed { j, c ->
            val neighbours = mutableListOf<Char>()

            for (k in (i - 1)..(i + 1)) {
                for (l in (j - 1)..(j + 1)) {
                    if (k == i && l == j) continue // skip the current "cell"

                    neighbours += when (val t = Try { matrix[k][l] }) {
                        is Success -> t.value
                        is Failure -> '.'
                    }
                }
            }

            val count = neighbours.count { it == '#' }

            newMatrix[i][j] = when (c) {
                '#' -> if (count in 2..3) '#' else '.'
                else -> if (count == 3) '#' else '.'
            }

            if (partTwo && (i == 0 || i == matrix.lastIndex) && (j == 0 || j == chars.lastIndex)) newMatrix[i][j] = '#'
        }
    }
    return newMatrix
}
