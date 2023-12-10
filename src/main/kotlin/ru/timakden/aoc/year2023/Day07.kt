package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2023.Day07.HandType.*

object Day07 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day07")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        return input.map { s ->
            val (hand, bid) = s.split(" ")
            HandP1(hand) to bid.toInt()
        }.sortedWith { h1, h2 ->
            h1.first.compareTo(h2.first)
        }.mapIndexed { index, (_, bid) ->
            bid * (index + 1)
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { s ->
            val (hand, bid) = s.split(" ")
            HandP2(hand) to bid.toInt()
        }.sortedWith { h1, h2 ->
            h1.first.compareTo(h2.first)
        }.mapIndexed { index, (_, bid) ->
            bid * (index + 1)
        }.sum()
    }

    private data class HandP1(val hand: String) : Comparable<HandP1> {
        private val type: HandType
            get() {
                val labels = hand.groupingBy { it }.eachCount()
                return when (labels.size) {
                    1 -> FIVE_OF_A_KIND
                    2 -> if (labels.values.any { it == 4 }) FOUR_OF_A_KIND else FULL_HOUSE
                    3 -> if (labels.values.any { it == 3 }) THREE_OF_A_KIND else TWO_PAIR
                    4 -> ONE_PAIR
                    else -> HIGH_CARD
                }
            }

        override fun compareTo(other: HandP1): Int {
            if (type.ordinal == other.type.ordinal) {
                for (i in 0..hand.lastIndex) {
                    val thisLabel = hand[i]
                    val otherLabel = other.hand[i]
                    val compareResult = labelComparator.compare(thisLabel, otherLabel)
                    if (compareResult != 0) return compareResult
                }
            } else return type.ordinal.compareTo(other.type.ordinal)

            return 0
        }

        companion object {
            private val labelComparator = Comparator { c1: Char, c2: Char ->
                val labelToStrength = mapOf(
                    '2' to 0,
                    '3' to 1,
                    '4' to 2,
                    '5' to 3,
                    '6' to 4,
                    '7' to 5,
                    '8' to 6,
                    '9' to 7,
                    'T' to 8,
                    'J' to 9,
                    'Q' to 10,
                    'K' to 11,
                    'A' to 12,
                )

                checkNotNull(labelToStrength[c1]) - checkNotNull(labelToStrength[c2])
            }
        }
    }

    private data class HandP2(val hand: String) : Comparable<HandP2> {
        private val type: HandType
            get() {
                val labels = hand.groupingBy { it }.eachCount()
                val jokers = labels.filterKeys { it == 'J' }.values.singleOrNull() ?: 0
                val others = labels.filterKeys { it != 'J' }
                    .toSortedMap(labelComparator.reversed())
                    .toMutableMap()

                others.maxByOrNull { it.value }?.key?.let {
                    others[it] = jokers + checkNotNull(others[it])
                }

                if (jokers == 5) return FIVE_OF_A_KIND

                return when (others.size) {
                    1 -> FIVE_OF_A_KIND
                    2 -> if (others.values.any { it == 4 }) FOUR_OF_A_KIND else FULL_HOUSE
                    3 -> if (others.values.any { it == 3 }) THREE_OF_A_KIND else TWO_PAIR
                    4 -> ONE_PAIR
                    else -> HIGH_CARD
                }
            }

        override fun compareTo(other: HandP2): Int {
            if (type.ordinal == other.type.ordinal) {
                for (i in 0..hand.lastIndex) {
                    val thisLabel = hand[i]
                    val otherLabel = other.hand[i]
                    val compareResult = labelComparator.compare(thisLabel, otherLabel)
                    if (compareResult != 0) return compareResult
                }
            } else return type.ordinal.compareTo(other.type.ordinal)

            return 0
        }

        companion object {
            private val labelComparator = Comparator { c1: Char, c2: Char ->
                val labelToStrength = mapOf(
                    'J' to 0,
                    '2' to 1,
                    '3' to 2,
                    '4' to 3,
                    '5' to 4,
                    '6' to 5,
                    '7' to 6,
                    '8' to 7,
                    '9' to 8,
                    'T' to 9,
                    'Q' to 10,
                    'K' to 11,
                    'A' to 12,
                )

                checkNotNull(labelToStrength[c1]) - checkNotNull(labelToStrength[c2])
            }
        }
    }

    enum class HandType {
        HIGH_CARD, // 23456, [1, 1, 1, 1, 1]
        ONE_PAIR, // A23A4, [2, 1, 1, 1]
        TWO_PAIR, // 23432, [2, 2, 1]
        THREE_OF_A_KIND, // TTT98, [3, 1, 1]
        FULL_HOUSE, // 23332, [3, 2]
        FOUR_OF_A_KIND, // AA8AA, [4, 1]
        FIVE_OF_A_KIND // AAAAA, [5]
    }
}
