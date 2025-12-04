package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2025.Day01
import ru.timakden.aoc.year2025.Day02
import ru.timakden.aoc.year2025.Day03
import ru.timakden.aoc.year2025.Day04

class AdventOfCode2025Spec : FunSpec({
    context("Year 2025") {
        context("Day 1: Secret Entrance") {
            val input = listOf(
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"
            )
            test("Part One") {
                val expected = 3
                Day01.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 6
                Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Gift Shop") {
            val input =
                "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
            test("Part One") {
                val expected = 1227775554
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 4174379265
                Day02.part2(input) shouldBe expected
            }
        }

        context("Day 3: Lobby") {
            val input = listOf(
                "987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111"
            )
            test("Part One") {
                val expected = 357
                Day03.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 3121910778619
                Day03.part2(input) shouldBe expected
            }
        }

        context("Day 4: Printing Department") {
            val input = listOf(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
            )
            test("Part One") {
                val expected = 13
                Day04.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 43
                Day04.part2(input) shouldBe expected
            }
        }
    }
})
