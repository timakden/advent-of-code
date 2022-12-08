package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day14 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2015/Day14")
            val raceTime = 2503

            println("Part One: ${part1(input, raceTime)}")
            println("Part Two: ${part2(input, raceTime)}")
        }
    }

    fun part1(input: List<String>, raceTime: Int) = race(input, raceTime)
        .maxByOrNull { it.totalDistance }?.totalDistance

    fun part2(input: List<String>, raceTime: Int) = race(input, raceTime)
        .maxByOrNull { it.scores }?.scores

    private fun race(input: List<String>, raceTime: Int): List<Reindeer> {
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

}
