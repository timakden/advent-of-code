package ru.timakden.adventofcode.year2015.day17

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val containers = mutableListOf<List<Int>>()

    val elapsedTime = measureTimeMillis {
        for (i in 1..1.shl(input.size)) {
            val list = mutableListOf<Int>()

            input.indices.forEach {
                if (i.shr(it).and(1) > 0) list.add(input[it])
            }

            if (list.sum() == 150) containers.add(list)
        }

        val minSize = containers.minBy { it.size }?.size

        println("Part One: ${containers.size}")
        println("Part Two: ${containers.count { it.size == minSize }}")
    }
    println("Elapsed time: $elapsedTime ms")
}
