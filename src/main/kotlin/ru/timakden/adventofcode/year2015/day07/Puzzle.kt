package ru.timakden.adventofcode.year2015.day07

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("a = ${solve(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: List<String>): Int? {
    val map = mutableMapOf<String, Int>()
    while (map.size != input.size) {
        input.forEach { it ->
            val expressions = it.split("\\s->\\s".toRegex())
            val leftPart = expressions[0].split("\\s(?!or|and|lshift|rshift)".toRegex())

            when (leftPart.size) {
                1 -> {
                    // example: 44430 -> b
                    if (leftPart[0].matches("\\d+".toRegex()))
                        map.put(expressions[1], leftPart[0].toInt())
                    else if (leftPart[0].matches("[a-zA-Z]+".toRegex()))
                        map[leftPart[0]]?.let { map.put(expressions[1], it) }
                }
                2 -> {
                    // example: NOT di -> dj
                    if (leftPart[1].matches("\\d+".toRegex()))
                        map.put(expressions[1], leftPart[1].toInt().inv())
                    else if (leftPart[1].matches("[a-zA-Z]+".toRegex()))
                        map[leftPart[1]]?.let { map.put(expressions[1], it.inv()) }
                }
                3 -> {
                    // example: dd OR do -> dp
                    var val1: Int? = null
                    var val2: Int? = null

                    if (leftPart[0].matches("\\d+".toRegex()))
                        val1 = leftPart[0].toInt()
                    else if (leftPart[0].matches("[a-zA-Z]+".toRegex()))
                        val1 = map[leftPart[0]]

                    if (leftPart[2].matches("\\d+".toRegex()))
                        val2 = leftPart[2].toInt()
                    else if (leftPart[2].matches("[a-zA-Z]+".toRegex()))
                        val2 = map[leftPart[2]]

                    if (val1 != null && val2 != null) {
                        when (leftPart[1]) {
                            "AND" -> map.put(expressions[1], val1 and val2)
                            "OR" -> map.put(expressions[1], val1 or val2)
                            "LSHIFT" -> map.put(expressions[1], val1 shl val2)
                            "RSHIFT" -> map.put(expressions[1], val1 shr val2)
                        }
                    }
                }
            }
        }
    }
    return map["a"]
}
