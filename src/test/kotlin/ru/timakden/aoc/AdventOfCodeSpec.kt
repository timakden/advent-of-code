package ru.timakden.aoc

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.util.*
import ru.timakden.aoc.util.Constants.Part.PART_ONE
import ru.timakden.aoc.util.Constants.Part.PART_TWO
import ru.timakden.aoc.year2015.day17.getContainers

class AdventOfCodeSpec : ShouldSpec({
    context("Year 2015") {
        context("Day 1: Not Quite Lisp") {
            should("Part One") {
                forAll(
                    row("(())", 0),
                    row("()()", 0),
                    row("(((", 3),
                    row("(()(()(", 3),
                    row("))(((((", 3),
                    row("())", -1),
                    row("))(", -1),
                    row(")))", -3),
                    row(")())())", -3)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day01.solvePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(")", 1),
                    row("()())", 5)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day01.solvePartTwo(input) shouldBe expected
                }
            }
        }

        context("Day 2: I Was Told There Would Be No Math") {
            should("Part One") {
                forAll(
                    row(listOf("2x3x4"), 58),
                    row(listOf("1x1x10"), 43)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day02.solvePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(listOf("2x3x4"), 34),
                    row(listOf("1x1x10"), 14)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day02.solvePartTwo(input) shouldBe expected
                }
            }
        }

        context("Day 3: Perfectly Spherical Houses in a Vacuum") {
            should("Part One") {
                forAll(
                    row(">", 2),
                    row("^>v<", 4),
                    row("^v^v^v^v^v", 2)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day03.solvePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("^v", 3),
                    row("^>v<", 3),
                    row("^v^v^v^v^v", 11)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day03.solvePartTwo(input) shouldBe expected
                }
            }
        }

        should("Day 4: The Ideal Stocking Stuffer") {
            forAll(
                row("abcdef", 609043),
                row("pqrstuv", 1048970)
            ) { input, expected ->
                ru.timakden.aoc.year2015.day04.solve(input, PART_ONE) shouldBe expected
            }
        }

        context("Day 5: Doesn't He Have Intern-Elves For This?") {
            should("Part One") {
                forAll(
                    row("ugknbfddgicrmopn", true),
                    row("aaa", true),
                    row("jchzalrnumimnmhp", false),
                    row("haegwjzuvuyypxyu", false),
                    row("dvszwmarrgswjxmb", false)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day05.isStringNicePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("qjhvhtzxzqqjkmpb", true),
                    row("xxyxx", true),
                    row("uurcxstgmygtbstg", false),
                    row("ieodomkazucvgmuy", false)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day05.isStringNicePartTwo(input) shouldBe expected
                }
            }
        }

        context("Day 6: Probably a Fire Hazard") {
            should("Part One") {
                forAll(
                    row(listOf("turn on 0,0 through 999,999"), 1000000),
                    row(listOf("toggle 0,0 through 999,0"), 1000),
                    row(listOf("turn off 499,499 through 500,500"), 0)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day06.solvePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(listOf("turn on 0,0 through 0,0"), 1),
                    row(listOf("toggle 0,0 through 999,999"), 2000000)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day06.solvePartTwo(input) shouldBe expected
                }
            }
        }

        should("Day 7: Some Assembly Required") {
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

            val result =
                ru.timakden.aoc.year2015.day07.solve(input, listOf("d", "e", "f", "g", "h", "i", "x", "y"))

            result shouldContainAll expected
        }

        context("Day 8: Matchsticks") {
            val input = listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")

            should("Part One") {
                val expected = 12
                val result = ru.timakden.aoc.year2015.day08.solvePartOne(input)

                result shouldBe expected
            }

            should("Part Two") {
                val expected = 19
                val result = ru.timakden.aoc.year2015.day08.solvePartTwo(input)

                result shouldBe expected
            }
        }

        context("Day 9: All in a Single Night") {
            val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")

            should("Part One") {
                val expected = 605
                val result = ru.timakden.aoc.year2015.day09.solve(input, PART_ONE)

                result shouldBe expected
            }

            should("Part Two") {
                val expected = 982
                val result = ru.timakden.aoc.year2015.day09.solve(input, PART_TWO)

                result shouldBe expected
            }
        }

        should("Day 10: Elves Look, Elves Say") {
            forAll(
                row("1", "11"),
                row("11", "21"),
                row("21", "1211"),
                row("1211", "111221"),
                row("111221", "312211")
            ) { input, expected ->
                ru.timakden.aoc.year2015.day10.solve(input, 1) shouldBe expected
            }
        }

        should("Day 11: Corporate Policy") {
            forAll(
                row("hijklmmn", false),
                row("abbceffg", false),
                row("abbcegjk", false),
                row("abcdefgh", false),
                row("abcdffaa", true),
                row("ghijklmn", false),
                row("ghjaabcc", true)
            ) { input, expected ->
                ru.timakden.aoc.year2015.day11.checkPassword(input) shouldBe expected
            }
        }

        context("Day 12: JSAbacusFramework.io") {
            should("Part One") {
                forAll(
                    row("""[1,2,3]""", 6),
                    row("""{"a":2,"b":4}""", 6),
                    row("""[[[3]]]""", 3),
                    row("""{"a":{"b":4},"c":-1}""", 3),
                    row("""{"a":[-1,1]}""", 0),
                    row("""[-1,{"a":1}]""", 0),
                    row("""[]""", 0),
                    row("""{}""", 0)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day12.solve(input, PART_TWO) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("""[1,2,3]""", 6),
                    row("""[1,{"c":"red","b":2},3]""", 4),
                    row("""{"d":"red","e":[1,2,3,4],"f":5}""", 0),
                    row("""[1,"red",5]""", 6)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.day12.solve(input, PART_TWO) shouldBe expected
                }
            }
        }

        should("Day 13: Knights of the Dinner Table") {
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

            ru.timakden.aoc.year2015.day13.solve(input, PART_ONE) shouldBe expected
        }

        should("Day 14: Reindeer Olympics") {
            val input = listOf(
                "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."
            )

            val raceTime = 1000

            val expected = 1120

            val reindeerList = ru.timakden.aoc.year2015.day14.race(input, raceTime)

            reindeerList.maxByOrNull { it.totalDistance }?.totalDistance shouldBe expected
        }

        xcontext("Day 15: Science for Hungry People") { TODO() }

        xcontext("Day 16: Aunt Sue") { TODO() }

        context("Day 17: No Such Thing as Too Much") {
            should("Part One") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 4
                val containers = getContainers(input, litersToStore)
                ru.timakden.aoc.year2015.day17.solve(containers, PART_ONE) shouldBe expected
            }

            should("Part Two") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 3
                val containers = getContainers(input, litersToStore)
                ru.timakden.aoc.year2015.day17.solve(containers, PART_TWO) shouldBe expected
            }
        }

        context("Day 18: Like a GIF For Your Yard") {
            should("Part One") {
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

                ru.timakden.aoc.year2015.day18.solve(input, numberOfSteps, PART_ONE) shouldBe expected
            }

            should("Part Two") {
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

                ru.timakden.aoc.year2015.day18.solve(input, numberOfSteps, PART_TWO) shouldBe expected
            }
        }

        context("Day 19: Medicine for Rudolph") {
            should("Part One") {
                val molecule = "HOH"
                val replacements = listOf("H => HO", "H => OH", "O => HH")
                val expected = 4
                ru.timakden.aoc.year2015.day19.solvePartOne(replacements, molecule) shouldBe expected
            }

            should("Part Two") {
                val molecule = "HOH"
                val replacements = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
                val expected = 3
                ru.timakden.aoc.year2015.day19.solvePartTwo(replacements, molecule) shouldBe expected
            }
        }

        should("Day 20: Infinite Elves and Infinite Houses") {
            val input = 70
            val expected = 4
            ru.timakden.aoc.year2015.day20.solve(input, PART_ONE) shouldBe expected
        }

        xcontext("Day 21: RPG Simulator 20XX") { TODO() }

        xcontext("Day 22: Wizard Simulator 20XX") { TODO() }

        should("Day 23: Opening the Turing Lock") {
            val input = listOf("inc a", "jio a, +2", "tpl a", "inc a")
            val expected = 2
            ru.timakden.aoc.year2015.day23.solve(input, PART_ONE)[0] shouldBe expected
        }

        xcontext("Day 24: It Hangs in the Balance") { TODO() }

        should("Day 25: Let It Snow") {
            forAll(
                row(1 to 1, 20151125),
                row(4 to 2, 32451966),
                row(3 to 5, 11661866),
                row(6 to 6, 27995004)
            ) { input, expected ->
                ru.timakden.aoc.year2015.day25.solve(input) shouldBe expected
            }
        }
    }

    context("Year 2016") {
        context("Day 1: No Time for a Taxicab") {
            should("Part One") {
                forAll(
                    row("R2, L3", 5),
                    row("R2, R2, R2", 2),
                    row("R5, L5, R5, R3", 12)
                ) { input, expected ->
                    ru.timakden.aoc.year2016.day01.solvePartOne(input) shouldBe expected
                }
            }

            should("Part Two") {
                val input = "R8, R4, R4, R8"
                val expected = 4

                ru.timakden.aoc.year2016.day01.solvePartTwo(input) shouldBe expected
            }
        }

        context("Day 2: Bathroom Security") {
            should("Part One") {
                val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
                val expected = "1985"

                ru.timakden.aoc.year2016.day02.solve(input, PART_ONE) shouldBe expected
            }

            should("Part Two") {
                val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
                val expected = "5DB3"

                ru.timakden.aoc.year2016.day02.solve(input, PART_TWO) shouldBe expected
            }
        }

        xcontext("Day 3: Squares With Three Sides") { TODO() }
    }

    context("Year 2022") {
        context("Day 1: Calorie Counting") {
            val input = """1000
                           |2000
                           |3000
                           |
                           |4000
                           |
                           |5000
                           |6000
                           |
                           |7000
                           |8000
                           |9000
                           |
                           |10000""".trimMargin()

            should("Part One") {
                val expected = 24000
                ru.timakden.aoc.year2022.day01.solvePartOne(input) shouldBe expected
            }

            should("Part Two") {
                val expected = 45000
                ru.timakden.aoc.year2022.day01.solvePartTwo(input) shouldBe expected
            }
        }

        context("Day 2: Rock Paper Scissors") {
            val input = """A Y
                           |B X
                           |C Z""".trimMargin()

            should("Part One") {
                val expected = 15
                ru.timakden.aoc.year2022.day02.solvePartOne(input) shouldBe expected
            }

            should("Part Two") {
                val expected = 12
                ru.timakden.aoc.year2022.day02.solvePartTwo(input) shouldBe expected
            }
        }

        context("Day 3: Rucksack Reorganization") {
            val input = listOf(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
            )

            should("Part One") {
                val expected = 157
                ru.timakden.aoc.year2022.day03.solvePartOne(input) shouldBe expected
            }

            should("Part Two") {
                val expected = 70
                ru.timakden.aoc.year2022.day03.solvePartTwo(input) shouldBe expected
            }
        }

        context("Day 4: Camp Cleanup") {
            val input = listOf(
                "2-4,6-8",
                "2-3,4-5",
                "5-7,7-9",
                "2-8,3-7",
                "6-6,4-6",
                "2-6,4-8"
            )

            should("Part One") {
                val expected = 2
                ru.timakden.aoc.year2022.day04.solvePartOne(input) shouldBe expected
            }

            should("Part Two") {
                val expected = 4
                ru.timakden.aoc.year2022.day04.solvePartTwo(input) shouldBe expected
            }
        }

        context("Day 5: Supply Stacks") {
            val stacks = listOf(
                ArrayDeque(listOf('Z', 'N')),
                ArrayDeque(listOf('M', 'C', 'D')),
                ArrayDeque(listOf('P'))
            )

            val input = listOf(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
            )

            should("Part One") {
                val expected = "CMZ"
                ru.timakden.aoc.year2022.day05.solvePartOne(stacks, input) shouldBe expected
            }

            should("Part Two") {
                val expected = "MCD"
                ru.timakden.aoc.year2022.day05.solvePartTwo(stacks, input) shouldBe expected
            }
        }
    }

    context("Utils") {
        should("md5Hex() generates MD5 hash as a hex string") {
            val expected = "35454b055cc325ea1af2126e27707052"
            md5Hex("ILoveJava") shouldBe expected
        }

        should("String.isNumber() parses numbers in a string") {
            forAll(
                row("123", true),
                row("abc", false),
                row("123.45", true)
            ) { input, expected ->
                input.isNumber() shouldBe expected
            }
        }

        should("String.isLetter() parses letters in a string") {
            forAll(
                row("abc", true),
                row("123", false),
                row("ab12", false)
            ) { input, expected ->
                input.isLetter() shouldBe expected
            }
        }

        should("Permutations.of() generates all permutations of a list") {
            val expected = listOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 2, 1),
                listOf(3, 1, 2)
            )

            Permutations.of(listOf(1, 2, 3)).toList().map { it.toList() } shouldContainExactlyInAnyOrder expected
        }

        should("PowerSet.of() returns the set of all possible subsets") {
            val input = setOf(1, 2, 3, 4)
            val powerSet = PowerSet(input)
            val expectedSize = 1 shl input.size

            powerSet.toList().size shouldBe expectedSize
        }
    }
})
