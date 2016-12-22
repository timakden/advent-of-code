package ru.timakden.adventofcode.year2015.day03

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: String): Int {
    var x = 0
    var y = 0
    val coordinates = mutableSetOf(Pair(0, 0))

    input.forEach { it ->
        when (it) {
            '^' -> y++
            'v' -> y--
            '<' -> x--
            '>' -> x++
        }

        coordinates.add(Pair(x, y))
    }

    return coordinates.size
}

fun solvePartTwo(input: String): Int {
    var santaX = 0
    var santaY = 0
    var robotX = 0
    var robotY = 0
    var counter = 0
    val coordinates = mutableSetOf(Pair(0, 0))

    input.forEach { it ->
        when (it) {
            '^' -> {
                if (counter % 2 == 0) {
                    santaY++
                } else robotY++
            }
            'v' -> {
                if (counter % 2 == 0) {
                    santaY--
                } else {
                    robotY--
                }
            }
            '<' -> {
                if (counter % 2 == 0) {
                    santaX--
                } else {
                    robotX--
                }
            }
            '>' -> {
                if (counter % 2 == 0) {
                    santaX++
                } else {
                    robotX++
                }
            }
        }

        when {
            counter % 2 == 0 -> coordinates.add(Pair(santaX, santaY))
            else -> coordinates.add(Pair(robotX, robotY))
        }

        counter++
    }

    return coordinates.size
}
