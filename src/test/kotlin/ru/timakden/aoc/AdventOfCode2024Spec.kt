package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2024.Day01
import ru.timakden.aoc.year2024.Day02

class AdventOfCode2024Spec : FunSpec({
    context("Year 2024") {
        context("Day 1: Historian Hysteria") {
            val input = listOf(
                "3   4",
                "4   3",
                "2   5",
                "1   3",
                "3   9",
                "3   3"
            )
            test("Part One") {
                val expected = 11
                Day01.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 31
                Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Day 2: Red-Nosed Reports") {
            val input = listOf(
                "7 6 4 2 1",
                "1 2 7 8 9",
                "9 7 6 2 1",
                "1 3 2 4 5",
                "8 6 4 4 1",
                "1 3 6 7 9"
            )
            test("Part One") {
                val expected = 2
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 4
                Day02.part2(input) shouldBe expected
            }
        }
    }
})
