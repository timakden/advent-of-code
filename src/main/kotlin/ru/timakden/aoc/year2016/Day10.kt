package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.time.ExperimentalTime

object Day10 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day10")
            println("Part One: ${part1(input.toMutableList(), listOf(17, 61))}")
            println("Part One: ${part2(input.toMutableList())}")
        }
    }

    fun part1(input: MutableList<String>, valuesToCompare: List<Int>): Int {
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

                for ((key, value) in bots) {
                    if (value.size == 2 && value.containsAll(valuesToCompare)) {
                        return key
                    }
                }
            }
        }

        return -1
    }

    fun part2(input: MutableList<String>): Int {
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

                if (outputs.contains(0) && outputs.contains(1) && outputs.contains(2)) {
                    return outputs[0]!! * outputs[1]!! * outputs[2]!!
                }
            }
        }

        return -1
    }
}
