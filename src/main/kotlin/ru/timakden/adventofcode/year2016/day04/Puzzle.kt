package ru.timakden.adventofcode.year2016.day04

import ru.timakden.adventofcode.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
}

fun solvePartOne(input: List<String>): Int {
    val realRooms = input.filter(::isRealRoom)

    return realRooms.fold(0) { acc, s ->
        val id = s.substring(s.lastIndexOf('-') + 1, s.indexOf('[')).toInt()
        acc + id
    }
}

fun solvePartTwo(input: List<String>): Int {
    val realRooms = input.filter(::isRealRoom)

    realRooms.forEach {
        val encryptedName = it.substringBeforeLast('-')
        val id = it.substring(it.lastIndexOf('-') + 1, it.indexOf('[')).toInt()
        val decryptedName = decryptRoomName(encryptedName, id)
        if (decryptedName == "northpole object storage") return id
    }

    return 0
}

private fun isRealRoom(room: String): Boolean {
    val encryptedName = room.substringBeforeLast('-').replace("-", "")
    val checksum = room.substring(room.indexOf('[') + 1, room.indexOf(']'))
    return calculateChecksum(encryptedName) == checksum
}

private fun calculateChecksum(encryptedName: String): String {
    val map = sortedMapOf<Char, Int>()
    encryptedName.forEach {
        var count = map[it] ?: 0
        map[it] = ++count
    }

    val newMap = mutableMapOf<Char, Int>()
    for (i in 0..4) {
        val entry = map.maxBy { it.value }
        entry?.let {
            newMap[it.key] = it.value
            map.remove(it.key)
        }
    }

    var checksum = ""
    newMap.forEach { checksum += it.key.toString() }
    return checksum
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

private fun getNextLetter(char: Char): Char {
    return when (char) {
        ' ' -> ' '
        '-' -> ' '
        'z' -> 'a'
        else -> char + 1
    }
}
