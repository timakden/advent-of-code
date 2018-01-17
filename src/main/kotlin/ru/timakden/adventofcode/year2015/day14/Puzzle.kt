package ru.timakden.adventofcode.year2015.day14

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val raceTime = 2503
    val deers = mutableListOf<Reindeer>()

    val elapsedTime = measureTimeMillis {
        input.forEach {
            val split = it.split("\\s".toRegex())
            deers.add(
                Reindeer(
                    name = split[0], distancePerSecond = split[3].toInt(),
                    moveTime = split[6].toInt(), restTime = split[13].toInt()
                )
            )
        }

        (1..raceTime).forEach {
            deers.forEach {
                if (it.moving && it.currentMoveTime < it.moveTime) {
                    it.totalDistance += it.distancePerSecond
                    it.currentMoveTime++
                } else if (it.moving && it.currentMoveTime == it.moveTime) {
                    it.currentRestTime = 1
                    it.moving = false
                } else if (!it.moving && it.currentRestTime < it.restTime) {
                    it.currentRestTime++
                } else {
                    it.currentMoveTime = 1
                    it.totalDistance += it.distancePerSecond
                    it.moving = true
                }
            }

            val currentLeadingDistance = deers.maxBy { it.totalDistance }?.totalDistance

            deers.forEach { if (it.totalDistance == currentLeadingDistance) it.scores++ }
        }
    }
    println("Part One: ${deers.maxBy { it.totalDistance }?.totalDistance}")
    println("Part Two: ${deers.maxBy { it.scores }?.scores}")
    println("Elapsed time: $elapsedTime ms")
}

data class Reindeer(
    var name: String, var distancePerSecond: Int, var moveTime: Int, var restTime: Int,
    var totalDistance: Int = 0, var currentMoveTime: Int = 0, var currentRestTime: Int = 0,
    var moving: Boolean = true, var scores: Int = 0
)
