package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.tuple
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2016.*

class AdventOfCode2016Spec : FunSpec({
    context("Year 2016") {
        context("Day 1: No Time for a Taxicab") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.a}, expected = ${it.b}" },
                    ts = listOf(tuple("R2, L3", 5), tuple("R2, R2, R2", 2), tuple("R5, L5, R5, R3", 12))
                ) { (input, expected) ->
                    Day01.part1(input) shouldBe expected
                }
            }
            test("Part Two") {
                val input = "R8, R4, R4, R8"
                val expected = 4
                Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Bathroom Security") {
            val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
            test("Part One") {
                val expected = "1985"
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = "5DB3"
                Day02.part2(input) shouldBe expected
            }
        }

        test("Day 4: Security Through Obscurity") {
            val input = listOf(
                "aaaaa-bbb-z-y-x-123[abxyz]",
                "a-b-c-d-e-f-g-h-987[abcde]",
                "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]"
            )
            val expected = 1514
            Day04.part1(input) shouldBe expected
        }

        context("Day 5: How About a Nice Game of Chess?") {
            val input = "abc"
            test("Part One") {
                val expected = "18f47a30"
                Day05.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = "05ace8e3"
                Day05.part2(input) shouldBe expected
            }
        }

        context("Day 6: Signals and Noise") {
            val input = listOf(
                "eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar"
            )
            test("Part One") {
                val expected = "easter"
                Day06.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = "advent"
                Day06.part2(input) shouldBe expected
            }
        }

        context("Day 7: Internet Protocol Version 7") {
            test("Part One") {
                val input = listOf(
                    "abba[mnop]qrst",
                    "abcd[bddb]xyyx",
                    "aaaa[qwer]tyui",
                    "ioxxoj[asdfgh]zxcvbn"
                )
                val expected = 2
                Day07.part1(input) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(
                    "aba[bab]xyz",
                    "xyx[xyx]xyx",
                    "aaa[kek]eke",
                    "zazbz[bzb]cdb"
                )
                val expected = 3
                Day07.part2(input) shouldBe expected
            }
        }

        context("Day 9: Explosives in Cyberspace") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.a}, expected = ${it.b}" },
                    ts = listOf(
                        tuple("ADVENT", 6),
                        tuple("A(1x5)BC", 7),
                        tuple("(3x3)XYZ", 9),
                        tuple("A(2x2)BCD(2x2)EFG", 11),
                        tuple("(6x1)(1x3)A", 6),
                        tuple("X(8x2)(3x3)ABCY", 18)
                    )
                ) { (input, expected) ->
                    Day09.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.a}, expected = ${it.b}" },
                    ts = listOf(
                        tuple("(3x3)XYZ", "XYZXYZXYZ".length),
                        tuple("X(8x2)(3x3)ABCY", "XABCABCABCABCABCABCY".length),
                        tuple("(27x12)(20x12)(13x14)(7x10)(1x12)A", 241920),
                        tuple("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", 445)
                    )
                ) { (input, expected) ->
                    Day09.part2(input) shouldBe expected
                }
            }
        }

        test("Day 10: Balance Bots") {
            val input = mutableListOf(
                "value 5 goes to bot 2",
                "bot 2 gives low to bot 1 and high to bot 0",
                "value 3 goes to bot 1",
                "bot 1 gives low to output 1 and high to bot 0",
                "bot 0 gives low to output 2 and high to output 0",
                "value 2 goes to bot 2"
            )
            val expected = 2
            Day10.part1(input, listOf(2, 5)) shouldBe expected
        }

        test("Day 12: Leonardo's Monorail") {
            val input = listOf("cpy 41 a", "inc a", "inc a", "dec a", "jnz a 2", "dec a")
            val expected = 42
            Day12.part1(input) shouldBe expected
        }

        context("Day 14: One-Time Pad") {
            val input = "abc"
            test("Part One") {
                val expected = 22728
                Day14.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 22551
                Day14.part2(input) shouldBe expected
            }
        }
    }
})
