package ru.timakden.adventofcode.year2015.day17

import ru.timakden.adventofcode.powerSet
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val containers = mutableListOf<List<Int>>()

    val elapsedTime = measureTimeMillis {
        input.powerSet().forEach {
            if (it.sum() == 150) {
                containers.add(it)
            }
        }

        val minSize = containers.minBy { it.size }?.size

        println("Part One: ${containers.size}")
        println("Part Two: ${containers.count { it.size == minSize }}")
    }
    println("Elapsed time: $elapsedTime ms")
}
