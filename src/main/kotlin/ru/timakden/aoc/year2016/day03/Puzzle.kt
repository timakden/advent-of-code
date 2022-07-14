package ru.timakden.aoc.year2016.day03

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>) = input.count { isTrianglePossible(it.split(" ")) }

fun solvePartTwo(input: List<String>): Int {
    var triangles = 0
    (0..input.lastIndex step 3).forEach { i ->
        val list = mutableListOf<List<String>>()

        (0..2).forEach {
            list.add(input[i + it].split(" "))
        }

        repeat((0..2).filter { isTrianglePossible(listOf(list[0][it], list[1][it], list[2][it])) }.size) {
            triangles++
        }
    }
    return triangles
}

private fun isTrianglePossible(sides: List<String>): Boolean {
    val side1 = sides[0].toInt()
    val side2 = sides[1].toInt()
    val side3 = sides[2].toInt()

    return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1
}
