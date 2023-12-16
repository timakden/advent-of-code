package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.properties.Delegates

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
                val point = Point(column, row)
                point to Tile(c, point)
            }
        }.toMap()

        tiles.forEach { (point, tile) ->
            when (tile.symbol) {
                '|' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x, point.y + 1)]
                )

                '-' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x - 1, point.y)],
                    tiles[Point(point.x + 1, point.y)]
                )

                'L' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x + 1, point.y)]
                )

                'J' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x - 1, point.y)]
                )

                '7' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y + 1)],
                    tiles[Point(point.x - 1, point.y)]
                )

                'F' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y + 1)],
                    tiles[Point(point.x + 1, point.y)]
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
                val point = Point(column, row)
                point to Tile(c, point)
            }
        }.toMap()

        tiles.forEach { (point, tile) ->
            when (tile.symbol) {
                '|' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x, point.y + 1)]
                )

                '-' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x - 1, point.y)],
                    tiles[Point(point.x + 1, point.y)]
                )

                'L' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x + 1, point.y)]
                )

                'J' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y - 1)],
                    tiles[Point(point.x - 1, point.y)]
                )

                '7' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y + 1)],
                    tiles[Point(point.x - 1, point.y)]
                )

                'F' -> tile.connectedTiles = listOfNotNull(
                    tiles[Point(point.x, point.y + 1)],
                    tiles[Point(point.x + 1, point.y)]
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

        val polygon = loop.map { it.point }

        return tiles.keys.count { it !in polygon && it.isInPolygon(polygon) }
    }

    private data class Tile(val symbol: Char, val point: Point) {
        var connectedTiles: List<Tile> = emptyList()
    }
}
