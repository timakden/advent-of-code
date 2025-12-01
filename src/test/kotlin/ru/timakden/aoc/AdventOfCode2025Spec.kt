package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2025.Day01

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
    }
})
