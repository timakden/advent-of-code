package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.lcm
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day08 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day08")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val instructions = instructionIterator(input.first())
        var currentNode = "AAA"
        var steps = 0

        val nodes = input.drop(2).associate { s ->
            val (name, leftRight) = s.split(" = ")
            val (left, right) = "\\w+".toRegex().findAll(leftRight).map { it.value }.toList()
            name to (left to right)
        }

        while (currentNode != "ZZZ") {
            val direction = instructions.next()
            val (left, right) = nodes[currentNode]!!
            currentNode = if (direction == 'L') left else right
            steps++
        }
        return steps
    }

    fun part2(input: List<String>): Long {
        val instructions = instructionIterator(input.first())

        val nodes = input.drop(2).associate { s ->
            val (name, leftRight) = s.split(" = ")
            val (left, right) = "\\w+".toRegex().findAll(leftRight).map { it.value }.toList()
            name to (left to right)
        }

        return nodes.keys.filter { it.endsWith("A") }.map { node ->
            var currentNode = node
            var s = 0L

            while (!currentNode.endsWith("Z")) {
                val direction = instructions.next()
                val (left, right) = nodes[currentNode]!!
                currentNode = if (direction == 'L') left else right
                s++
            }

            s
        }.reduce(::lcm)
    }

    /**
     * Returns an iterator that yields characters from the given input string infinitely.
     *
     * @param input the input string
     * @return an iterator that yields characters from the input string
     */
    private fun instructionIterator(input: String) = iterator {
        while (true) {
            yieldAll(input.toList())
        }
    }
}
