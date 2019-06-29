package ru.timakden.adventofcode.year2015.day14

import ru.timakden.adventofcode.measure

fun main() {
    measure {
        val raceTime = 2503
        val deers = race(input, raceTime)

        println("Part One: ${deers.maxBy { it.totalDistance }?.totalDistance}")
        println("Part Two: ${deers.maxBy { it.scores }?.scores}")
    }
}

fun race(input: List<String>, raceTime: Int): List<Reindeer> {
    val deers = input.map { Reindeer(it.split("\\s".toRegex())) }

    repeat(raceTime) {
        deers.forEach { deer ->
            if (deer.isMoving && deer.currentMoveTime < deer.moveTime) {
                deer.totalDistance += deer.distancePerSecond
                deer.currentMoveTime++
            } else if (deer.isMoving && deer.currentMoveTime == deer.moveTime) {
                deer.currentRestTime = 1
                deer.isMoving = false
            } else if (!deer.isMoving && deer.currentRestTime < deer.restTime) {
                deer.currentRestTime++
            } else {
                deer.currentMoveTime = 1
                deer.totalDistance += deer.distancePerSecond
                deer.isMoving = true
            }
        }

        val currentLeadingDistance = deers.maxBy { it.totalDistance }?.totalDistance

        deers.forEach { if (it.totalDistance == currentLeadingDistance) it.scores++ }
    }

    return deers
}

data class Reindeer(
    var name: String, var distancePerSecond: Int, var moveTime: Int, var restTime: Int,
    var totalDistance: Int = 0, var currentMoveTime: Int = 0, var currentRestTime: Int = 0,
    var isMoving: Boolean = true, var scores: Int = 0
) {
    constructor(split: List<String>) : this(
        name = split[0], distancePerSecond = split[3].toInt(),
        moveTime = split[6].toInt(), restTime = split[13].toInt()
    )
}
