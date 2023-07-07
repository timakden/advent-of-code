package ru.timakden.aoc.year2015

import arrow.core.Either
import arrow.core.getOrElse
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day18 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day18")
            println("Part One: ${solve(input, 100)}")
            println("Part Two: ${solve(input, 100, true)}")
        }
    }

    fun solve(input: List<String>, numberOfSteps: Int, isPartTwo: Boolean = false): Int {
        var matrix = input.map { it.toCharArray() }.toTypedArray()

        repeat(numberOfSteps) { matrix = performStep(matrix, isPartTwo) }

        return matrix.sumOf { it.count { ch -> ch == '#' } }
    }

    private fun performStep(matrix: Array<CharArray>, isPartTwo: Boolean): Array<CharArray> {
        val size = matrix.size
        val newMatrix = Array(size) { CharArray(size) }

        matrix.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, c ->
                val neighbours = mutableListOf<Char>()

                for (k in (i - 1)..(i + 1)) {
                    for (l in (j - 1)..(j + 1)) {
                        if (k == i && l == j) continue // skip the current "cell"

                        neighbours += Either.catch { matrix[k][l] }.getOrElse { '.' }
                    }
                }

                val count = neighbours.count { it == '#' }

                newMatrix[i][j] = when (c) {
                    '#' -> if (count in 2..3) '#' else '.'
                    else -> if (count == 3) '#' else '.'
                }

                if (isPartTwo && (i == 0 || i == matrix.lastIndex) && (j == 0 || j == chars.lastIndex))
                    newMatrix[i][j] = '#'
            }
        }
        return newMatrix
    }
}
