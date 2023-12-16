package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 8: Two-Factor Authentication](https://adventofcode.com/2016/day/8).
 */
object Day08 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day08")
            val screen = createScreen()
            println("Part One: ${part1(screen, input)}")
            printScreen(screen)
        }
    }

    fun part1(screen: Array<CharArray>, input: List<String>): Int {
        input.forEach { line ->
            if (line.startsWith("rect")) {
                val (width, height) = line.substringAfter(' ').split('x').map { it.toInt() }
                rect(screen, width, height)
            } else {
                val index = line.substringAfter('=').substringBefore(' ').toInt()
                val pixels = line.substringAfterLast(' ').toInt()
                if ("row" in line) rotateRow(screen, index, pixels) else rotateColumn(screen, index, pixels)
            }
        }

        return countLitPixels(screen)
    }

    private fun createScreen(width: Int = 50, height: Int = 6): Array<CharArray> {
        val screen = Array(height) { CharArray(width) }
        screen.forEach { row -> row.indices.forEach { row[it] = '.' } }
        return screen
    }

    private fun rect(screen: Array<CharArray>, width: Int, height: Int) {
        (0..<height).forEach { row -> (0..<width).forEach { column -> screen[row][column] = '#' } }
    }

    private fun rotateRow(screen: Array<CharArray>, row: Int, pixels: Int) {
        repeat(pixels) { screen[row] = shift(screen[row]) }
    }

    private fun rotateColumn(screen: Array<CharArray>, column: Int, pixels: Int) {
        var columnStr = ""
        (0..screen.lastIndex).forEach { columnStr += screen[it][column] }

        var array = columnStr.toCharArray()
        repeat(pixels) { array = shift(array) }

        (0..screen.lastIndex).forEach { screen[it][column] = array[it] }
    }

    private fun shift(array: CharArray): CharArray {
        val newArray = array.clone()
        newArray[0] = array[array.lastIndex]
        (0..<array.lastIndex).forEach {
            newArray[it + 1] = array[it]
        }
        return newArray
    }

    private fun countLitPixels(screen: Array<CharArray>) = screen.fold(0) { acc, chars ->
        acc + chars.count { it == '#' }
    }

    private fun printScreen(screen: Array<CharArray>) {
        println()
        screen.forEach(::println)
        println()
    }
}
