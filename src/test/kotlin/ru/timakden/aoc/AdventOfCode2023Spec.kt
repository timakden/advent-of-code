package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2023.*

class AdventOfCode2023Spec : FunSpec({
    context("Year 2023") {
        context("Day 1: Trebuchet?!") {
            test("Part One") {
                val input = listOf(
                    "1abc2",
                    "pqr3stu8vwx",
                    "a1b2c3d4e5f",
                    "treb7uchet"
                )
                val expected = 142
                Day01.part1(input) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(
                    "two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
                    "7pqrstsixteen"
                )
                val expected = 281
                Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Cube Conundrum") {
            val input = listOf(
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
            )
            test("Part One") {
                val expected = 8
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 2286
                Day02.part2(input) shouldBe expected
            }
        }

        context("Day 3: Gear Ratios") {
            val input = listOf(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."
            )
            test("Part One") {
                val expected = 4361
                Day03.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 467835
                Day03.part2(input) shouldBe expected
            }
        }

        context("Day 4: Scratchcards") {
            val input = listOf(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
            )
            test("Part One") {
                val expected = 13
                Day04.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 30
                Day04.part2(input) shouldBe expected
            }
        }

        context("Day 5: If You Give A Seed A Fertilizer") {
            val input = listOf(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                "",
                "soil-to-fertilizer map:",
                "0 15 37",
                "37 52 2",
                "39 0 15",
                "",
                "fertilizer-to-water map:",
                "49 53 8",
                "0 11 42",
                "42 0 7",
                "57 7 4",
                "",
                "water-to-light map:",
                "88 18 7",
                "18 25 70",
                "",
                "light-to-temperature map:",
                "45 77 23",
                "81 45 19",
                "68 64 13",
                "",
                "temperature-to-humidity map:",
                "0 69 1",
                "1 0 69",
                "",
                "humidity-to-location map:",
                "60 56 37",
                "56 93 4"
            )
            test("Part One") {
                val expected = 35
                Day05.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 46
                Day05.part2(input) shouldBe expected
            }
        }

        context("Day 6: Wait For It") {
            val input = listOf(
                "Time:      7  15   30",
                "Distance:  9  40  200"
            )
            test("Part One") {
                val expected = 288
                Day06.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 71503
                Day06.part2(input) shouldBe expected
            }
        }

        context("Day 7: Camel Cards") {
            val input = listOf(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483"
            )
            test("Part One") {
                val expected = 6440
                Day07.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 5905
                Day07.part2(input) shouldBe expected
            }
        }

        context("Day 8: Haunted Wasteland") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf(
                            "RL",
                            "",
                            "AAA = (BBB, CCC)",
                            "BBB = (DDD, EEE)",
                            "CCC = (ZZZ, GGG)",
                            "DDD = (DDD, DDD)",
                            "EEE = (EEE, EEE)",
                            "GGG = (GGG, GGG)",
                            "ZZZ = (ZZZ, ZZZ)"
                        ) to 2,
                        listOf(
                            "LLR",
                            "",
                            "AAA = (BBB, BBB)",
                            "BBB = (AAA, ZZZ)",
                            "ZZZ = (ZZZ, ZZZ)"
                        ) to 6
                    )
                ) { (input, expected) ->
                    Day08.part1(input) shouldBe expected
                }
            }
            test("Part Two") {
                val input = listOf(
                    "LR",
                    "",
                    "11A = (11B, XXX)",
                    "11B = (XXX, 11Z)",
                    "11Z = (11B, XXX)",
                    "22A = (22B, XXX)",
                    "22B = (22C, 22C)",
                    "22C = (22Z, 22Z)",
                    "22Z = (22B, 22B)",
                    "XXX = (XXX, XXX)"
                )
                val expected = 6
                Day08.part2(input) shouldBe expected
            }
        }
    }
})
