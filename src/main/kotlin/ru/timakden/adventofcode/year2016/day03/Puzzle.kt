package ru.timakden.adventofcode.year2016.day03

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: List<String>) = input.count { isTrianglePossible(it.split(" ")) }

fun solvePartTwo(input: List<String>): Int {
    var triangles = 0
    (0..input.lastIndex step 3).forEach { i ->
        val list = mutableListOf<List<String>>()
        (0..2).forEach { list.add(input[i + it].split(" ")) }
        (0..2).filter { isTrianglePossible(listOf(list[0][it], list[1][it], list[2][it])) }.forEach { triangles++ }
    }
    return triangles
}

private fun isTrianglePossible(sides: List<String>): Boolean {
    val side1 = sides[0].toInt()
    val side2 = sides[1].toInt()
    val side3 = sides[2].toInt()

    return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1
}
