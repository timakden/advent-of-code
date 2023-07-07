package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

object Day15 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day15")
            println("Part One: ${part1(input, 2000000)}")
            println("Part Two: ${part2(input, 4000000)}")
        }
    }

    fun part1(input: List<String>, row: Int): Int {
        val sensors = Sensors(input)
        val minColumn = sensors.sensorMap.values.map { it.minColumnCovered(row) }.minOf { it.second }
        val maxColumn = sensors.sensorMap.values.map { it.maxColumnCovered(row) }.maxOf { it.second }
        var currentCell = row to minColumn
        var count = 0
        while (currentCell.second <= maxColumn) {
            val coveredBySensor = sensors.coveredBySensor(currentCell)
            if (coveredBySensor != null) {
                val maxColumnCovered = coveredBySensor.maxColumnCovered(currentCell.first)
                count += maxColumnCovered.second - currentCell.second + 1
                count -= sensors.sensorsOnRow(currentCell.first, currentCell.second, maxColumnCovered.second).size
                count -= sensors.beaconsOnRow(currentCell.first, currentCell.second, maxColumnCovered.second).size
                currentCell = maxColumnCovered
            }
            currentCell = currentCell.first to (currentCell.second + 1)
        }
        return count
    }

    fun part2(input: List<String>, maxCoordinate: Int): Long {
        val notCoveredCell = checkNotNull(notCoveredCell(input, maxCoordinate))
        return notCoveredCell.second.toLong() * 4000000 + notCoveredCell.first.toLong()
    }

    class Sensor(val coordinates: Pair<Int, Int>, val beacon: Pair<Int, Int>) {
        private val beaconDistance: Int by lazy { manhattanDistance(coordinates, beacon) }
        private fun distanceTo(cell: Pair<Int, Int>) = manhattanDistance(coordinates, cell)

        fun covers(cell: Pair<Int, Int>) = beaconDistance >= distanceTo(cell)

        fun maxColumnCovered(row: Int) =
            row to coordinates.second + abs(beaconDistance - abs(row - coordinates.first))

        fun minColumnCovered(row: Int) =
            row to coordinates.second - abs(beaconDistance - abs(row - coordinates.first))
    }

    class Sensors(input: List<String>) {
        private val beacons: Set<Pair<Int, Int>>
        val sensorMap: Map<Pair<Int, Int>, Sensor>

        fun sensorsOnRow(row: Int, minColumn: Int, maxColumn: Int) =
            sensorMap.values.filter { it.coordinates.first == row && it.coordinates.second >= minColumn && it.coordinates.second <= maxColumn }

        fun beaconsOnRow(row: Int, minColumn: Int, maxColumn: Int) =
            beacons.filter { it.first == row && it.second >= minColumn && it.second <= maxColumn }

        init {
            val regex = "-?\\d+".toRegex()
            sensorMap = input.map { line -> regex.findAll(line).map { it.value.toInt() }.toList() }
                .map { Sensor(it[1] to it[0], it[3] to it[2]) }.associateBy { it.coordinates }
            beacons = sensorMap.values.map { it.beacon }.toSet()
        }

        fun coveredBySensor(cell: Pair<Int, Int>) = sensorMap.values.find { it.covers(cell) }
    }

    private fun manhattanDistance(p1: Pair<Int, Int>, p2: Pair<Int, Int>) =
        abs(p1.first - p2.first) + abs(p1.second - p2.second)

    private fun notCoveredCell(input: List<String>, maxCoordinate: Int): Pair<Int, Int>? {
        val sensors = Sensors(input)
        (0..maxCoordinate).forEach { row ->
            var currentCell = row to 0
            while (currentCell.second <= maxCoordinate) {
                val coveredBySensor = sensors.coveredBySensor(currentCell)
                if (coveredBySensor != null) {
                    val cell = coveredBySensor.maxColumnCovered(currentCell.first)
                    currentCell = cell.first to (cell.second + 1)
                } else return currentCell
            }
        }
        return null
    }
}
