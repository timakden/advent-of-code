package ru.timakden.aoc.year2016.day10

import ru.timakden.aoc.util.Constants.Part
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.util.measure
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(input.toMutableList(), listOf(17, 61), PART_ONE)}")
        println("Part Two: ${solve(input.toMutableList(), listOf(17, 61), PART_TWO)}")
    }
}

fun solve(input: MutableList<String>, valuesToCompare: List<Int>, part: Part): Int {
    val bots = mutableMapOf<Int, MutableList<Int>>()
    val outputs = mutableMapOf<Int, Int>()

    while (input.isNotEmpty()) {
        val iterator = input.listIterator()

        while (iterator.hasNext()) {
            val instruction = iterator.next()

            if (instruction.startsWith("value")) {
                val botNumber = instruction.substringAfter("bot ").toInt()
                val chipValue = instruction.substringAfter("value ").substringBefore(" goes").toInt()

                if (bots.contains(botNumber)) {
                    bots[botNumber]?.add(chipValue)
                } else {
                    bots[botNumber] = mutableListOf(chipValue)
                }

                iterator.remove()
            } else {
                val botNumber = instruction.substringAfter("bot ").substringBefore(" gives").toInt()

                if (bots.contains(botNumber) && bots[botNumber]?.size == 2) {
                    val lowTo = instruction.substringAfter("low to ").substringBefore(" and high")
                    val lowToNumber = lowTo.substringAfter(" ").toInt()
                    val highTo = instruction.substringAfter("high to ")
                    val highToNumber = highTo.substringAfter(" ").toInt()
                    val lowValue = bots[botNumber]?.minOrNull()!!
                    val highValue = bots[botNumber]?.maxOrNull()!!

                    if (lowTo.contains("bot")) {
                        if (bots.contains(lowToNumber)) {
                            bots[lowToNumber]?.add(lowValue)
                        } else {
                            bots[lowToNumber] = mutableListOf(lowValue)
                        }
                    } else {
                        outputs[lowToNumber] = lowValue
                    }

                    if (highTo.contains("bot")) {
                        if (bots.contains(highToNumber)) {
                            bots[highToNumber]?.add(highValue)
                        } else {
                            bots[highToNumber] = mutableListOf(highValue)
                        }
                    } else {
                        outputs[highToNumber] = highValue
                    }

                    bots.remove(botNumber)
                    iterator.remove()
                }
            }

            if (part == PART_ONE) {
                for ((key, value) in bots) {
                    if (value.size == 2 && value.containsAll(valuesToCompare)) {
                        return key
                    }
                }
            }

            if (part == PART_TWO && outputs.contains(0) && outputs.contains(1) && outputs.contains(2)) {
                return outputs[0]!! * outputs[1]!! * outputs[2]!!
            }
        }
    }

    return -1
}
