package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.Point
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

/**
 * [Day 8: Treetop Tree House](https://adventofcode.com/2022/day/8).
 */
object Day08 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day08")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        var visibleTrees = 0
        val treeGrid = input.map { row -> row.map { it.digitToInt() } }
        val lastColumnIndex = treeGrid.first().lastIndex
        val lastRowIndex = treeGrid.lastIndex
        val heights = mutableMapOf<Point, Int>()

        treeGrid.forEachIndexed { i, trees -> trees.forEachIndexed { j, tree -> heights[Point(i, j)] = tree } }

        treeGrid.forEachIndexed { i, trees ->
            for (j in trees.indices) {
                val tree = trees[j]
                if (i == 0 || j == 0 || i == lastRowIndex || j == lastColumnIndex) visibleTrees++
                else {
                    if (heights.filterKeys { it.x == i && it.y in (0..<j) }.all { it.value < tree }) {
                        visibleTrees++
                        continue
                    }
                    if (heights.filterKeys { it.x == i && it.y in (j + 1..lastColumnIndex) }
                            .all { it.value < tree }
                    ) {
                        visibleTrees++
                        continue
                    }
                    if (heights.filterKeys { it.x in (0..<i) && it.y == j }.all { it.value < tree }) {
                        visibleTrees++
                        continue
                    }
                    if (heights.filterKeys { it.x in (i + 1..lastRowIndex) && it.y == j }
                            .all { it.value < tree }
                    ) {
                        visibleTrees++
                        continue
                    }
                }
            }
        }

        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        val treeGrid = input.map { row -> row.map { it.digitToInt() } }
        val lastColumnIndex = treeGrid.first().lastIndex
        val lastRowIndex = treeGrid.lastIndex
        val heights = mutableMapOf<Point, Int>()
        val scenicScores = mutableMapOf<Point, Int>()

        treeGrid.forEachIndexed { i, trees -> trees.forEachIndexed { j, tree -> heights[Point(i, j)] = tree } }

        treeGrid.forEachIndexed { i, trees ->
            trees.forEachIndexed { j, tree ->
                if (i == 0 || j == 0 || i == lastRowIndex || j == lastColumnIndex) scenicScores[Point(i, j)] = 0
                else {
                    val scoreLeft = kotlin.run {
                        var count = 0
                        for (k in j - 1 downTo 0) {
                            if (checkNotNull(heights[Point(i, k)]) < tree) count++
                            else if (checkNotNull(heights[Point(i, k)]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreRight = kotlin.run {
                        var count = 0
                        for (k in j + 1..lastColumnIndex) {
                            if (checkNotNull(heights[Point(i, k)]) < tree) count++
                            else if (checkNotNull(heights[Point(i, k)]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreUp = kotlin.run {
                        var count = 0
                        for (k in i - 1 downTo 0) {
                            if (checkNotNull(heights[Point(k, j)]) < tree) count++
                            else if (checkNotNull(heights[Point(k, j)]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreDown = kotlin.run {
                        var count = 0
                        for (k in i + 1..lastRowIndex) {
                            if (checkNotNull(heights[Point(k, j)]) < tree) count++
                            else if (checkNotNull(heights[Point(k, j)]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    scenicScores[Point(i, j)] = scoreLeft * scoreRight * scoreUp * scoreDown
                }
            }
        }

        return scenicScores.maxOf { it.value }
    }
}
