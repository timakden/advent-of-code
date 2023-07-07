package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.md5
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day05 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day05").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): String {
        var count = 0L
        var encoded: String
        var password = ""

        repeat(8) {
            while (true) {
                encoded = (input + count.toString()).md5()
                count++
                if (encoded.startsWith("00000")) {
                    password += encoded[5]
                    break
                }
            }
        }

        return password
    }

    fun part2(input: String): String {
        var count = 0L
        var encoded: String
        var password = "________"
        var position: Int

        repeat(8) {
            while (true) {
                encoded = (input + count.toString()).md5()
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
}
