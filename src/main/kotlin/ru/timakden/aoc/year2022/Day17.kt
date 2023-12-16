package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day17 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day17").single()
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input, 1000000000000L)}")
        }
    }

    fun part1(input: String): Int {
        val wind = windIterator(input)
        val shapes = shapeIterator()
        val occupied = mutableSetOf<Point>()
        repeat(2022) {
            val currentTop = occupied.top
            var current = shapes.next().relTo(currentTop).tryMove(occupied, Point(2, 0))
            while (true) {
                current = current.tryMove(occupied, windVector(wind))
                val tryFall = current.tryMove(occupied, Point(0, -1))
                if (tryFall == current) {
                    occupied.addAll(current.points)
                    break
                } else current = tryFall
            }
        }
        return occupied.maxOf(Point::y) + 1
    }

    fun part2(input: String, lastShape: Long): Long {
        val wind = windIterator(input)

        val shapes = shapeIterator()
        val occupied = hashSetOf<Point>()
        val diffs = StringBuilder()
        for (i in 0..<lastShape) {
            val currentTop = occupied.top
            var cur = shapes.next().relTo(currentTop).tryMove(occupied, Point(2, 0))
            while (true) {
                val windVector = windVector(wind)
                cur = cur.tryMove(occupied, windVector)
                val tryFall = cur.tryMove(occupied, Point(0, -1))
                if (tryFall == cur) {
                    occupied.addAll(cur.points)
                    diffs.append(occupied.top - currentTop)
                    val periodicSequenceSearchLength = 20
                    if (diffs.length > periodicSequenceSearchLength * 2) {
                        val repetitions = diffs.windowed(periodicSequenceSearchLength).count {
                            it == diffs.takeLast(periodicSequenceSearchLength)
                        }
                        if (repetitions > 1) {
                            val (start, period) = diffs.asSequence().withIndex().windowed(periodicSequenceSearchLength)
                                .map {
                                    val foundIndex = diffs.indexOf(
                                        it.map(IndexedValue<Char>::value).joinToString(""),
                                        it.last().index
                                    )
                                    it.first().index to (foundIndex - it.first().index)
                                }.firstOrNull { it.second >= 0 } ?: break
                            val periodicSequence = diffs.substring(start..<start + period)
                            val numberOfRepetitions = (lastShape - start) / period
                            val repetitionIncrement = periodicSequence.map(Char::digitToInt).sum()
                            val startIncrement = diffs.substring(0..<start).map(Char::digitToInt).sum()
                            val remainder = lastShape - (start - 1) - (numberOfRepetitions * period) - 1
                            val tailIncrement =
                                periodicSequence.take(remainder.toInt()).map(Char::digitToInt).sum()
                            return startIncrement.toLong() + (numberOfRepetitions * repetitionIncrement) + tailIncrement
                        }
                    }
                    break
                } else cur = tryFall
            }
        }
        return -1L
    }

    private fun windVector(wind: Iterator<Char>) = when (wind.next()) {
        '>' -> Point(1, 0)
        '<' -> Point(-1, 0)
        else -> error("Unsupported wind direction")
    }

    private fun windIterator(input: String) = iterator {
        while (true) {
            yieldAll(input.toList())
        }
    }

    private fun shapeIterator() = iterator {
        val x = buildList {
            add(Shape(0, 0, 1, 0, 2, 0, 3, 0))
            add(Shape(1, 0, 0, 1, 1, 1, 2, 1, 1, 2))
            add(Shape(0, 0, 1, 0, 2, 0, 2, 1, 2, 2))
            add(Shape(0, 0, 0, 1, 0, 2, 0, 3))
            add(Shape(0, 0, 1, 0, 0, 1, 1, 1))
        }
        while (true) {
            yieldAll(x)
        }
    }

    private val Set<Point>.top
        get() = if (isEmpty()) -1 else maxOf(Point::y)

    data class Shape(val points: List<Point>) {
        constructor(vararg points: Int) : this(points.asSequence().chunked(2) { (a, b) -> Point(a, b) }.toList())

        fun relTo(currentTop: Int) = Shape(points.map { it.copy(y = currentTop + it.y + 4) })

        fun tryMove(occupied: Set<Point>, vector: Point): Shape {
            val nextPos = points.map { it.move(vector) }
            for (point in nextPos) {
                if (occupied.contains(point) || point.x < 0 || point.x > 6 || point.y < 0) return this
            }
            return Shape(nextPos)
        }
    }
}
