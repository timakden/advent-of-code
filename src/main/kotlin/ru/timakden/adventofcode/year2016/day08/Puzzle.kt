package ru.timakden.adventofcode.year2016.day08

import kotlin.system.measureTimeMillis

fun main() {
    val elapsedTime = measureTimeMillis {
        val screen = createScreen()
        println("Part One: ${solvePartOne(screen, input)}")
        printScreen(screen)
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(screen: Array<CharArray>, input: List<String>): Int {
    input.forEach {
        if (it.startsWith("rect")) {
            val split = it.substringAfter(" ").split("x")
            val width = split[0].toInt()
            val height = split[1].toInt()
            rect(screen, width, height)
        } else {
            val index = it.substringAfter("=").substringBefore(" ").toInt()
            val pixels = it.substringAfterLast(" ").toInt()
            if (it.contains("row")) rotateRow(screen, index, pixels) else rotateColumn(screen, index, pixels)
        }
    }

    return countLitPixels(screen)
}

fun createScreen(width: Int = 50, height: Int = 6): Array<CharArray> {
    val screen = Array(height) { CharArray(width) }
    screen.forEach { row -> row.indices.forEach { row[it] = '.' } }
    return screen
}

private fun rect(screen: Array<CharArray>, width: Int, height: Int) {
    (0 until height).forEach { row -> (0 until width).forEach { column -> screen[row][column] = '#' } }
}

private fun rotateRow(screen: Array<CharArray>, row: Int, pixels: Int) {
    repeat(pixels) {
        screen[row] = shift(screen[row])
    }
}

private fun rotateColumn(screen: Array<CharArray>, column: Int, pixels: Int) {
    var columnStr = ""
    (0..screen.lastIndex).forEach {
        columnStr += screen[it][column]
    }

    var array = columnStr.toCharArray()
    repeat(pixels) {
        array = shift(array)
    }

    (0..screen.lastIndex).forEach {
        screen[it][column] = array[it]
    }
}

private fun shift(array: CharArray): CharArray {
    val newArray = array.clone()
    newArray[0] = array[array.lastIndex]
    (0 until array.lastIndex).forEach {
        newArray[it + 1] = array[it]
    }
    return newArray
}

private fun countLitPixels(screen: Array<CharArray>): Int {
    var count = 0
    screen.forEach { count += it.count { it == '#' } }
    return count
}

private fun printScreen(screen: Array<CharArray>) {
    println()
    screen.forEach(::println)
    println()
}
