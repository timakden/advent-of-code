package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.util.*
import kotlin.properties.Delegates

object Day12 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day12")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val nodes = buildNodes(input)
        val start = checkNotNull(nodes.find { it.elevation == 'S' })
        val finish = checkNotNull(nodes.find { it.elevation == 'E' })
        dijkstra(start)

        return finish.shortestPath.size
    }

    fun part2(input: List<String>): Int {
        val nodes = buildNodes(input)

        fun reset() {
            nodes.forEach {
                it.distance = Int.MAX_VALUE
                it.shortestPath = LinkedList<Node>()
            }
        }

        return nodes.filter { it.elevation == 'a' || it.elevation == 'S' }
            .map { it.point }
            .map { coordinate ->
                reset()
                val start = checkNotNull(nodes.find { it.point == coordinate })
                val finish = checkNotNull(nodes.find { it.elevation == 'E' })
                dijkstra(start)

                finish.shortestPath.size
            }.filter { it != 0 }.min()
    }

    private fun buildNodes(input: List<String>) =
        input.flatMapIndexed { i, s -> s.mapIndexed { j, c -> Node(i to j, c) } }.also { nodes ->
            nodes.forEach { node ->
                val x = node.point.first
                val y = node.point.second
                (listOf(x - 1, x + 1)).forEach { i ->
                    nodes.find { it.point == i to y }?.let {
                        if (canMove(node, it)) node.addDestination(it, 1)
                    }
                }

                (listOf(y - 1, y + 1)).forEach { j ->
                    nodes.find { it.point == x to j }?.let {
                        if (canMove(node, it)) node.addDestination(it, 1)
                    }
                }
            }
        }

    private fun canMove(currentNode: Node, adjacentNode: Node): Boolean {
        val currentElevation = when (currentNode.elevation) {
            'S' -> 'a'
            'E' -> 'z'
            else -> currentNode.elevation
        }
        val adjacentElevation = when (adjacentNode.elevation) {
            'S' -> 'a'
            'E' -> 'z'
            else -> adjacentNode.elevation
        }
        return currentElevation >= adjacentElevation || (adjacentElevation - currentElevation) == 1
    }

    private fun dijkstra(source: Node) {
        val lowestDistanceNode = { unsettledNodes: Set<Node> ->
            var lowestDistanceNode: Node by Delegates.notNull()
            var lowestDistance = Int.MAX_VALUE
            unsettledNodes.forEach { node ->
                val nodeDistance = node.distance
                if (nodeDistance < lowestDistance) {
                    lowestDistance = nodeDistance
                    lowestDistanceNode = node
                }
            }
            lowestDistanceNode
        }

        val calculateMinimumDistance = { evaluationNode: Node, edgeWeight: Int, sourceNode: Node ->
            val sourceDistance = sourceNode.distance
            if (sourceDistance + edgeWeight < evaluationNode.distance) {
                evaluationNode.distance = sourceDistance + edgeWeight
                val shortestPath = LinkedList(sourceNode.shortestPath)
                shortestPath.add(sourceNode)
                evaluationNode.shortestPath = shortestPath
            }
        }

        source.distance = 0
        val settledNodes = mutableSetOf<Node>()
        val unsettledNodes = mutableSetOf<Node>()

        unsettledNodes.add(source)

        while (unsettledNodes.size != 0) {
            val currentNode = lowestDistanceNode(unsettledNodes)
            unsettledNodes.remove(currentNode)
            currentNode.adjacentNodes.forEach { (adjacentNode, edgeWeight) ->
                if (adjacentNode !in settledNodes) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode)
                    unsettledNodes.add(adjacentNode)
                }
            }
            settledNodes.add(currentNode)
        }
    }

    private data class Node(val point: Pair<Int, Int>, val elevation: Char) {
        var shortestPath = LinkedList<Node>()
        var distance = Int.MAX_VALUE
        val adjacentNodes = mutableMapOf<Node, Int>()

        fun addDestination(destination: Node, distance: Int) {
            adjacentNodes[destination] = distance
        }
    }
}
