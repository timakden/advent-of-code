package ru.timakden.adventofcode.year2016.day11

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        val configuration = mutableMapOf<Int, MutableList<String>>()
        input.forEachIndexed { index, s ->
            val list = mutableListOf<String>()
            list.add(s)
            configuration[index] = list
        }
        println(configuration)
    }
}
