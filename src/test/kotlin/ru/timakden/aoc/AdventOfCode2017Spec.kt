package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2017.Day01

class AdventOfCode2017Spec : FunSpec({
    context("Year 2025") {
        context("Day 1: Inverse Captcha") {
            test("Part One") {
                val input = listOf(
                    "1122",
                    "1111",
                    "1234",
                    "91212129"
                )
                val expected = 16
                Day01.part1(input) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(
                    "1212",
                    "1221",
                    "123425",
                    "123123",
                    "12131415"
                )
                val expected = 26
                Day01.part2(input) shouldBe expected
            }
        }
    }
})
