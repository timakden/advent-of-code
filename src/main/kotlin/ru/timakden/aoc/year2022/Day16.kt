package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
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
        val valves = input.toValves()
        val maxOpenValves = valves.count { it.flowRate > 0 }
        val start = checkNotNull(valves.find { it.name == "AA" })
        val startPath = Path1(listOf(start), mutableMapOf())
        var allPaths = listOf(startPath)
        var bestPath = startPath

        for (time in 1..30) {
            allPaths = buildList {
                for (it in allPaths) {
                    if (it.open.size == maxOpenValves) listOf<Path1>()

                    val currentLast = it.last()
                    val currentValves = it.valves

                    if (currentLast.flowRate > 0 && !it.open.containsKey(currentLast)) {
                        val open = it.open.toMutableMap()
                        open[currentLast] = time
                        val possibleValves = currentValves + currentLast
                        this.add(Path1(possibleValves, open))

                    }

                    currentLast.connectedValves.mapTo(this) { valve ->
                        Path1(currentValves + checkNotNull(valves.find { it.name == valve }), it.open)
                    }
                }
            }.sortedByDescending { it.total() }.take(10_000)

            if (allPaths.first().total() > bestPath.total()) bestPath = allPaths.first()
        }

        return bestPath.total()
    }

    fun part2(input: List<String>): Int {
        val valves = input.toValves()
        val maxOpenValves = valves.count { it.flowRate > 0 }
        val start = checkNotNull(valves.find { it.name == "AA" })
        val startPath = Path2(listOf(start), listOf(start), mutableMapOf())
        var allPaths = listOf(startPath)
        var bestPath = startPath

        for (time in 1..26) {
            allPaths = buildList {
                for (currentPath in allPaths) {
                    if (currentPath.open.size == maxOpenValves) continue

                    val currentLastMe = currentPath.lastMe()
                    val currentLastElephant = currentPath.lastElephant()
                    val currentValvesMe = currentPath.valvesMe
                    val currentValvesElephant = currentPath.valvesElephant

                    val openMe = currentLastMe.flowRate > 0 && !currentPath.open.containsKey(currentLastMe)
                    val openElephant =
                        currentLastElephant.flowRate > 0 && !currentPath.open.containsKey(currentLastElephant)

                    if (openMe || openElephant) {
                        val open = currentPath.open.toMutableMap()

                        val possibleValvesMe =
                            findPossibleValves(openMe, currentLastMe, currentValvesMe, open, time, valves)

                        val possibleValvesElephants =
                            findPossibleValves(
                                openElephant,
                                currentLastElephant,
                                currentValvesElephant,
                                open,
                                time,
                                valves
                            )

                        possibleValvesMe.flatMapTo(this) { a -> possibleValvesElephants.map { b -> Path2(a, b, open) } }
                    }
                    currentLastMe.connectedValves
                        .flatMap { a -> currentLastElephant.connectedValves.map { b -> a to b } }
                        .filter { (a, b) -> a != b }
                        .mapTo(this) { (leadMe, leadElephant) ->
                            Path2(
                                currentValvesMe + checkNotNull(valves.find { it.name == leadMe }),
                                currentValvesElephant + checkNotNull(valves.find { it.name == leadElephant }),
                                currentPath.open
                            )
                        }
                }
            }.sortedByDescending { it.total() }.take(100_000)

            if (allPaths.first().total() > bestPath.total()) bestPath = allPaths.first()
        }

        return bestPath.total()
    }

    private fun findPossibleValves(
        open: Boolean,
        currentLast: Valve,
        currentValves: List<Valve>,
        opened: MutableMap<Valve, Int>,
        time: Int,
        allValves: Set<Valve>
    ) = if (open) {
        opened[currentLast] = time
        listOf(currentValves + currentLast)
    } else currentLast.connectedValves.map { valve -> currentValves + checkNotNull(allValves.find { it.name == valve }) }

    private fun List<String>.toValves() = this.map { line ->
        val valveNames = "[A-Z]{2}".toRegex().findAll(line).map { it.value }.toList()
        val name = valveNames.first()
        val flowRate = checkNotNull("\\d+".toRegex().find(line)?.value?.toInt())
        val connectedValveNames = valveNames.drop(1)
        Valve(name, flowRate, connectedValveNames)
    }.toSet()

    data class Valve(val name: String, val flowRate: Int, val connectedValves: List<String>)

    data class Path1(val valves: List<Valve>, val open: Map<Valve, Int>) {
        fun last() = valves.last()
        fun total() = open.map { (valve, time) -> (30 - time) * valve.flowRate }.sum()
    }

    data class Path2(val valvesMe: List<Valve>, val valvesElephant: List<Valve>, val open: Map<Valve, Int>) {
        fun lastMe() = valvesMe.last()
        fun lastElephant() = valvesElephant.last()
        fun total() = open.map { (valve, time) -> (26 - time) * valve.flowRate }.sum()
    }
}
