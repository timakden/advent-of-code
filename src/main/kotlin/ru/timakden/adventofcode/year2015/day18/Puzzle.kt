package ru.timakden.adventofcode.year2015.day18

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }

    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>, partTwo: Boolean): Int {
    var matrix = Array(100, { CharArray(100) })

    input.indices.forEach { matrix[it] = input[it].toCharArray() }

    (1..100).forEach { matrix = performStep(matrix, partTwo) }

    return matrix.sumBy { it.count { it == '#' } }
}

private fun performStep(matrix: Array<CharArray>, partTwo: Boolean): Array<CharArray> {
    val newMatrix = Array(100, { CharArray(100) })

    (0..99).forEach { i ->
        (0..99).forEach { j ->
            val neighbours = CharArray(8)

            if (i > 0 && i < 99 && j > 0 && j < 99) {
                neighbours[0] = matrix[i - 1][j - 1]
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = matrix[i - 1][j + 1]
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = matrix[i + 1][j - 1]
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = matrix[i + 1][j + 1]
            } else if (i == 0 && j == 0) {
                neighbours[0] = '.'
                neighbours[1] = '.'
                neighbours[2] = '.'
                neighbours[3] = '.'
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = '.'
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = matrix[i + 1][j + 1]
            } else if (i == 0 && j == 99) {
                neighbours[0] = '.'
                neighbours[1] = '.'
                neighbours[2] = '.'
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = '.'
                neighbours[5] = matrix[i + 1][j - 1]
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = '.'
            } else if (i == 99 && j == 0) {
                neighbours[0] = '.'
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = matrix[i - 1][j + 1]
                neighbours[3] = '.'
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = '.'
                neighbours[6] = '.'
                neighbours[7] = '.'
            } else if (i == 99 && j == 99) {
                neighbours[0] = matrix[i - 1][j - 1]
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = '.'
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = '.'
                neighbours[5] = '.'
                neighbours[6] = '.'
                neighbours[7] = '.'
            } else if (i == 0 && j > 0 && j < 99) {
                neighbours[0] = '.'
                neighbours[1] = '.'
                neighbours[2] = '.'
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = matrix[i + 1][j - 1]
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = matrix[i + 1][j + 1]
            } else if (i == 99 && j > 0 && j < 99) {
                neighbours[0] = matrix[i - 1][j - 1]
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = matrix[i - 1][j + 1]
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = '.'
                neighbours[6] = '.'
                neighbours[7] = '.'
            } else if (i > 0 && i < 99 && j == 0) {
                neighbours[0] = '.'
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = matrix[i - 1][j + 1]
                neighbours[3] = '.'
                neighbours[4] = matrix[i][j + 1]
                neighbours[5] = '.'
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = matrix[i + 1][j + 1]
            } else if (i > 0 && i < 99 && j == 99) {
                neighbours[0] = matrix[i - 1][j - 1]
                neighbours[1] = matrix[i - 1][j]
                neighbours[2] = '.'
                neighbours[3] = matrix[i][j - 1]
                neighbours[4] = '.'
                neighbours[5] = matrix[i + 1][j - 1]
                neighbours[6] = matrix[i + 1][j]
                neighbours[7] = '.'
            }

            val sharpCount = neighbours.count { it == '#' }

            if (matrix[i][j] == '#') {
                if (sharpCount == 2 || sharpCount == 3) newMatrix[i][j] = '#' else newMatrix[i][j] = '.'
            } else {
                if (sharpCount == 3) newMatrix[i][j] = '#' else newMatrix[i][j] = '.'
            }

            if (partTwo && (i == 0 || i == 99) && (j == 0 || j == 99)) newMatrix[i][j] = '#'
        }
    }
    return newMatrix
}
