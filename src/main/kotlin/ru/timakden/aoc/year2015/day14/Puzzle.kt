package ru.timakden.aoc.year2015.day14

import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        val raceTime = 2503
        val reindeerList = race(input, raceTime)

        println("Part One: ${reindeerList.maxByOrNull { it.totalDistance }?.totalDistance}")
        println("Part Two: ${reindeerList.maxByOrNull { it.scores }?.scores}")
    }
}

fun race(input: List<String>, raceTime: Int): List<Reindeer> {
    val reindeerList = input.map { Reindeer(it.split("\\s".toRegex())) }

    repeat(raceTime) {
        reindeerList.forEach { deer ->
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

        val currentLeadingDistance = reindeerList.maxByOrNull { it.totalDistance }?.totalDistance

        reindeerList.forEach { if (it.totalDistance == currentLeadingDistance) it.scores++ }
    }

    return reindeerList
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
