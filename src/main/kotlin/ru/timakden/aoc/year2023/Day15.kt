package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 15: Lens Library](https://adventofcode.com/2023/day/15).
 */
object Day15 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day15").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: String) = input.split(",").sumOf { it.hash() }

    fun part2(input: String): Int {
        val boxes = mutableMapOf<Int, MutableList<Pair<String, Int>>>()
        input.split(",").forEach { s ->
            val label = if (s.contains("-")) s.substringBefore("-") else s.substringBefore("=")
            val boxNumber = label.hash()
            val box = boxes[boxNumber]
            if (s.contains("-")) {
                box?.removeIf { it.first == label }
            } else {
                val focalLength = s.substringAfter("=").toInt()
                if (box == null) {
                    boxes[boxNumber] = mutableListOf(label to focalLength)
                } else {
                    val idx = box.indexOfFirst { it.first == label }
                    if (idx != -1) box[idx] = label to focalLength else box += label to focalLength
                }
            }
        }

        return boxes.entries.sumOf { (boxIdx, lenses) ->
            lenses.foldIndexed(0.toInt()) { lensIdx, acc, (label, focalLength) ->
                acc + ((boxIdx + 1) * (lensIdx + 1) * focalLength)
            }
        }
    }

    private fun String.hash(): Int {
        var result = 0
        this.forEach { c ->
            val asciiCode = c.code
            result += asciiCode
            result *= 17
            result %= 256
        }
        return result
    }
}
