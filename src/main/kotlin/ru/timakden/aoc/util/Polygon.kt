package ru.timakden.aoc.util

import kotlin.math.absoluteValue

/**
 * Represents a polygon in a two-dimensional space.
 *
 * @property vertices A list of points representing the vertices of the polygon.
 * @constructor Creates a Polygon instance with the given vertices.
 */
data class Polygon(val vertices: List<Point>) {
    operator fun contains(point: Point) = point in vertices

    /**
     * Represents the size of a polygon, which is the number of vertices it has.
     *
     * @property size The size of the polygon.
     */
    val size: Int by lazy { vertices.size }

    /**
     * Represents the area of a polygon.
     *
     * The area of a polygon can be calculated using the shoelace formula, which sums the products
     * of the x-coordinates and the differences between the y-coordinates of consecutive vertices.
     * The absolute value of the result divided by 2 is the area of the polygon.
     *
     * @property vertices The list of points representing the vertices of the polygon.
     * @return The area of the polygon.
     */
    val area: Double by lazy {
        val n = vertices.size
        var area = 0.0

        var j = n - 1
        for (i in 0 until n) {
            val (x1, y1) = vertices[i]
            val (x2, y2) = vertices[j]

            area += (x2.toLong() + x1.toLong()) * (y2.toLong() - y1.toLong())

            j = i
        }

        (area / 2).absoluteValue
    }

    /**
     * Represents the perimeter of a polygon.
     *
     * The perimeter of a polygon is defined as the sum of the distances between consecutive vertices.
     *
     * @property vertices The list of points representing the vertices of the polygon.
     * @return The perimeter of the polygon.
     */
    val perimeter: Double by lazy {
        var perimeter = 0.0
        val n = vertices.size

        for (i in 0 until n) {
            val pointI = vertices[i]
            val pointJ = vertices[(i + 1) % n]

            perimeter += pointI.distanceTo(pointJ)
        }

        perimeter
    }
}
