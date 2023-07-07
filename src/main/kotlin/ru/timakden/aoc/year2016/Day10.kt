package ru.timakden.aoc.year2016

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput

object Day10 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2016/Day10")
            println("Part One: ${part1(input.toMutableList(), listOf(17, 61))}")
            println("Part Two: ${part2(input.toMutableList())}")
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

                    if (botNumber in bots) {
                        bots[botNumber]?.add(chipValue)
                    } else {
                        bots[botNumber] = mutableListOf(chipValue)
                    }

                    iterator.remove()
                } else {
                    val botNumber = instruction.substringAfter("bot ").substringBefore(" gives").toInt()

                    if (botNumber in bots && bots[botNumber]?.size == 2) {
                        val lowTo = instruction.substringAfter("low to ").substringBefore(" and high")
                        val lowToNumber = lowTo.substringAfter(" ").toInt()
                        val highTo = instruction.substringAfter("high to ")
                        val highToNumber = highTo.substringAfter(" ").toInt()
                        val lowValue = bots[botNumber]?.minOrNull()!!
                        val highValue = bots[botNumber]?.maxOrNull()!!

                        if ("bot" in lowTo) {
                            if (lowToNumber in bots) {
                                bots[lowToNumber]?.add(lowValue)
                            } else {
                                bots[lowToNumber] = mutableListOf(lowValue)
                            }
                        } else {
                            outputs[lowToNumber] = lowValue
                        }

                        if ("bot" in highTo) {
                            if (highToNumber in bots) {
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

                    if (botNumber in bots) {
                        bots[botNumber]?.add(chipValue)
                    } else {
                        bots[botNumber] = mutableListOf(chipValue)
                    }

                    iterator.remove()
                } else {
                    val botNumber = instruction.substringAfter("bot ").substringBefore(" gives").toInt()

                    if (botNumber in bots && bots[botNumber]?.size == 2) {
                        val lowTo = instruction.substringAfter("low to ").substringBefore(" and high")
                        val lowToNumber = lowTo.substringAfter(" ").toInt()
                        val highTo = instruction.substringAfter("high to ")
                        val highToNumber = highTo.substringAfter(" ").toInt()
                        val lowValue = bots[botNumber]?.minOrNull()!!
                        val highValue = bots[botNumber]?.maxOrNull()!!

                        if ("bot" in lowTo) {
                            if (lowToNumber in bots) bots[lowToNumber]?.add(lowValue)
                            else bots[lowToNumber] = mutableListOf(lowValue)
                        } else outputs[lowToNumber] = lowValue

                        if ("bot" in highTo) {
                            if (highToNumber in bots) bots[highToNumber]?.add(highValue)
                            else bots[highToNumber] = mutableListOf(highValue)
                        } else outputs[highToNumber] = highValue

                        bots.remove(botNumber)
                        iterator.remove()
                    }
                }

                if (0 in outputs && 1 in outputs && 2 in outputs) {
                    return outputs[0]!! * outputs[1]!! * outputs[2]!!
                }
            }
        }

        return -1
    }
}
