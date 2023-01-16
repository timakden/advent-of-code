package ru.timakden.aoc.year2022

import arrow.core.flattenOption
import arrow.core.getOrElse
import arrow.core.toOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day19 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day19")
            runBlocking {
                println("Part One: ${part1(input)}")
                println("Part Two: ${part2(input)}")
            }
        }
    }

    private fun blueprints(input: List<String>) = input.map { line ->
        ID_PATTERN.matchAt(line, 0).toOption()
            .map { it.destructured }
            .map { it.component1() }
            .map {
                it.toInt() to PART_PATTERN.findAll(line).associate { partMatch ->
                    val (robot, costs) = partMatch.destructured
                    robot to COST_PATTERN.findAll(costs).associate { costMatch ->
                        val (cost, type) = costMatch.destructured
                        type to cost.toInt()
                    }
                }
            }
    }.flattenOption()


    suspend fun part1(input: List<String>): Int = withContext(Dispatchers.Default) {
        blueprints(input).map { (id, blueprint) ->
            async {
                id * geodes(blueprint, 24)
            }
        }.sumOf { it.await() }
    }

    suspend fun part2(input: List<String>): Int = withContext(Dispatchers.Default) {
        blueprints(input).take(3).map { (id, blueprint) ->
            async {
                geodes(blueprint, 32)
            }
        }.fold(1) { acc, deferred -> acc * deferred.await() }
    }

    private data class State(
        val robots: Map<String, Int>,
        val resources: Map<String, Int>,
        val time: Int,
    ) {
        val estimate: Int = resources.getOrDefault("geode", 0) + robots.getOrDefault("geode", 0) * time
    }

    private val ID_PATTERN = """Blueprint (\d+):""".toRegex()
    private val PART_PATTERN = """Each (\w+) robot ([^.]+)[.]""".toRegex()
    private val COST_PATTERN = """(?:costs | and )(\d+) (\w+)""".toRegex()

    @Suppress("CyclomaticComplexMethod")
    private fun geodes(blueprint: Map<String, Map<String, Int>>, time: Int): Int {
        val maxValues = buildMap {
            for (costs in blueprint.values) {
                for ((material, cost) in costs) this[material] = maxOf(this.getOrDefault(material, 0), cost)
            }
        }
        var best = 0
        val queue = mutableListOf(State(mapOf("ore" to 1), emptyMap(), time))
        while (queue.isNotEmpty()) {
            val state = queue.removeLast()
            if (potential(blueprint, state) < best) continue
            if (state.estimate > best) best = state.estimate
            for ((robot, costs) in blueprint) {
                val maxValue = maxValues[robot]
                if (maxValue != null && state.robots.getOrDefault(robot, 0) >= maxValue) continue
                val delta = blueprint.keys.maxOf { type ->
                    val demand = costs.getOrDefault(type, 0) - state.resources.getOrDefault(type, 0)
                    if (demand <= 0) {
                        0
                    } else {
                        val supply = state.robots.getOrDefault(type, 0)
                        if (supply <= 0) Int.MAX_VALUE else (demand + supply - 1) / supply
                    }
                }
                if (delta < state.time) {
                    val robots = state.robots + (robot to state.robots.getOrDefault(robot, 0) + 1)
                    val resources = buildMap<String, Int> {
                        putAll(state.resources)
                        for ((type, cost) in costs) this[type] = getOrDefault(type, 0) - cost
                        for ((type, count) in state.robots) this[type] =
                            getOrDefault(type, 0) + count * (delta + 1)
                    }
                    queue += State(robots, resources, state.time - delta - 1)
                }
            }
        }
        return best
    }

    private fun potential(blueprint: Map<String, Map<String, Int>>, state: State): Int {
        val potentialRobots = blueprint.keys.associateWithTo(mutableMapOf()) { 0 }
        val potentialResources = state.resources.toMutableMap()
        repeat(state.time) {
            for ((robot, count) in potentialRobots) {
                potentialResources[robot] =
                    potentialResources.getOrDefault(robot, 0) + state.robots.getOrDefault(robot, 0) + count
            }
            for (entry in potentialRobots) {
                val (robot, count) = entry
                if (
                    blueprint[robot].toOption().map {
                        it.all { (type, cost) ->
                            potentialResources.getOrDefault(type, 0) >= cost * (count + 1)
                        }
                    }.getOrElse { false }
                ) {
                    entry.setValue(count + 1)
                }
            }
        }
        return potentialResources.getOrDefault("geode", 0)
    }
}
