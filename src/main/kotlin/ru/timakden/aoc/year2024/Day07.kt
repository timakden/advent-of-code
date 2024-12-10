package ru.timakden.aoc.year2024

import arrow.core.tail
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.math.BigInteger
import java.math.BigInteger.ZERO

/**
 * [Day 7: Bridge Repair](https://adventofcode.com/2024/day/7).
 */
object Day07 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2024/Day07")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): BigInteger {
        return input.sumOf { equation ->
            val testValue = equation.substringBefore(":").toBigInteger()
            val numbers = equation.substringAfter(": ").split(" ").map { it.toBigInteger() }
            val operatorPermutations = fillOperatorsPartOne(numbers.size - 1)
            val isPossible = operatorPermutations.any { operators ->
                val result = numbers.tail().foldIndexed(numbers.first()) { index, acc, number ->
                    when (operators[index]) {
                        "+" -> acc + number
                        else -> acc * number
                    }
                }

                result == testValue
            }

            if (isPossible) testValue else ZERO
        }
    }

    fun part2(input: List<String>): BigInteger {
        return input.sumOf { equation ->
            val testValue = equation.substringBefore(":").toBigInteger()
            val numbers = equation.substringAfter(": ").split(" ").map { it.toBigInteger() }
            val operatorPermutations = fillOperatorsPartTwo(numbers.size - 1)
            val isPossible = operatorPermutations.any { operators ->
                val result = numbers.tail().foldIndexed(numbers.first()) { index, acc, number ->
                    when (operators[index]) {
                        "+" -> acc + number
                        "*" -> acc * number
                        else -> "$acc$number".toBigInteger()
                    }
                }

                result == testValue
            }

            if (isPossible) testValue else ZERO
        }
    }

    private fun fillOperatorsPartOne(numberToFill: Int): List<List<String>> {
        val operators = mutableListOf(
            mutableListOf("+"),
            mutableListOf("*")
        )

        repeat(numberToFill - 1) {
            val copy = operators.map { it.toMutableList() }.toMutableList()
            operators.forEach { it += "+" }
            copy.forEach { it += "*" }
            operators += copy
        }

        return operators
    }

    private fun fillOperatorsPartTwo(numberToFill: Int): List<List<String>> {
        val operators = mutableListOf(
            mutableListOf("+"),
            mutableListOf("*"),
            mutableListOf("||"),
        )

        repeat(numberToFill - 1) {
            val copy = operators.map { it.toMutableList() }.toMutableList()
            val copy2 = operators.map { it.toMutableList() }.toMutableList()
            operators.forEach { it += "+" }
            copy.forEach { it += "*" }
            copy2.forEach { it += "||" }
            operators += copy
            operators += copy2
        }

        return operators
    }
}
