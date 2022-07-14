package ru.timakden.aoc.year2016.day02

import ru.timakden.aoc.util.Constants.Part
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Part): String {
    var point = if (part == PART_ONE) Pair(1, 1) else Pair(2, 0)
    val list = mutableListOf<Char>()

    input.forEach {
        point = goToTheNextPoint(point, it, part)

        if (part == PART_ONE) list.add(keypadPartOne[point.first][point.second])
        else list.add(keypadPartTwo[point.first][point.second])
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

private fun goToTheNextPoint(point: Pair<Int, Int>, instruction: String, part: Part): Pair<Int, Int> {
    var x = point.second
    var y = point.first

    instruction.forEach {
        when (it) {
            'U' -> if (!(y == 0 || (part == PART_TWO && keypadPartTwo[y - 1][x] == ' '))) y--
            'D' -> if ((part == PART_ONE && y != 2) || (part == PART_TWO && y != 4 && keypadPartTwo[y + 1][x] != ' ')) y++
            'R' -> if ((part == PART_ONE && x != 2) || (part == PART_TWO && x != 4 && keypadPartTwo[y][x + 1] != ' ')) x++
            'L' -> if (!(x == 0 || (part == PART_TWO && keypadPartTwo[y][x - 1] == ' '))) x--
        }
    }

    return y to x
}
