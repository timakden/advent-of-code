package ru.timakden.adventofcode.year2016.day02

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
}

fun solve(input: List<String>, partTwo: Boolean): String {
    var point = if (!partTwo) Pair(1, 1) else Pair(2, 0)
    val list = mutableListOf<Char>()

    input.forEach {
        point = goToTheNextPoint(point, it, partTwo)
        if (!partTwo)
            list.add(keypadPartOne[point.first][point.second])
        else
            list.add(keypadPartTwo[point.first][point.second])
    }

    return list.joinToString("")
}

private val keypadPartOne = arrayOf(
    charArrayOf('1', '2', '3'),
    charArrayOf('4', '5', '6'),
    charArrayOf('7', '8', '9')
)

private val keypadPartTwo = arrayOf(
    charArrayOf(' ', ' ', '1', ' ', ' '),
    charArrayOf(' ', '2', '3', '4', ' '),
    charArrayOf('5', '6', '7', '8', '9'),
    charArrayOf(' ', 'A', 'B', 'C', ' '),
    charArrayOf(' ', ' ', 'D', ' ', ' ')
)

private fun goToTheNextPoint(point: Pair<Int, Int>, instruction: String, partTwo: Boolean): Pair<Int, Int> {
    var x = point.second
    var y = point.first

    instruction.forEach {
        when (it) {
            'U' -> if (!(y == 0 || (partTwo && keypadPartTwo[y - 1][x] == ' '))) y--
            'D' -> if ((!partTwo && y != 2) || (partTwo && y != 4 && keypadPartTwo[y + 1][x] != ' ')) y++
            'R' -> if ((!partTwo && x != 2) || (partTwo && x != 4 && keypadPartTwo[y][x + 1] != ' ')) x++
            'L' -> if (!(x == 0 || (partTwo && keypadPartTwo[y][x - 1] == ' '))) x--
        }
    }

    return y to x
}
