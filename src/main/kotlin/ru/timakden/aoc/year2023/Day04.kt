package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.pow

/**
 * [Day 4: Scratchcards](https://adventofcode.com/2023/day/4).
 */
object Day04 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day04")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { card ->
            val winningNumbersCount = card.getWinningNumbersCount()
            2.0.pow(winningNumbersCount - 1).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val cardToTotalCount = mutableMapOf<Int, Int>()
        val cardToWinningNumbersCount = input.map { card ->
            val cardNumber = card.substringAfter("Card")
                .substringBefore(":")
                .trim()
                .toInt().also {
                    cardToTotalCount[it] = 1
                }

            cardNumber to card.getWinningNumbersCount()
        }

        cardToWinningNumbersCount.forEach { (cardNumber, winningNumbersCount) ->
            repeat(winningNumbersCount) {
                cardToTotalCount[cardNumber + it + 1] =
                    checkNotNull(cardToTotalCount[cardNumber + it + 1]) + checkNotNull(cardToTotalCount[cardNumber])
            }
        }

        return cardToTotalCount.map { it.value }.sum()
    }

    private fun String.getWinningNumbersCount(): Int {
        val digits = "\\d+".toRegex()
        val (cardNumbers, winningNumbers) = this.substringAfter(":")
            .split("|")
            .map { s -> digits.findAll(s).map { it.value.toInt() }.toList() }

        return cardNumbers.count { it in winningNumbers }
    }
}
