package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.tuple
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2017.Day01
import ru.timakden.aoc.year2017.Day02
import ru.timakden.aoc.year2017.Day03

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

        context("Day 2: Corruption Checksum") {
            test("Part One") {
                val input = listOf(
                    "5 1 9 5",
                    "7 5 3",
                    "2 4 6 8"
                )
                val expected = 18
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(
                    "5 9 2 8",
                    "9 4 7 3",
                    "3 8 6 5",
                )
                val expected = 9
                Day02.part2(input) shouldBe expected
            }
        }

        context("Day 3: Spiral Memory") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.a}, expected = ${it.b}" },
                    ts = listOf(
                        tuple("1", 0),
                        tuple("12", 3),
                        tuple("23", 2),
                        tuple("1024", 31)
                    )
                ) { (input, expected) ->
                    Day03.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.a}, expected = ${it.b}" },
                    ts = listOf(
                        tuple("100", 122),
                        tuple("300", 304),
                        tuple("500", 747),
                        tuple("1000", 1968)
                    )
                ) { (input, expected) ->
                    Day03.part2(input) shouldBe expected
                }
            }
        }
    }
})
