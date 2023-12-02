package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2015.*

class AdventOfCode2015 : FunSpec({
    context("Year 2015") {
        context("Day 1: Not Quite Lisp") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "(())" to 0,
                        "()()" to 0,
                        "(((" to 3,
                        "(()(()(" to 3,
                        "))(((((" to 3,
                        "())" to -1,
                        "))(" to -1,
                        ")))" to -3,
                        ")())())" to -3
                    )
                ) { (input, expected) ->
                    Day01.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        ")" to 1,
                        "()())" to 5
                    )
                ) { (input, expected) ->
                    Day01.part2(input) shouldBe expected
                }
            }
        }

        context("Day 2: I Was Told There Would Be No Math") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf("2x3x4") to 58,
                        listOf("1x1x10") to 43
                    )
                ) { (input, expected) ->
                    Day02.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf("2x3x4") to 34,
                        listOf("1x1x10") to 14
                    )
                ) { (input, expected) ->
                    Day02.part2(input) shouldBe expected
                }
            }
        }

        context("Day 3: Perfectly Spherical Houses in a Vacuum") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        ">" to 2,
                        "^>v<" to 4,
                        "^v^v^v^v^v" to 2
                    )
                ) { (input, expected) ->
                    Day03.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "^v" to 3,
                        "^>v<" to 3,
                        "^v^v^v^v^v" to 11
                    )
                ) { (input, expected) ->
                    Day03.part2(input) shouldBe expected
                }
            }
        }

        context("Day 4: The Ideal Stocking Stuffer") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    "abcdef" to 609043,
                    "pqrstuv" to 1048970
                )
            ) { (input, expected) ->
                Day04.part1(input) shouldBe expected
            }
        }

        context("Day 5: Doesn't He Have Intern-Elves For This?") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "ugknbfddgicrmopn" to true,
                        "aaa" to true,
                        "jchzalrnumimnmhp" to false,
                        "haegwjzuvuyypxyu" to false,
                        "dvszwmarrgswjxmb" to false
                    )
                ) { (input, expected) ->
                    Day05.isStringNicePartOne(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "qjhvhtzxzqqjkmpb" to true,
                        "xxyxx" to true,
                        "uurcxstgmygtbstg" to false,
                        "ieodomkazucvgmuy" to false
                    )
                ) { (input, expected) ->
                    Day05.isStringNicePartTwo(input) shouldBe expected
                }
            }
        }

        context("Day 6: Probably a Fire Hazard") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf("turn on 0,0 through 999,999") to 1000000,
                        listOf("toggle 0,0 through 999,0") to 1000,
                        listOf("turn off 499,499 through 500,500") to 0
                    )
                ) { (input, expected) ->
                    Day06.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf("turn on 0,0 through 0,0") to 1,
                        listOf("toggle 0,0 through 999,999") to 2000000
                    )
                ) { (input, expected) ->
                    Day06.part2(input) shouldBe expected
                }
            }
        }

        test("Day 7: Some Assembly Required") {
            val input = listOf(
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i"
            )
            val expected = mapOf(
                "d" to 72.toUShort(),
                "e" to 507.toUShort(),
                "f" to 492.toUShort(),
                "g" to 114.toUShort(),
                "h" to 65412.toUShort(),
                "i" to 65079.toUShort(),
                "x" to 123.toUShort(),
                "y" to 456.toUShort()
            )
            val wiresToReturn = listOf("d", "e", "f", "g", "h", "i", "x", "y")
            Day07.solve(input, wiresToReturn) shouldContainAll expected
        }

        context("Day 8: Matchsticks") {
            val input = listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")
            test("Part One") {
                val expected = 12
                Day08.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 19
                Day08.part2(input) shouldBe expected
            }
        }

        context("Day 9: All in a Single Night") {
            val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")
            test("Part One") {
                val expected = 605
                Day09.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 982
                Day09.part2(input) shouldBe expected
            }
        }

        context("Day 10: Elves Look, Elves Say") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    "1" to "11",
                    "11" to "21",
                    "21" to "1211",
                    "1211" to "111221",
                    "111221" to "312211"
                )
            ) { (input, expected) ->
                Day10.solve(input, 1) shouldBe expected
            }
        }

        context("Day 11: Corporate Policy") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    "hijklmmn" to false,
                    "abbceffg" to false,
                    "abbcegjk" to false,
                    "abcdefgh" to false,
                    "abcdffaa" to true,
                    "ghijklmn" to false,
                    "ghjaabcc" to true
                )
            ) { (input, expected) ->
                Day11.checkPassword(input) shouldBe expected
            }
        }

        context("Day 12: JSAbacusFramework.io") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        """[1,2,3]""" to 6,
                        """{"a":2,"b":4}""" to 6,
                        """[[[3]]]""" to 3,
                        """{"a":{"b":4},"c":-1}""" to 3,
                        """{"a":[-1,1]}""" to 0,
                        """[-1,{"a":1}]""" to 0,
                        """[]""" to 0,
                        """{}""" to 0
                    )
                ) { (input, expected) ->
                    Day12.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        """[1,2,3]""" to 6,
                        """[1,{"c":"red","b":2},3]""" to 4,
                        """{"d":"red","e":[1,2,3,4],"f":5}""" to 0,
                        """[1,"red",5]""" to 6
                    )
                ) { (input, expected) ->
                    Day12.part2(input) shouldBe expected
                }
            }
        }

        test("Day 13: Knights of the Dinner Table") {
            val input = listOf(
                "Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol."
            )
            val expected = 330
            Day13.part1(input) shouldBe expected
        }

        context("Day 14: Reindeer Olympics") {
            val input = listOf(
                "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."
            )
            val raceTime = 1000
            test("Part One") {
                val expected = 1120
                Day14.part1(input, raceTime) shouldBe expected
            }
            test("Part Two") {
                val expected = 689
                Day14.part2(input, raceTime) shouldBe expected
            }
        }

        context("Day 17: No Such Thing as Too Much") {
            test("Part One") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 4
                val containers = Day17.getContainers(input, litersToStore)
                Day17.part1(containers) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 3
                val containers = Day17.getContainers(input, litersToStore)
                Day17.part2(containers) shouldBe expected
            }
        }

        context("Day 18: Like a GIF For Your Yard") {
            test("Part One") {
                val input = listOf(
                    ".#.#.#",
                    "...##.",
                    "#....#",
                    "..#...",
                    "#.#..#",
                    "####.."
                )
                val numberOfSteps = 4
                val expected = 4
                Day18.solve(input, numberOfSteps) shouldBe expected
            }
            test("Part Two") {
                val input = listOf(
                    "##.#.#",
                    "...##.",
                    "#....#",
                    "..#...",
                    "#.#..#",
                    "####.#"
                )
                val numberOfSteps = 5
                val expected = 17
                Day18.solve(input, numberOfSteps, true) shouldBe expected
            }
        }

        context("Day 19: Medicine for Rudolph") {
            test("Part One") {
                val molecule = "HOH"
                val replacements = listOf("H => HO", "H => OH", "O => HH")
                val expected = 4
                Day19.part1(replacements, molecule) shouldBe expected
            }
            test("Part Two") {
                val molecule = "HOH"
                val replacements = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
                val expected = 3
                Day19.part2(replacements, molecule) shouldBe expected
            }
        }

        test("Day 20: Infinite Elves and Infinite Houses") {
            val input = 70
            val expected = 4
            Day20.part1(input) shouldBe expected
        }

        test("Day 23: Opening the Turing Lock") {
            val input = listOf("inc a", "jio a, +2", "tpl a", "inc a")
            val expected = 2
            Day23.solve(input).first shouldBe expected
        }

        context("Day 24: It Hangs in the Balance") {
            val input = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)
            test("Part One") {
                val expected = 99
                Day24.solve(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 44
                Day24.solve(input, true) shouldBe expected
            }
        }

        context("Day 25: Let It Snow") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    (1 to 1) to 20151125,
                    (4 to 2) to 32451966,
                    (3 to 5) to 11661866,
                    (6 to 6) to 27995004
                )
            ) { (input, expected) ->
                Day25.solve(input) shouldBe expected
            }
        }
    }
})
