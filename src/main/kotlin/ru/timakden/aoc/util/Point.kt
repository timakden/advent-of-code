package ru.timakden.aoc.util

import kotlin.math.abs

/**
 * Represents a point in a two-dimensional space.
 *
 * @property x The x-coordinate of the point.
 * @property y The y-coordinate of the point.
 *
 * @constructor Creates a Point instance.
 */
data class Point(val x: Int = 0, val y: Int = 0) {
    /**
     * Moves the point by adding the coordinates of the given vector to the current coordinates.
     *
     * @param vector The vector representing the distance to move the point.
     * @return A new Point instance with the updated coordinates.
     */
    fun move(vector: Point) = Point(x + vector.x, y + vector.y)


    /**
     * Checks if a given point is inside a polygon. Sources:
     * * [Habr](https://habr.com/ru/articles/125356/)
     * * [Wikipedia](https://en.wikipedia.org/wiki/Point_in_polygon)
     *
     * @param polygon The list of points representing the polygon.
     * @return true if the point is inside the polygon, false otherwise.
     */
    fun isInPolygon(polygon: List<Point>): Boolean {
        val qPatt = arrayOf(intArrayOf(0, 1), intArrayOf(3, 2))

        if (polygon.size < 3) return false

        var predPoint = Pair(polygon.last().x - x, polygon.last().y - y)
        var predQ = qPatt[if (predPoint.second < 0) 1 else 0][if (predPoint.first < 0) 1 else 0]
        var w = 0

        for (point in polygon) {
            val curPoint = Pair(point.x - x, point.y - y)
            val q = qPatt[if (curPoint.second < 0) 1 else 0][if (curPoint.first < 0) 1 else 0]

            when (q - predQ) {
                -3 -> ++w
                3 -> --w
                -2 -> if (predPoint.first * curPoint.second >= predPoint.second * curPoint.first) ++w
                2 -> if (predPoint.first * curPoint.second < predPoint.second * curPoint.first) --w
            }

            predPoint = curPoint
            predQ = q
        }

        return w != 0
    }

    /**
     * Calculates the Manhattan distance between this point and the given point.
     *
     * The Manhattan distance is defined as the sum of the absolute differences between the
     * x-coordinates and the y-coordinates of two points.
     *
     * @param other The other point to calculate the distance to.
     * @return The Manhattan distance between this point and the other point.
     */
    fun manhattanDistanceTo(other: Point) = abs(x - other.x) + abs(y - other.y)

    override fun toString() = "($x, $y)"
}
