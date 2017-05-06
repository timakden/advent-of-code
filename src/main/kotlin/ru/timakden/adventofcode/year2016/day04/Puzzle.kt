package ru.timakden.adventofcode.year2016.day04

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solvePartOne(input)}")
        println("Part Two: ${solvePartTwo(input)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(input: List<String>): Int {
    val realRooms = input.filter(::isRealRoom)
    var totalCount = 0

    realRooms.forEach {
        val id = it.substring(it.lastIndexOf('-') + 1, it.indexOf('[')).toInt()
        totalCount += id
    }

    return totalCount
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
        (1..id).forEach { nextLetter = getNextLetter(nextLetter) }
        decryptedName += nextLetter.toString()
    }
    return decryptedName
}

private fun getNextLetter(char: Char): Char {
    when (char) {
        ' ' -> return ' '
        '-' -> return ' '
        'z' -> return 'a'
        else -> return char + 1
    }
}
