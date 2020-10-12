package ru.timakden.adventofcode.year2016.day12

import ru.timakden.adventofcode.Constants.Part
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.isNumber
import ru.timakden.adventofcode.measure
import ru.timakden.adventofcode.year2016.day12.Command.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input, PART_ONE)}")
        println("Part Two: ${solve(input, PART_TWO)}")
    }
}

fun solve(input: List<String>, part: Part): Int {
    val registers = mutableMapOf("a" to 0, "b" to 0, "c" to (if (part == PART_ONE) 0 else 1), "d" to 0)
    var currentInstruction = 0

    while (currentInstruction < input.size) {
        val instruction = input[currentInstruction].split(" ")

        when (Command.from(instruction.first())) {
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
        fun from(command: String): Command {
            return when (command) {
                "cpy" -> COPY
                "jnz" -> JUMP
                "inc" -> INCREMENT
                "dec" -> DECREMENT
                else -> throw IllegalArgumentException("Unknown command \"$command\"")
            }
        }
    }
}
