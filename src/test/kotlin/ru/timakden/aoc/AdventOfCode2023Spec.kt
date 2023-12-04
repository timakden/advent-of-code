package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2023.Day01
import ru.timakden.aoc.year2023.Day02
import ru.timakden.aoc.year2023.Day03
import ru.timakden.aoc.year2023.Day04

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
    }
})