package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day11 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day11").single()
            var newPassword = solve(input)
            println("Part One: $newPassword")

            newPassword = solve(newPassword)
            println("Part Two: $newPassword")
        }
    }

    fun solve(input: String): String {
        var newPassword = incrementPassword(input)

        while (!checkPassword(newPassword)) {
            newPassword = incrementPassword(newPassword)
        }

        return newPassword
    }

    fun checkPassword(password: String): Boolean {
        val charArray = password.toCharArray()

        val firstRequirement = charArray.indices.any {
            it + 2 <= charArray.lastIndex &&
                    charArray[it + 1] == charArray[it] + 1 &&
                    charArray[it + 2] == charArray[it] + 2
        }
        val secondRequirement = "[^iol]".toRegex().containsMatchIn(password)
        val thirdRequirement = "(.)\\1.*(.)\\2".toRegex().containsMatchIn(password)

        return firstRequirement && secondRequirement && thirdRequirement
    }

    private fun incrementPassword(oldPassword: String): String {
        val charArray = oldPassword.toCharArray()
        var startIndex = charArray.lastIndex

        while (charArray[startIndex] == 'z') {
            startIndex--
        }

        (startIndex..charArray.lastIndex).forEach {
            charArray[it] = charArray[it] + 1

            if (!charArray[it].isLetter()) {
                charArray[it] = 'a'
            }
        }

        return String(charArray)
    }
}
