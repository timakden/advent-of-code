package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2024.Day01

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
    }
})
