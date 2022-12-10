package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.md5
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day14 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day14").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String): Int {
        var foundKeys = 0
        var index = 0

        val hashes = mutableMapOf<Int, String>()

        while (true) {
            var hash = hashes.getOrPut(index) { "$input$index".md5() }

            "(.)\\1\\1".toRegex().find(hash)?.value?.first()?.let { symbol ->
                for (it in index + 1..index + 1000) {
                    hash = hashes.getOrPut(it) { "$input$it".md5() }
                    if ("($symbol)\\1{4}".toRegex().containsMatchIn(hash)) {
                        if (++foundKeys == 64) return index
                        break
                    }
                }
            }

            index++
        }
    }

    fun part2(input: String): Int {
        var foundKeys = 0
        var index = 0

        val hashes = mutableMapOf<Int, String>()

        while (true) {
            var hash = hashes.getOrPut(index) {
                var temp = "$input$index".md5()
                repeat(2016) { temp = temp.md5() }
                temp
            }

            "(.)\\1\\1".toRegex().find(hash)?.value?.first()?.let { symbol ->
                for (it in index + 1..index + 1000) {
                    hash = hashes.getOrPut(it) {
                        var temp = "$input$it".md5()
                        repeat(2016) { temp = temp.md5() }
                        temp
                    }

                    if ("($symbol)\\1{4}".toRegex().containsMatchIn(hash)) {
                        if (++foundKeys == 64) return index
                        break
                    }
                }
            }

            index++
        }
    }
}
