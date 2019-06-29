package ru.timakden.adventofcode.year2015.day06

import ru.timakden.adventofcode.measure

fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>): Int {
    var lights = 0

    val regex = "\\s(?!on|off)".toRegex()

    val lightsGrid = Array(1000) { BooleanArray(1000) }

    lightsGrid.forEach { booleans -> booleans.indices.forEach { booleans[it] = false } }

    input.forEach {
        val list = it.split(regex)
        val action = list[0]

        val coordinates1 = list[1].split(",")
        val coordinates2 = list[3].split(",")

        val pair1 = Pair(coordinates1[0].toInt(), coordinates1[1].toInt())
        val pair2 = Pair(coordinates2[0].toInt(), coordinates2[1].toInt())

        var i = pair1.first

        while (i <= pair2.first) {
            var j = pair1.second

            while (j <= pair2.second) {
                when (action) {
                    "toggle" -> lightsGrid[i][j] = !lightsGrid[i][j]
                    "turn on" -> lightsGrid[i][j] = true
                    "turn off" -> lightsGrid[i][j] = false
                }
                j++
            }
            i++
        }
    }

    lightsGrid.forEach { booleans -> lights += booleans.count { it } }

    return lights
}

fun solvePartTwo(input: List<String>): Int {
    var brightness = 0

    val regex = "\\s(?!on|off)".toRegex()

    val brightnessGrid = Array(1000) { IntArray(1000) }

    brightnessGrid.forEach { ints -> ints.indices.forEach { ints[it] = 0 } }

    input.forEach {
        val list = it.split(regex)
        val action = list[0]

        val coordinates1 = list[1].split(",")
        val coordinates2 = list[3].split(",")

        val pair1 = Pair(coordinates1[0].toInt(), coordinates1[1].toInt())
        val pair2 = Pair(coordinates2[0].toInt(), coordinates2[1].toInt())

        var i = pair1.first

        while (i <= pair2.first) {
            var j = pair1.second

            while (j <= pair2.second) {
                when (action) {
                    "toggle" -> brightnessGrid[i][j] += 2
                    "turn on" -> brightnessGrid[i][j]++
                    "turn off" -> if (brightnessGrid[i][j] > 0) brightnessGrid[i][j]--
                }
                j++
            }
            i++
        }
    }

    brightnessGrid.forEach { ints -> ints.forEach { brightness += it } }

    return brightness
}
