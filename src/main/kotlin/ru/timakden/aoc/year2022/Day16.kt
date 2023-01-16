package ru.timakden.aoc.year2022

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.ExperimentalTime

object Day16 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day16")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val spaces = input.map { Space.from(it) }

        val distanceMap = spaces.map { it.name to it.getOtherDistances(spaces) }
        val startingSpace = checkNotNull(spaces.find { it.name == "AA" })
        val valveOptions = spaces.filter { it.flowRate > 0 }

        val paths = getPathPermutations(startingSpace, valveOptions, distanceMap, 30)

        return paths.maxOf { it.second }
    }

    fun part2(input: List<String>): Int {
        val spaces = input.map { Space.from(it) }

        val distanceMap = spaces.map { it.name to it.getOtherDistances(spaces) }
        val startingSpace = checkNotNull(spaces.find { it.name == "AA" })
        val valveOptions = spaces.filter { it.flowRate > 0 }

        val myPaths = getPathPermutations(startingSpace, valveOptions, distanceMap, 26)

        val bestScore = AtomicInteger()

        runBlocking {
            withContext(Dispatchers.Default) {
                myPaths.forEach { path ->
                    launch {
                        getPathPermutations(startingSpace, valveOptions, distanceMap, 26, path.first).forEach {
                            if (path.second + it.second > bestScore.get()) bestScore.set(path.second + it.second)
                        }
                    }
                }
            }
        }

        return bestScore.get()
    }

    private fun getPathPermutations(
        startingSpace: Space,
        spaces: List<Space>,
        distanceMap: List<Pair<String, List<Pair<String, Int>>>>,
        time: Int,
        visitedSpaces: List<String> = listOf()
    ): List<Pair<List<String>, Int>> {
        val permutations = mutableListOf<Pair<List<String>, Int>>()

        fun getAllPaths(
            pathHead: Space,
            currentPath: Pair<List<String>, Int>,
            minutesRemaining: Int
        ): Set<Pair<List<String>, Int>> {
            val remainingSpaces = spaces.filter {
                !visitedSpaces.contains(it.name) && !currentPath.first.contains(it.name) && minutesRemaining >= (distanceMap.distanceBetween(
                    pathHead.name,
                    it.name
                ) + 1)
            }

            return if (remainingSpaces.isNotEmpty()) {
                remainingSpaces.flatMap {
                    getAllPaths(
                        it,
                        Pair(
                            currentPath.first.plus(it.name),
                            currentPath.second + ((minutesRemaining - (distanceMap.distanceBetween(
                                pathHead.name,
                                it.name
                            ) + 1)) * it.flowRate)
                        ),
                        minutesRemaining - (distanceMap.distanceBetween(pathHead.name, it.name) + 1)
                    ).plus(setOf(currentPath))
                }.toSet()
            } else setOf(currentPath)
        }

        val allPaths = getAllPaths(startingSpace, emptyList<String>() to 0, time)
        permutations.addAll(allPaths)

        return permutations
    }

    private fun List<Pair<String, List<Pair<String, Int>>>>.distanceBetween(source: String, destination: String) =
        checkNotNull(find { key -> key.first == source }?.second?.find { it.first == destination }?.second)

    private data class Space(
        val name: String,
        val flowRate: Int,
        val connections: List<String>
    ) {
        fun getOtherDistances(spaces: List<Space>): List<Pair<String, Int>> {
            val currentSpace = name to 0
            val otherDistances = mutableListOf(currentSpace)

            fun getNestedDistances(key: String, distance: Int): List<Pair<String, Int>> {
                val space = checkNotNull(spaces.find { it.name == key })

                space.connections.forEach { connection ->
                    val x = otherDistances.find { connection == it.first }
                    x?.let {
                        if (distance < it.second) otherDistances.remove(it)
                    }
                }

                val unmappedDistances = space.connections.filter { connection ->
                    otherDistances.none { connection == it.first }
                }

                return if (unmappedDistances.isNotEmpty()) {
                    otherDistances.addAll(unmappedDistances.map { it to distance })
                    unmappedDistances.flatMap { getNestedDistances(it, distance + 1) }
                } else emptyList()
            }

            otherDistances.addAll(getNestedDistances(this.name, 1))

            return otherDistances.minus(currentSpace)
        }

        companion object {
            fun from(line: String): Space {
                val (part1, part2) = line.split(';')
                val name = part1.split(' ')[1]
                val rate = part1.split('=')[1].toInt()
                val connections = part2.split("valves", "valve")[1].split(',').map { it.trim() }
                return Space(name, rate, connections)
            }
        }
    }
}
