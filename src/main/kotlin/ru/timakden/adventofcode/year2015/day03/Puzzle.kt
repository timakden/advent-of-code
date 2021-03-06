package ru.timakden.adventofcode.year2015.day03

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: String): Int {
    var x = 0
    var y = 0
    val coordinates = mutableSetOf(Pair(0, 0))

    input.forEach {
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

    input.forEach {
        val isCounterEven = counter % 2 == 0

        when (it) {
            '^' -> if (isCounterEven) santaY++ else robotY++
            'v' -> if (isCounterEven) santaY-- else robotY--
            '<' -> if (isCounterEven) santaX-- else robotX--
            '>' -> if (isCounterEven) santaX++ else robotX++
        }

        when {
            isCounterEven -> coordinates.add(Pair(santaX, santaY))
            else -> coordinates.add(Pair(robotX, robotY))
        }

        counter++
    }

    return coordinates.size
}
