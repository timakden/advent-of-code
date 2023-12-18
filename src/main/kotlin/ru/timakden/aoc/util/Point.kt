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
     * Adds the coordinates of the given point to this point, creating a new Point instance with the updated coordinates.
     *
     * @param other The point to be added.
     * @return A new Point instance with the updated coordinates.
     */
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    /**
     * Subtracts the coordinates of the given point from this point, creating a new Point instance with the updated coordinates.
     *
     * @param other The point to be subtracted.
     * @return A new Point instance with the updated coordinates.
     */
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)

    /**
     * Moves the current point downwards by subtracting the steps from the y-coordinate.
     *
     * @param steps The number of steps to move down. Default value is 1.
     * @return A new Point instance with updated coordinates.
     */
    fun moveDown(steps: Int = 1) = this + Point(0, -steps)

    /**
     * Moves the current point to the left by subtracting the given number of steps from the x-coordinate.
     *
     * @param steps The number of steps to move to the left. Default value is 1.
     * @return A new Point instance with updated coordinates.
     */
    fun moveLeft(steps: Int = 1) = this + Point(-steps, 0)

    /**
     * Moves the current point to the right by adding the given number of steps to the x-coordinate.
     *
     * @param steps The number of steps to move to the right. Default value is 1.
     * @return A new Point instance with updated coordinates.
     */
    fun moveRight(steps: Int = 1) = this + Point(steps, 0)

    /**
     * Moves the current point upwards by adding the steps to the y-coordinates.
     *
     * @param steps The number of steps to move up. Default value is 1.
     * @return A new Point instance with updated coordinates.
     */
    fun moveUp(steps: Int = 1) = this + Point(0, steps)

    /**
     * Checks if a point is inside a polygon. Sources:
     *
     * * [Habr](https://habr.com/ru/articles/125356/)
     * * [Wikipedia](https://en.wikipedia.org/wiki/Point_in_polygon)
     *
     * @param polygon The polygon to check against.
     * @return true if the point is inside the polygon, false otherwise.
     */
    fun isInPolygon(polygon: Polygon): Boolean {
        val qPatt = arrayOf(intArrayOf(0, 1), intArrayOf(3, 2))

        if (polygon.size < 3) return false

        var predPoint = Pair(polygon.vertices.last().x - x, polygon.vertices.last().y - y)
        var predQ = qPatt[if (predPoint.second < 0) 1 else 0][if (predPoint.first < 0) 1 else 0]
        var w = 0

        for (point in polygon.vertices) {
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
    fun distanceTo(other: Point) = abs(x - other.x) + abs(y - other.y)

    override fun toString() = "($x, $y)"
}
