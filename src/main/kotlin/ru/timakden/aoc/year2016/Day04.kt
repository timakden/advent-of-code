package ru.timakden.aoc.year2016

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>) = input.filter(isRealRoom).fold(0) { acc, s ->
        val id = s.substring(s.lastIndexOf('-') + 1, s.indexOf('[')).toInt()
        acc + id
    }

    fun part2(input: List<String>): Option<Int> {
        input.filter(isRealRoom).forEach {
            val encryptedName = it.substringBeforeLast('-')
            val id = it.substring(it.lastIndexOf('-') + 1, it.indexOf('[')).toInt()
            val decryptedName = decryptRoomName(encryptedName, id)
            if (decryptedName == "northpole object storage") return id.some()
        }

        return none()
    }

    private val isRealRoom: (String) -> Boolean = {
        val encryptedName = it.substringBeforeLast('-').replace("-", "")
        val checksum = it.substring(it.indexOf('[') + 1, it.indexOf(']'))
        calculateChecksum(encryptedName) == checksum
    }

    private fun calculateChecksum(encryptedName: String): String {
        val map = sortedMapOf<Char, Int>()
        encryptedName.forEach {
            var count = map[it] ?: 0
            map[it] = ++count
        }

        val newMap = mutableMapOf<Char, Int>()
        repeat(5) {
            map.maxByOrNull { it.value }?.let {
                newMap[it.key] = it.value
                map.remove(it.key)
            }
        }

        return newMap.keys.fold("") { acc, c -> acc + c.toString() }
    }

    private fun decryptRoomName(encryptedName: String, id: Int): String {
        var decryptedName = ""
        encryptedName.forEach {
            var nextLetter = it
            repeat((1..id).count()) { nextLetter = getNextLetter(nextLetter) }
            decryptedName += nextLetter.toString()
        }
        return decryptedName
    }

    private fun getNextLetter(char: Char) = when (char) {
        ' ' -> ' '
        '-' -> ' '
        'z' -> 'a'
        else -> char + 1
    }
}
