package ru.timakden.adventofcode.year2016.day05

import ru.timakden.adventofcode.md5Hex
import kotlin.system.measureTimeMillis

fun main() {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: String): String {
    var count = 0L
    var encoded: String
    var password = ""

    repeat(8) {
        while (true) {
            encoded = md5Hex(input + count.toString())
            count++
            if (encoded.startsWith("00000")) {
                password += encoded[5]
                break
            }
        }
    }

    return password
}

fun solvePartTwo(input: String): String {
    var count = 0L
    var encoded: String
    var password = "________"
    var position: Int

    repeat(8) {
        while (true) {
            encoded = md5Hex(input + count.toString())
            count++
            if (encoded.startsWith("00000") && encoded[5].isDigit()) {
                position = encoded[5].toString().toInt()
                if (position in 0..7 && password[position] == '_') {
                    password = password.replaceRange(position, position + 1, encoded[6].toString())
                    break
                }
            }
        }
    }

    return password
}
