package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.isNumber
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2016.Day12.Command.*
import ru.timakden.aoc.year2016.Day12.Command.Companion.toCommand
import kotlin.time.ExperimentalTime

object Day12 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day12")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val registers =
            mutableMapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0)
        var currentInstruction = 0

        while (currentInstruction < input.size) {
            val instruction = input[currentInstruction].split(" ")

            when (instruction.first().toCommand()) {
                COPY -> {
                    val value = with(instruction[1]) {
                        if (this.isNumber()) this.toInt()
                        else registers[this] ?: 0
                    }
                    registers[instruction[2]] = value
                    currentInstruction++
                }

                JUMP -> {
                    with(instruction[1]) {
                        if ((this.isNumber() && this.toInt() != 0) || registers[this] != 0)
                            currentInstruction += instruction[2].toInt()
                        else
                            currentInstruction++
                    }
                }

                INCREMENT -> {
                    with(instruction[1]) {
                        registers[this] = registers[this]?.plus(1) ?: 0
                    }

                    currentInstruction++
                }

                DECREMENT -> {
                    with(instruction[1]) {
                        registers[this] = registers[this]?.minus(1) ?: 0
                    }
                    currentInstruction++
                }
            }
        }

        return registers["a"] ?: 0
    }

    fun part2(input: List<String>): Int {
        val registers =
            mutableMapOf("a" to 0, "b" to 0, "c" to 1, "d" to 0)
        var currentInstruction = 0

        while (currentInstruction < input.size) {
            val instruction = input[currentInstruction].split(" ")

            when (instruction.first().toCommand()) {
                COPY -> {
                    val value = with(instruction[1]) {
                        if (this.isNumber()) this.toInt()
                        else registers[this] ?: 0
                    }
                    registers[instruction[2]] = value
                    currentInstruction++
                }

                JUMP -> {
                    with(instruction[1]) {
                        if ((this.isNumber() && this.toInt() != 0) || registers[this] != 0)
                            currentInstruction += instruction[2].toInt()
                        else
                            currentInstruction++
                    }
                }

                INCREMENT -> {
                    with(instruction[1]) {
                        registers[this] = registers[this]?.plus(1) ?: 0
                    }

                    currentInstruction++
                }

                DECREMENT -> {
                    with(instruction[1]) {
                        registers[this] = registers[this]?.minus(1) ?: 0
                    }
                    currentInstruction++
                }
            }
        }

        return registers["a"] ?: 0
    }

    private enum class Command {
        COPY, JUMP, INCREMENT, DECREMENT;

        companion object {
            fun String.toCommand(): Command {
                return when (this) {
                    "cpy" -> COPY
                    "jnz" -> JUMP
                    "inc" -> INCREMENT
                    "dec" -> DECREMENT
                    else -> throw IllegalArgumentException("Unknown command \"$this\"")
                }
            }
        }
    }
}
