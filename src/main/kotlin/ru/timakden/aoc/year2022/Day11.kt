package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day11.Monkey.Companion.toMonkey
import kotlin.time.ExperimentalTime

object Day11 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day11")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val rounds = 20

        val monkeys =
            input.fold<String, MutableList<MutableList<String>>>(mutableListOf(mutableListOf())) { acc, item ->
                if (item.isBlank()) acc.add(mutableListOf()) else acc.last().add(item)
                acc
            }.map { it.toMonkey() }

        repeat(rounds) {
            monkeys.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    monkey.itemsInspectedCount++
                    val item = monkey.items.removeFirst()
                    val split = monkey.operation.substringAfter("= ").split(' ')

                    val number1 = if (split[0] == "old") item else split[0].toLong()
                    val number2 = if (split[2] == "old") item else split[2].toLong()

                    val newItem = when (split[1]) {
                        "+" -> number1 + number2
                        "-" -> number1 - number2
                        "/" -> number1 / number2
                        "*" -> number1 * number2
                        else -> throw IllegalArgumentException()
                    }.div(3)

                    val newMonkey =
                        if (newItem % monkey.testNumber == 0L)
                            monkeys.find { it.name.substringAfter(' ').toInt() == monkey.trueMonkeyIndex }
                        else
                            monkeys.find { it.name.substringAfter(' ').toInt() == monkey.falseMonkeyIndex }

                    newMonkey?.items?.add(newItem)
                }
            }
        }

        return monkeys.sortedByDescending { it.itemsInspectedCount }
            .take(2)
            .map { it.itemsInspectedCount }
            .reduce { a, b -> a * b }
    }

    fun part2(input: List<String>): Long {
        val rounds = 10000

        val monkeys =
            input.fold<String, MutableList<MutableList<String>>>(mutableListOf(mutableListOf())) { acc, item ->
                if (item.isBlank()) acc.add(mutableListOf()) else acc.last().add(item)
                acc
            }.map { it.toMonkey() }
        val lcm = monkeys.map { it.testNumber }.reduce(::lcm)

        repeat(rounds) {
            monkeys.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    monkey.itemsInspectedCount++
                    val item = monkey.items.removeFirst()
                    val split = monkey.operation.substringAfter("= ").split(' ')

                    val number1 = if (split[0] == "old") item else split[0].toLong()
                    val number2 = if (split[2] == "old") item else split[2].toLong()

                    val newItem = when (split[1]) {
                        "+" -> number1 + number2
                        "-" -> number1 - number2
                        "/" -> number1 / number2
                        "*" -> number1 * number2
                        else -> throw IllegalArgumentException()
                    }

                    val newMonkey =
                        if (newItem % monkey.testNumber == 0L)
                            monkeys.find { m -> m.name.substringAfter(' ').toInt() == monkey.trueMonkeyIndex }
                        else
                            monkeys.find { m -> m.name.substringAfter(' ').toInt() == monkey.falseMonkeyIndex }


                    newMonkey?.items?.add(newItem % lcm)
                }
            }
        }

        return monkeys.sortedByDescending { it.itemsInspectedCount }
            .take(2)
            .map { it.itemsInspectedCount }
            .reduce { a, b -> a * b }
    }

    private class Monkey(
        val name: String,
        val items: MutableList<Long>,
        val operation: String,
        val testNumber: Long,
        val trueMonkeyIndex: Int,
        val falseMonkeyIndex: Int,
        var itemsInspectedCount: Long = 0
    ) {
        companion object {
            fun List<String>.toMonkey(): Monkey {
                val name = this.first().substringBefore(':')
                val items = "\\d+".toRegex().findAll(this[1]).map { it.value }.map { it.toLong() }.toMutableList()
                val operation = this[2].substringAfter(": ")
                val testNumber = checkNotNull("\\d+".toRegex().find(this[3])?.value?.toLong())
                val trueMonkeyIndex = checkNotNull("\\d+".toRegex().find(this[4])?.value?.toInt())
                val falseMonkeyIndex = checkNotNull("\\d+".toRegex().find(this[5])?.value?.toInt())
                return Monkey(name, items, operation, testNumber, trueMonkeyIndex, falseMonkeyIndex)
            }
        }
    }

    private tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

    private fun lcm(a: Long, b: Long) = a / gcd(a, b) * b
}
