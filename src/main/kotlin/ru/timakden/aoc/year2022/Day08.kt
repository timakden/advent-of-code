package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day08 {
    @JvmStatic
    @ExperimentalTime
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
        val heights = mutableMapOf<Pair<Int, Int>, Int>()

        treeGrid.forEachIndexed { i, trees -> trees.forEachIndexed { j, tree -> heights[i to j] = tree } }

        treeGrid.forEachIndexed { i, trees ->
            for (j in trees.indices) {
                val tree = trees[j]
                if (i == 0 || j == 0 || i == lastRowIndex || j == lastColumnIndex) visibleTrees++
                else {
                    if (heights
                            .filterKeys { it.first == i && it.second in (0 until j) }
                            .all { it.value < tree }
                    ) {
                        visibleTrees++
                        continue
                    }
                    if (heights
                            .filterKeys { it.first == i && it.second in (j + 1..lastColumnIndex) }
                            .all { it.value < tree }
                    ) {
                        visibleTrees++
                        continue
                    }
                    if (heights
                            .filterKeys { it.first in (0 until i) && it.second == j }
                            .all { it.value < tree }
                    ) {
                        visibleTrees++
                        continue
                    }
                    if (heights
                            .filterKeys { it.first in (i + 1..lastRowIndex) && it.second == j }
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
        val heights = mutableMapOf<Pair<Int, Int>, Int>()
        val scenicScores = mutableMapOf<Pair<Int, Int>, Int>()

        treeGrid.forEachIndexed { i, trees -> trees.forEachIndexed { j, tree -> heights[i to j] = tree } }

        treeGrid.forEachIndexed { i, trees ->
            trees.forEachIndexed { j, tree ->
                if (i == 0 || j == 0 || i == lastRowIndex || j == lastColumnIndex) scenicScores[i to j] = 0
                else {
                    val scoreLeft = kotlin.run {
                        var count = 0
                        for (k in j - 1 downTo 0) {
                            if (checkNotNull(heights[i to k]) < tree) {
                                count++
                            } else if (checkNotNull(heights[i to k]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreRight = kotlin.run {
                        var count = 0
                        for (k in j + 1..lastColumnIndex) {
                            if (checkNotNull(heights[i to k]) < tree) {
                                count++
                            } else if (checkNotNull(heights[i to k]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreUp = kotlin.run {
                        var count = 0
                        for (k in i - 1 downTo 0) {
                            if (checkNotNull(heights[k to j]) < tree) {
                                count++
                            } else if (checkNotNull(heights[k to j]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    val scoreDown = kotlin.run {
                        var count = 0
                        for (k in i + 1..lastRowIndex) {
                            if (checkNotNull(heights[k to j]) < tree) {
                                count++
                            } else if (checkNotNull(heights[k to j]) >= tree) {
                                count++
                                break
                            } else break
                        }
                        count
                    }

                    scenicScores[i to j] = scoreLeft * scoreRight * scoreUp * scoreDown
                }
            }
        }

        return scenicScores.maxOf { it.value }
    }
}
