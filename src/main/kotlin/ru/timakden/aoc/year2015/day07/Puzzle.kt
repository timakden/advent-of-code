package ru.timakden.aoc.year2015.day07

import ru.timakden.aoc.util.isLetter
import ru.timakden.aoc.util.isNumber
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("a = ${solve(input, listOf("a"))["a"]}")
    }
}

fun solve(input: List<String>, wiresToReturn: List<String>): Map<String, UShort> {
    val map = mutableMapOf<String, UShort>()
    while (map.size != input.size) {
        input.forEach {
            val expressions = it.split("\\s->\\s".toRegex())
            val leftPart = expressions[0].split("\\s(?!or|and|lshift|rshift)".toRegex())

            when (leftPart.size) {
                1 -> {
                    // example: 44430 -> b
                    if (leftPart[0].isNumber()) {
                        map[expressions[1]] = leftPart[0].toUShort()
                    } else if (leftPart[0].isLetter()) {
                        map[leftPart[0]]?.let { value -> map[expressions[1]] = value }
                    }
                }
                2 -> {
                    // example: NOT di -> dj
                    if (leftPart[1].isNumber()) {
                        map[expressions[1]] = leftPart[1].toUShort().inv()
                    } else if (leftPart[1].isLetter()) {
                        map[leftPart[1]]?.let { value -> map[expressions[1]] = value.inv() }
                    }
                }
                3 -> {
                    // example: dd OR do -> dp
                    var val1: UShort? = null
                    var val2: UShort? = null

                    if (leftPart[0].isNumber()) {
                        val1 = leftPart[0].toUShort()
                    } else if (leftPart[0].isLetter()) {
                        val1 = map[leftPart[0]]
                    }

                    if (leftPart[2].isNumber()) {
                        val2 = leftPart[2].toUShort()
                    } else if (leftPart[2].isLetter()) {
                        val2 = map[leftPart[2]]
                    }

                    if (val1 != null && val2 != null) {
                        when (leftPart[1]) {
                            "AND" -> map[expressions[1]] = val1 and val2
                            "OR" -> map[expressions[1]] = val1 or val2
                            "LSHIFT" -> map[expressions[1]] = val1 shl val2
                            "RSHIFT" -> map[expressions[1]] = val1 shr val2
                        }
                    }
                }
            }
        }
    }

    return map.filter { it.key in wiresToReturn }
}

private infix fun UShort.shl(shift: UShort): UShort = (this.toInt() shl shift.toInt()).toUShort()

private infix fun UShort.shr(shift: UShort): UShort = (this.toInt() shr shift.toInt()).toUShort()
