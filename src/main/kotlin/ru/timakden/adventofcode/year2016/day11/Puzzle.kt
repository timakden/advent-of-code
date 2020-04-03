package ru.timakden.adventofcode.year2016.day11

import kotlin.system.measureTimeMillis

fun main() {
    val elapsedTime = measureTimeMillis {
        val configuration = mutableMapOf<Int, MutableList<String>>()
        input.forEachIndexed { index, s ->
            val list = mutableListOf<String>()
            list.add(s)
            configuration[index] = list
        }
        println(configuration)
    }
    println("Elapsed time: $elapsedTime ms")
}
