package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput


object Day21 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day21")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val nameToNumber = mutableMapOf<String, Long>()
        val nameToOperation = mutableMapOf<String, String>()
        input.forEach { line ->
            val name = line.substringBefore(':')
            if ("\\d+".toRegex().containsMatchIn(line)) nameToNumber[name] = line.substringAfter(": ").toLong()
            else nameToOperation[name] = line.substringAfter(": ")
        }

        while (nameToOperation.isNotEmpty()) {
            val iterator = nameToOperation.iterator()
            while (iterator.hasNext()) {
                val (name, operation) = iterator.next()
                val (left, operand, right) = operation.split(' ')
                if (left in nameToNumber && right in nameToNumber) {
                    val leftNumber = checkNotNull(nameToNumber[left])
                    val rightNumber = checkNotNull(nameToNumber[right])
                    nameToNumber[name] = when (operand) {
                        "+" -> leftNumber + rightNumber
                        "-" -> leftNumber - rightNumber
                        "*" -> leftNumber * rightNumber
                        "/" -> leftNumber / rightNumber
                        else -> error("Unsupported operand")
                    }
                    iterator.remove()
                }
            }
        }

        return checkNotNull(nameToNumber["root"])
    }

    fun part2(input: List<String>): Long {
        val nameToNumber = mutableMapOf<String, Long>()
        val nameToOperation = mutableMapOf<String, String>()
        input.forEach { line ->
            val name = line.substringBefore(':')
            if (name != "humn") {
                if ("\\d+".toRegex().containsMatchIn(line))
                    nameToNumber[name] = line.substringAfter(": ").toLong()
                else {
                    val operation = line.substringAfter(": ")
                    if (name == "root") {
                        val (left, _, right) = operation.split(' ')
                        nameToOperation[name] = "$left = $right"
                    } else nameToOperation[name] = operation
                }
            }
        }

        while (nameToOperation.isNotEmpty()) {
            val iterator = nameToOperation.iterator()
            while (iterator.hasNext()) {
                val (name, operation) = iterator.next()
                val (left, operand, right) = operation.split(' ')
                if (operand == "=") {
                    if (left in nameToNumber) {
                        nameToNumber[right] = checkNotNull(nameToNumber[left])
                        iterator.remove()
                    } else if (right in nameToNumber) {
                        nameToNumber[left] = checkNotNull(nameToNumber[right])
                        iterator.remove()
                    }
                } else {
                    if (left in nameToNumber && right in nameToNumber) {
                        val leftNumber = checkNotNull(nameToNumber[left])
                        val rightNumber = checkNotNull(nameToNumber[right])
                        nameToNumber[name] = when (operand) {
                            "+" -> leftNumber + rightNumber
                            "-" -> leftNumber - rightNumber
                            "*" -> leftNumber * rightNumber
                            "/" -> leftNumber / rightNumber
                            else -> error("Unsupported operand")
                        }
                        iterator.remove()
                    } else if (name in nameToNumber && (left in nameToNumber || right in nameToNumber)) {
                        val number = checkNotNull(nameToNumber[name])
                        if (left in nameToNumber) with(checkNotNull(nameToNumber[left])) {
                            nameToNumber[right] = when (operand) {
                                "+" -> number - this
                                "-" -> this - number
                                "*" -> number / this
                                "/" -> this / number
                                else -> error("Unsupported operand")
                            }
                        } else with(checkNotNull(nameToNumber[right])) {
                            nameToNumber[left] = when (operand) {
                                "+" -> number - this
                                "-" -> number + this
                                "*" -> number / this
                                "/" -> number * this
                                else -> error("Unsupported operand")
                            }
                        }
                        iterator.remove()
                    }
                }
            }
        }

        return checkNotNull(nameToNumber["humn"])
    }
}
