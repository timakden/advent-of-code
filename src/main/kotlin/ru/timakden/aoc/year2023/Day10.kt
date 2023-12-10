package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.properties.Delegates

typealias Coordinate = Pair<Int, Int>

object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day10")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val tiles = input.flatMapIndexed { row, s ->
            s.mapIndexed { column, c ->
                val coordinate = Coordinate(row, column)
                coordinate to Tile(c, coordinate)
            }
        }.toMap()

        tiles.forEach { (coordinate, tile) ->
            when (tile.symbol) {
                '|' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)]
                )

                '-' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )

                'L' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )

                'J' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)]
                )

                '7' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)]
                )

                'F' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )
            }
        }

        val sTile = tiles.filterValues { it.symbol == 'S' }.entries.single().value.apply {
            connectedTiles = tiles.values.filter { tile -> tile.connectedTiles.any { it.symbol == 'S' } }
        }

        val loop = mutableListOf(sTile)
        var currentTile = sTile
        var previousTile: Tile by Delegates.notNull()

        while (true) {
            val nextTile = when (currentTile) {
                sTile -> currentTile.connectedTiles.random()
                else -> currentTile.connectedTiles.single { it != previousTile && it.symbol != '.' }
            }

            if (nextTile == sTile) break

            loop += nextTile
            previousTile = currentTile
            currentTile = nextTile
        }

        return loop.size / 2
    }

    fun part2(input: List<String>): Int {
        val tiles = input.flatMapIndexed { row, s ->
            s.mapIndexed { column, c ->
                val coordinate = Coordinate(row, column)
                coordinate to Tile(c, coordinate)
            }
        }.toMap()

        tiles.forEach { (coordinate, tile) ->
            when (tile.symbol) {
                '|' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)]
                )

                '-' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )

                'L' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )

                'J' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first - 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)]
                )

                '7' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second - 1)]
                )

                'F' -> tile.connectedTiles = listOfNotNull(
                    tiles[Coordinate(coordinate.first + 1, coordinate.second)],
                    tiles[Coordinate(coordinate.first, coordinate.second + 1)]
                )
            }
        }

        val sTile = tiles.filterValues { it.symbol == 'S' }.entries.single().value.apply {
            connectedTiles = tiles.values.filter { tile -> tile.connectedTiles.any { it.symbol == 'S' } }
        }

        val loop = mutableListOf(sTile)
        var currentTile = sTile
        var previousTile: Tile by Delegates.notNull()

        while (true) {
            val nextTile = when (currentTile) {
                sTile -> currentTile.connectedTiles.random()
                else -> currentTile.connectedTiles.single { it != previousTile && it.symbol != '.' }
            }

            if (nextTile == sTile) break

            loop += nextTile
            previousTile = currentTile
            currentTile = nextTile
        }

        val polygon = loop.map { it.coordinate }

        return tiles.keys.count { it !in polygon && isPointInPolygon(it, polygon) }
    }

    private data class Tile(val symbol: Char, val coordinate: Coordinate) {
        var connectedTiles: List<Tile> = emptyList()
    }

    /**
     * Checks if a given point is inside a polygon.
     * Sources:
     * * [Habr](https://habr.com/ru/articles/125356/)
     * * [Wikipedia](https://en.wikipedia.org/wiki/Point_in_polygon)
     *
     * @param test The point to check.
     * @param polygon The polygon represented as a list of coordinates.
     *
     * @return True if the point is inside the polygon, false otherwise.
     */
    private fun isPointInPolygon(test: Coordinate, polygon: List<Coordinate>): Boolean {
        val qPatt = arrayOf(intArrayOf(0, 1), intArrayOf(3, 2))

        if (polygon.size < 3) return false

        var predPoint = Pair(polygon.last().first - test.first, polygon.last().second - test.second)
        var predQ = qPatt[if (predPoint.second < 0) 1 else 0][if (predPoint.first < 0) 1 else 0]
        var w = 0

        for (point in polygon) {
            val curPoint = Pair(point.first - test.first, point.second - test.second)
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
}
