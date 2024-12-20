package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2024.*

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

        context("Day 2: Red-Nosed Reports") {
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

        context("Day 3: Mull It Over") {
            val input = listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
            test("Part One") {
                val expected = 161
                Day03.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 48
                Day03.part2(input) shouldBe expected
            }
        }

        context("Day 4: Ceres Search") {
            val input = listOf(
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX"
            )
            test("Part One") {
                val expected = 18
                Day04.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 9
                Day04.part2(input) shouldBe expected
            }
        }

        context("Day 5: Print Queue") {
            val input = listOf(
                "47|53",
                "97|13",
                "97|61",
                "97|47",
                "75|29",
                "61|13",
                "75|53",
                "29|13",
                "97|29",
                "53|29",
                "61|53",
                "97|53",
                "61|29",
                "47|13",
                "75|47",
                "97|75",
                "47|61",
                "75|61",
                "47|29",
                "75|13",
                "53|13",
                "",
                "75,47,61,53,29",
                "97,61,53,29,13",
                "75,29,13",
                "75,97,47,61,53",
                "61,13,29",
                "97,13,75,29,47"
            )
            test("Part One") {
                val expected = 143
                Day05.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 123
                Day05.part2(input) shouldBe expected
            }
        }

        context("Day 6: Guard Gallivant") {
            val input = listOf(
                "....#.....",
                ".........#",
                "..........",
                "..#.......",
                ".......#..",
                "..........",
                ".#..^.....",
                "........#.",
                "#.........",
                "......#..."
            )
            test("Part One") {
                val expected = 41
                Day06.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 6
                Day06.part2(input) shouldBe expected
            }
        }

        context("Day 7: Bridge Repair") {
            val input = listOf(
                "190: 10 19",
                "3267: 81 40 27",
                "83: 17 5",
                "156: 15 6",
                "7290: 6 8 6 15",
                "161011: 16 10 13",
                "192: 17 8 14",
                "21037: 9 7 18 13",
                "292: 11 6 16 20"
            )
            test("Part One") {
                val expected = 3749.toBigInteger()
                Day07.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 11387.toBigInteger()
                Day07.part2(input) shouldBe expected
            }
        }

        context("Day 10: Hoof It") {
            val input = listOf(
                "89010123",
                "78121874",
                "87430965",
                "96549874",
                "45678903",
                "32019012",
                "01329801",
                "10456732"
            )
            test("Part One") {
                val expected = 36
                Day10.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 81
                Day10.part2(input) shouldBe expected
            }
        }

        context("Day 11: Plutonian Pebbles") {
            val input = "125 17"
            test("Part One") {
                val expected = 55312
                Day11.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 65601038650482
                Day11.part2(input) shouldBe expected
            }
        }
    }
})
