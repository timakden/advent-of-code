package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.abs

/**
 * [Day 15: Beacon Exclusion Zone](https://adventofcode.com/2022/day/15).
 */
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
        val minColumn = sensors.sensorMap.values.map { it.minColumnCovered(row) }.minOf { it.x }
        val maxColumn = sensors.sensorMap.values.map { it.maxColumnCovered(row) }.maxOf { it.x }
        var currentCell = Point(minColumn, row)
        var count = 0
        while (currentCell.x <= maxColumn) {
            val coveredBySensor = sensors.coveredBySensor(currentCell)
            if (coveredBySensor != null) {
                val maxColumnCovered = coveredBySensor.maxColumnCovered(currentCell.y)
                count += maxColumnCovered.x - currentCell.x + 1
                count -= sensors.sensorsOnRow(currentCell.y, currentCell.x, maxColumnCovered.x).size
                count -= sensors.beaconsOnRow(currentCell.y, currentCell.x, maxColumnCovered.x).size
                currentCell = maxColumnCovered
            }
            currentCell = Point((currentCell.x + 1), currentCell.y)
        }
        return count
    }

    fun part2(input: List<String>, maxCoordinate: Int): Long {
        val notCoveredCell = checkNotNull(notCoveredCell(input, maxCoordinate))
        return notCoveredCell.x.toLong() * 4000000 + notCoveredCell.y.toLong()
    }

    class Sensor(val point: Point, val beacon: Point) {
        private val beaconDistance: Int by lazy { point.distanceTo(beacon) }
        private fun distanceTo(cell: Point) = point.distanceTo(cell)

        fun covers(cell: Point) = beaconDistance >= distanceTo(cell)

        fun maxColumnCovered(row: Int) =
            Point(point.x + abs(beaconDistance - abs(row - point.y)), row)

        fun minColumnCovered(row: Int) =
            Point(point.x - abs(beaconDistance - abs(row - point.y)), row)
    }

    class Sensors(input: List<String>) {
        private val beacons: Set<Point>
        val sensorMap: Map<Point, Sensor>

        fun sensorsOnRow(row: Int, minColumn: Int, maxColumn: Int) =
            sensorMap.values.filter { it.point.y == row && it.point.x >= minColumn && it.point.x <= maxColumn }

        fun beaconsOnRow(row: Int, minColumn: Int, maxColumn: Int) =
            beacons.filter { it.y == row && it.x >= minColumn && it.x <= maxColumn }

        init {
            val regex = "-?\\d+".toRegex()
            sensorMap = input.map { line -> regex.findAll(line).map { it.value.toInt() }.toList() }
                .map { Sensor(Point(it[0], it[1]), Point(it[2], it[3])) }.associateBy { it.point }
            beacons = sensorMap.values.map { it.beacon }.toSet()
        }

        fun coveredBySensor(cell: Point) = sensorMap.values.find { it.covers(cell) }
    }

    private fun notCoveredCell(input: List<String>, maxCoordinate: Int): Point? {
        val sensors = Sensors(input)
        (0..maxCoordinate).forEach { row ->
            var currentCell = Point(0, row)
            while (currentCell.x <= maxCoordinate) {
                val coveredBySensor = sensors.coveredBySensor(currentCell)
                if (coveredBySensor != null) {
                    val cell = coveredBySensor.maxColumnCovered(currentCell.y)
                    currentCell = Point((cell.x + 1), cell.y)
                } else return currentCell
            }
        }
        return null
    }
}
