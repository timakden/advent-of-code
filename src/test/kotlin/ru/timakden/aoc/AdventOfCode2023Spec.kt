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

        context("Day 9: Mirage Maintenance") {
            val input = listOf(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45"
            )
            test("Part One") {
                val expected = 114
                Day09.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 2
                Day09.part2(input) shouldBe expected
            }
        }

        context("Day 10: Pipe Maze") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf(
                            ".....",
                            ".S-7.",
                            ".|.|.",
                            ".L-J.",
                            "....."
                        ) to 4,
                        listOf(
                            "..F7.",
                            ".FJ|.",
                            "SJ.L7",
                            "|F--J",
                            "LJ..."
                        ) to 8
                    )
                ) { (input, expected) ->
                    Day10.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf(
                            "...........",
                            ".S-------7.",
                            ".|F-----7|.",
                            ".||.....||.",
                            ".||.....||.",
                            ".|L-7.F-J|.",
                            ".|..|.|..|.",
                            ".L--J.L--J.",
                            "..........."
                        ) to 4,
                        listOf(
                            ".F----7F7F7F7F-7....",
                            ".|F--7||||||||FJ....",
                            ".||.FJ||||||||L7....",
                            "FJL7L7LJLJ||LJ.L-7..",
                            "L--J.L7...LJS7F-7L7.",
                            "....F-J..F7FJ|L7L7L7",
                            "....L7.F7||L7|.L7L7|",
                            ".....|FJLJ|FJ|F7|.LJ",
                            "....FJL-7.||.||||...",
                            "....L---J.LJ.LJLJ..."
                        ) to 8,
                        listOf(
                            "FF7FSF7F7F7F7F7F---7",
                            "L|LJ||||||||||||F--J",
                            "FL-7LJLJ||||||LJL-77",
                            "F--JF--7||LJLJ7F7FJ-",
                            "L---JF-JLJ.||-FJLJJ7",
                            "|F|F-JF---7F7-L7L|7|",
                            "|FFJF7L7F-JF7|JL---7",
                            "7-L-JL7||F7|L7F-7F7|",
                            "L.L7LFJ|||||FJL7||LJ",
                            "L7JLJL-JLJLJL--JLJ.L"
                        ) to 10
                    )
                ) { (input, expected) ->
                    Day10.part2(input) shouldBe expected
                }
            }
        }

        context("Day 11: Cosmic Expansion") {
            val input = listOf(
                "...#......",
                ".......#..",
                "#.........",
                "..........",
                "......#...",
                ".#........",
                ".........#",
                "..........",
                ".......#..",
                "#...#....."
            )
            test("Part One") {
                val expected = 374L
                Day11.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 82000210L
                Day11.part2(input) shouldBe expected
            }
        }

        context("Day 15: Lens Library") {
            val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
            test("Part One") {
                val expected = 1320
                Day15.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 145
                Day15.part2(input) shouldBe expected
            }
        }
    }
})
