package ru.timakden.aoc

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.util.*
import ru.timakden.aoc.year2015.Day17.getContainers

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
                    ru.timakden.aoc.year2015.Day01.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(")", 1),
                    row("()())", 5)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day01.part2(input) shouldBe expected
                }
            }
        }

        context("Day 2: I Was Told There Would Be No Math") {
            should("Part One") {
                forAll(
                    row(listOf("2x3x4"), 58),
                    row(listOf("1x1x10"), 43)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day02.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(listOf("2x3x4"), 34),
                    row(listOf("1x1x10"), 14)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day02.part2(input) shouldBe expected
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
                    ru.timakden.aoc.year2015.Day03.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("^v", 3),
                    row("^>v<", 3),
                    row("^v^v^v^v^v", 11)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day03.part2(input) shouldBe expected
                }
            }
        }

        should("Day 4: The Ideal Stocking Stuffer") {
            forAll(
                row("abcdef", 609043),
                row("pqrstuv", 1048970)
            ) { input, expected ->
                ru.timakden.aoc.year2015.Day04.part1(input) shouldBe expected
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
                    ru.timakden.aoc.year2015.Day05.isStringNicePartOne(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("qjhvhtzxzqqjkmpb", true),
                    row("xxyxx", true),
                    row("uurcxstgmygtbstg", false),
                    row("ieodomkazucvgmuy", false)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day05.isStringNicePartTwo(input) shouldBe expected
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
                    ru.timakden.aoc.year2015.Day06.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row(listOf("turn on 0,0 through 0,0"), 1),
                    row(listOf("toggle 0,0 through 999,999"), 2000000)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day06.part2(input) shouldBe expected
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
            val wiresToReturn = listOf("d", "e", "f", "g", "h", "i", "x", "y")
            ru.timakden.aoc.year2015.Day07.solve(input, wiresToReturn) shouldContainAll expected
        }

        context("Day 8: Matchsticks") {
            val input = listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")
            should("Part One") {
                val expected = 12
                ru.timakden.aoc.year2015.Day08.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 19
                ru.timakden.aoc.year2015.Day08.part2(input) shouldBe expected
            }
        }

        context("Day 9: All in a Single Night") {
            val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")
            should("Part One") {
                val expected = 605
                ru.timakden.aoc.year2015.Day09.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 982
                ru.timakden.aoc.year2015.Day09.part2(input) shouldBe expected
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
                ru.timakden.aoc.year2015.Day10.solve(input, 1) shouldBe expected
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
                ru.timakden.aoc.year2015.Day11.checkPassword(input) shouldBe expected
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
                    ru.timakden.aoc.year2015.Day12.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("""[1,2,3]""", 6),
                    row("""[1,{"c":"red","b":2},3]""", 4),
                    row("""{"d":"red","e":[1,2,3,4],"f":5}""", 0),
                    row("""[1,"red",5]""", 6)
                ) { input, expected ->
                    ru.timakden.aoc.year2015.Day12.part2(input) shouldBe expected
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
            ru.timakden.aoc.year2015.Day13.part1(input) shouldBe expected
        }

        context("Day 14: Reindeer Olympics") {
            val input = listOf(
                "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."
            )
            val raceTime = 1000
            should("Part One") {
                val expected = 1120
                ru.timakden.aoc.year2015.Day14.part1(input, raceTime) shouldBe expected
            }
            should("Part Two") {
                val expected = 689
                ru.timakden.aoc.year2015.Day14.part2(input, raceTime) shouldBe expected
            }
        }

        context("Day 17: No Such Thing as Too Much") {
            should("Part One") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 4
                val containers = getContainers(input, litersToStore)
                ru.timakden.aoc.year2015.Day17.part1(containers) shouldBe expected
            }
            should("Part Two") {
                val input = listOf(5, 5, 10, 15, 20)
                val litersToStore = 25
                val expected = 3
                val containers = getContainers(input, litersToStore)
                ru.timakden.aoc.year2015.Day17.part2(containers) shouldBe expected
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
                ru.timakden.aoc.year2015.Day18.solve(input, numberOfSteps) shouldBe expected
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
                ru.timakden.aoc.year2015.Day18.solve(input, numberOfSteps, true) shouldBe expected
            }
        }

        context("Day 19: Medicine for Rudolph") {
            should("Part One") {
                val molecule = "HOH"
                val replacements = listOf("H => HO", "H => OH", "O => HH")
                val expected = 4
                ru.timakden.aoc.year2015.Day19.part1(replacements, molecule) shouldBe expected
            }
            should("Part Two") {
                val molecule = "HOH"
                val replacements = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
                val expected = 3
                ru.timakden.aoc.year2015.Day19.part2(replacements, molecule) shouldBe expected
            }
        }

        should("Day 20: Infinite Elves and Infinite Houses") {
            val input = 70
            val expected = 4
            ru.timakden.aoc.year2015.Day20.part1(input) shouldBe expected
        }

        should("Day 23: Opening the Turing Lock") {
            val input = listOf("inc a", "jio a, +2", "tpl a", "inc a")
            val expected = 2
            ru.timakden.aoc.year2015.Day23.solve(input).first shouldBe expected
        }

        context("Day 24: It Hangs in the Balance") {
            val input = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)
            should("Part One") {
                val expected = 99
                ru.timakden.aoc.year2015.Day24.solve(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 44
                ru.timakden.aoc.year2015.Day24.solve(input, true) shouldBe expected
            }
        }

        should("Day 25: Let It Snow") {
            forAll(
                row(1 to 1, 20151125),
                row(4 to 2, 32451966),
                row(3 to 5, 11661866),
                row(6 to 6, 27995004)
            ) { input, expected ->
                ru.timakden.aoc.year2015.Day25.solve(input) shouldBe expected
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
                    ru.timakden.aoc.year2016.Day01.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                val input = "R8, R4, R4, R8"
                val expected = 4
                ru.timakden.aoc.year2016.Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Bathroom Security") {
            should("Part One") {
                val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
                val expected = "1985"
                ru.timakden.aoc.year2016.Day02.part1(input) shouldBe expected
            }
            should("Part Two") {
                val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
                val expected = "5DB3"
                ru.timakden.aoc.year2016.Day02.part2(input) shouldBe expected
            }
        }

        should("Day 4: Security Through Obscurity") {
            val input = listOf(
                "aaaaa-bbb-z-y-x-123[abxyz]",
                "a-b-c-d-e-f-g-h-987[abcde]",
                "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]"
            )
            val expected = 1514
            ru.timakden.aoc.year2016.Day04.part1(input) shouldBe expected
        }

        context("Day 5: How About a Nice Game of Chess?") {
            val input = "abc"
            should("Part One") {
                val expected = "18f47a30"
                ru.timakden.aoc.year2016.Day05.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = "05ace8e3"
                ru.timakden.aoc.year2016.Day05.part2(input) shouldBe expected
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
            should("Part One") {
                val expected = "easter"
                ru.timakden.aoc.year2016.Day06.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = "advent"
                ru.timakden.aoc.year2016.Day06.part2(input) shouldBe expected
            }
        }

        context("Day 7: Internet Protocol Version 7") {
            should("Part One") {
                val input = listOf(
                    "abba[mnop]qrst",
                    "abcd[bddb]xyyx",
                    "aaaa[qwer]tyui",
                    "ioxxoj[asdfgh]zxcvbn"
                )
                val expected = 2
                ru.timakden.aoc.year2016.Day07.part1(input) shouldBe expected
            }
            should("Part Two") {
                val input = listOf(
                    "aba[bab]xyz",
                    "xyx[xyx]xyx",
                    "aaa[kek]eke",
                    "zazbz[bzb]cdb"
                )
                val expected = 3
                ru.timakden.aoc.year2016.Day07.part2(input) shouldBe expected
            }
        }

        context("Day 9: Explosives in Cyberspace") {
            should("Part One") {
                forAll(
                    row("ADVENT", 6),
                    row("A(1x5)BC", 7),
                    row("(3x3)XYZ", 9),
                    row("A(2x2)BCD(2x2)EFG", 11),
                    row("(6x1)(1x3)A", 6),
                    row("X(8x2)(3x3)ABCY", 18)
                ) { input, expected ->
                    ru.timakden.aoc.year2016.Day09.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("(3x3)XYZ", "XYZXYZXYZ".length),
                    row("X(8x2)(3x3)ABCY", "XABCABCABCABCABCABCY".length),
                    row("(27x12)(20x12)(13x14)(7x10)(1x12)A", 241920),
                    row("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", 445)
                ) { input, expected ->
                    ru.timakden.aoc.year2016.Day09.part2(input) shouldBe expected
                }
            }
        }

        should("Day 10: Balance Bots") {
            val input = mutableListOf(
                "value 5 goes to bot 2",
                "bot 2 gives low to bot 1 and high to bot 0",
                "value 3 goes to bot 1",
                "bot 1 gives low to output 1 and high to bot 0",
                "bot 0 gives low to output 2 and high to output 0",
                "value 2 goes to bot 2"
            )
            val expected = 2
            ru.timakden.aoc.year2016.Day10.part1(input, listOf(2, 5)) shouldBe expected
        }

        should("Day 12: Leonardo's Monorail") {
            val input = listOf(
                "cpy 41 a",
                "inc a",
                "inc a",
                "dec a",
                "jnz a 2",
                "dec a"
            )
            val expected = 42
            ru.timakden.aoc.year2016.Day12.part1(input) shouldBe expected
        }

        context("Day 14: One-Time Pad") {
            val input = "abc"
            should("Part One") {
                val expected = 22728
                ru.timakden.aoc.year2016.Day14.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 22551
                ru.timakden.aoc.year2016.Day14.part2(input) shouldBe expected
            }
        }
    }

    context("Year 2022") {
        context("Day 1: Calorie Counting") {
            val input = listOf(
                "1000",
                "2000",
                "3000",
                "",
                "4000",
                "",
                "5000",
                "6000",
                "",
                "7000",
                "8000",
                "9000",
                "",
                "10000"
            )
            should("Part One") {
                val expected = 24000
                ru.timakden.aoc.year2022.Day01.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 45000
                ru.timakden.aoc.year2022.Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Rock Paper Scissors") {
            val input = listOf(
                "A Y",
                "B X",
                "C Z"
            )
            should("Part One") {
                val expected = 15
                ru.timakden.aoc.year2022.Day02.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 12
                ru.timakden.aoc.year2022.Day02.part2(input) shouldBe expected
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
                ru.timakden.aoc.year2022.Day03.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 70
                ru.timakden.aoc.year2022.Day03.part2(input) shouldBe expected
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
                ru.timakden.aoc.year2022.Day04.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 4
                ru.timakden.aoc.year2022.Day04.part2(input) shouldBe expected
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
                ru.timakden.aoc.year2022.Day05.part1(stacks, input) shouldBe expected
            }
            should("Part Two") {
                val expected = "MCD"
                ru.timakden.aoc.year2022.Day05.part2(stacks, input) shouldBe expected
            }
        }

        context("Day 6: Tuning Trouble") {
            should("Part One") {
                forAll(
                    row("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
                    row("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
                    row("nppdvjthqldpwncqszvftbrmjlhg", 6),
                    row("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
                    row("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
                ) { input, expected ->
                    ru.timakden.aoc.year2022.Day06.part1(input) shouldBe expected
                }
            }
            should("Part Two") {
                forAll(
                    row("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
                    row("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
                    row("nppdvjthqldpwncqszvftbrmjlhg", 23),
                    row("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
                    row("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26)
                ) { input, expected ->
                    ru.timakden.aoc.year2022.Day06.part2(input) shouldBe expected
                }
            }
        }

        context("Day 7: No Space Left On Device") {
            val input = listOf(
                "$ cd /",
                "$ ls",
                "dir a",
                "14848514 b.txt",
                "8504156 c.dat",
                "dir d",
                "$ cd a",
                "$ ls",
                "dir e",
                "29116 f",
                "2557 g",
                "62596 h.lst",
                "$ cd e",
                "$ ls",
                "584 i",
                "$ cd ..",
                "$ cd ..",
                "$ cd d",
                "$ ls",
                "4060174 j",
                "8033020 d.log",
                "5626152 d.ext",
                "7214296 k"
            )
            should("Part One") {
                val expected = 95437
                ru.timakden.aoc.year2022.Day07.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 24933642
                ru.timakden.aoc.year2022.Day07.part2(input) shouldBe expected
            }
        }

        context("Day 8: Treetop Tree House") {
            val input = listOf(
                "30373",
                "25512",
                "65332",
                "33549",
                "35390"
            )
            should("Part One") {
                val expected = 21
                ru.timakden.aoc.year2022.Day08.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 8
                ru.timakden.aoc.year2022.Day08.part2(input) shouldBe expected
            }
        }

        context("Day 9: Rope Bridge") {
            should("Part One") {
                val input = listOf(
                    "R 4",
                    "U 4",
                    "L 3",
                    "D 1",
                    "R 4",
                    "D 1",
                    "L 5",
                    "R 2"
                )
                val expected = 13
                ru.timakden.aoc.year2022.Day09.part1(input) shouldBe expected
            }

            should("Part Two") {
                forAll(
                    row(
                        listOf(
                            "R 4",
                            "U 4",
                            "L 3",
                            "D 1",
                            "R 4",
                            "D 1",
                            "L 5",
                            "R 2"
                        ), 1
                    ),
                    row(
                        listOf(
                            "R 5",
                            "U 8",
                            "L 8",
                            "D 3",
                            "R 17",
                            "D 10",
                            "L 25",
                            "U 20"
                        ), 36
                    )
                ) { input, expected ->
                    ru.timakden.aoc.year2022.Day09.part2(input) shouldBe expected
                }
            }
        }

        should("Day 10: Cathode-Ray Tube") {
            val input = listOf(
                "addx 15",
                "addx -11",
                "addx 6",
                "addx -3",
                "addx 5",
                "addx -1",
                "addx -8",
                "addx 13",
                "addx 4",
                "noop",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx -35",
                "addx 1",
                "addx 24",
                "addx -19",
                "addx 1",
                "addx 16",
                "addx -11",
                "noop",
                "noop",
                "addx 21",
                "addx -15",
                "noop",
                "noop",
                "addx -3",
                "addx 9",
                "addx 1",
                "addx -3",
                "addx 8",
                "addx 1",
                "addx 5",
                "noop",
                "noop",
                "noop",
                "noop",
                "noop",
                "addx -36",
                "noop",
                "addx 1",
                "addx 7",
                "noop",
                "noop",
                "noop",
                "addx 2",
                "addx 6",
                "noop",
                "noop",
                "noop",
                "noop",
                "noop",
                "addx 1",
                "noop",
                "noop",
                "addx 7",
                "addx 1",
                "noop",
                "addx -13",
                "addx 13",
                "addx 7",
                "noop",
                "addx 1",
                "addx -33",
                "noop",
                "noop",
                "noop",
                "addx 2",
                "noop",
                "noop",
                "noop",
                "addx 8",
                "noop",
                "addx -1",
                "addx 2",
                "addx 1",
                "noop",
                "addx 17",
                "addx -9",
                "addx 1",
                "addx 1",
                "addx -3",
                "addx 11",
                "noop",
                "noop",
                "addx 1",
                "noop",
                "addx 1",
                "noop",
                "noop",
                "addx -13",
                "addx -19",
                "addx 1",
                "addx 3",
                "addx 26",
                "addx -30",
                "addx 12",
                "addx -1",
                "addx 3",
                "addx 1",
                "noop",
                "noop",
                "noop",
                "addx -9",
                "addx 18",
                "addx 1",
                "addx 2",
                "noop",
                "noop",
                "addx 9",
                "noop",
                "noop",
                "noop",
                "addx -1",
                "addx 2",
                "addx -37",
                "addx 1",
                "addx 3",
                "noop",
                "addx 15",
                "addx -21",
                "addx 22",
                "addx -6",
                "addx 1",
                "noop",
                "addx 2",
                "addx 1",
                "noop",
                "addx -10",
                "noop",
                "noop",
                "addx 20",
                "addx 1",
                "addx 2",
                "addx 2",
                "addx -6",
                "addx -11",
                "noop",
                "noop",
                "noop"
            )
            val expected = 13140
            ru.timakden.aoc.year2022.Day10.part1(input) shouldBe expected
        }

        context("Day 11: Monkey in the Middle") {
            val input = listOf(
                "Monkey 0:",
                "   Starting items: 79, 98",
                "   Operation: new = old * 19",
                "   Test: divisible by 23",
                "       If true: throw to monkey 2",
                "       If false: throw to monkey 3",
                "",
                "Monkey 1:",
                "   Starting items: 54, 65, 75, 74",
                "   Operation: new = old + 6",
                "   Test: divisible by 19",
                "       If true: throw to monkey 2",
                "       If false: throw to monkey 0",
                "",
                "Monkey 2:",
                "   Starting items: 79, 60, 97",
                "   Operation: new = old * old",
                "   Test: divisible by 13",
                "       If true: throw to monkey 1",
                "       If false: throw to monkey 3",
                "",
                "Monkey 3:",
                "   Starting items: 74",
                "   Operation: new = old + 3",
                "   Test: divisible by 17",
                "   If true: throw to monkey 0",
                "   If false: throw to monkey 1",
            )
            should("Part One") {
                val expected = 10605
                ru.timakden.aoc.year2022.Day11.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 2713310158
                ru.timakden.aoc.year2022.Day11.part2(input) shouldBe expected
            }
        }

        context("Day 12: Hill Climbing Algorithm") {
            val input = listOf(
                "Sabqponm",
                "abcryxxl",
                "accszExk",
                "acctuvwj",
                "abdefghi"
            )
            should("Part One") {
                val expected = 31
                ru.timakden.aoc.year2022.Day12.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 29
                ru.timakden.aoc.year2022.Day12.part2(input) shouldBe expected
            }
        }

        context("Day 13: Distress Signal") {
            val input = listOf(
                "[1,1,3,1,1]",
                "[1,1,5,1,1]",
                "",
                "[[1],[2,3,4]]",
                "[[1],4]",
                "",
                "[9]",
                "[[8,7,6]]",
                "",
                "[[4,4],4,4]",
                "[[4,4],4,4,4]",
                "",
                "[7,7,7,7]",
                "[7,7,7]",
                "",
                "[]",
                "[3]",
                "",
                "[[[]]]",
                "[[]]",
                "",
                "[1,[2,[3,[4,[5,6,7]]]],8,9]",
                "[1,[2,[3,[4,[5,6,0]]]],8,9]"
            )
            should("Part One") {
                val expected = 13
                ru.timakden.aoc.year2022.Day13.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 140
                ru.timakden.aoc.year2022.Day13.part2(input) shouldBe expected
            }
        }

        context("Day 14: Regolith Reservoir") {
            val input = listOf(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
            )
            should("Part One") {
                val expected = 24
                ru.timakden.aoc.year2022.Day14.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 93
                ru.timakden.aoc.year2022.Day14.part2(input) shouldBe expected
            }
        }

        context("Day 15: Beacon Exclusion Zone") {
            val input = listOf(
                "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
            )
            should("Part One") {
                val expected = 26
                ru.timakden.aoc.year2022.Day15.part1(input, 10) shouldBe expected
            }
            should("Part Two") {
                val expected = 56000011
                ru.timakden.aoc.year2022.Day15.part2(input, 20) shouldBe expected
            }
        }

        context("Day 16: Proboscidea Volcanium") {
            val input = listOf(
                "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                "Valve HH has flow rate=22; tunnel leads to valve GG",
                "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                "Valve JJ has flow rate=21; tunnel leads to valve II"
            )
            should("Part One") {
                val expected = 1651
                ru.timakden.aoc.year2022.Day16.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 1707
                ru.timakden.aoc.year2022.Day16.part2(input) shouldBe expected
            }
        }

        context("Day 17: Pyroclastic Flow") {
            val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
            should("Part One") {
                val expected = 3068
                ru.timakden.aoc.year2022.Day17.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 1514285714288
                ru.timakden.aoc.year2022.Day17.part2(input, 1000000000000L) shouldBe expected
            }
        }

        context("Day 18: Boiling Boulders") {
            val input = listOf(
                "2,2,2",
                "1,2,2",
                "3,2,2",
                "2,1,2",
                "2,3,2",
                "2,2,1",
                "2,2,3",
                "2,2,4",
                "2,2,6",
                "1,2,5",
                "3,2,5",
                "2,1,5",
                "2,3,5"
            )
            should("Part One") {
                val expected = 64
                ru.timakden.aoc.year2022.Day18.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 58
                ru.timakden.aoc.year2022.Day18.part2(input) shouldBe expected
            }
        }

        context("Day 19: Not Enough Minerals") {
            val input = listOf(
                "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.",
                "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian."
            )
            should("Part One") {
                val expected = 33
                ru.timakden.aoc.year2022.Day19.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 3472
                ru.timakden.aoc.year2022.Day19.part2(input) shouldBe expected
            }
        }

        context("Day 20: Grove Positioning System") {
            val input = listOf("1", "2", "-3", "3", "-2", "0", "4")
            should("Part One") {
                val expected = 3
                ru.timakden.aoc.year2022.Day20.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 1623178306
                ru.timakden.aoc.year2022.Day20.part2(input) shouldBe expected
            }
        }

        context("Day 21: Monkey Math") {
            val input = listOf(
                "root: pppw + sjmn",
                "dbpl: 5",
                "cczh: sllz + lgvd",
                "zczc: 2",
                "ptdq: humn - dvpt",
                "dvpt: 3",
                "lfqf: 4",
                "humn: 5",
                "ljgn: 2",
                "sjmn: drzm * dbpl",
                "sllz: 4",
                "pppw: cczh / lfqf",
                "lgvd: ljgn * ptdq",
                "drzm: hmdt - zczc",
                "hmdt: 32"
            )
            should("Part One") {
                val expected = 152
                ru.timakden.aoc.year2022.Day21.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 301
                ru.timakden.aoc.year2022.Day21.part2(input) shouldBe expected
            }
        }

        should("Day 22: Monkey Map") {
            val input = listOf(
                "        ...#",
                "        .#..",
                "        #...",
                "        ....",
                "...#.......#",
                "........#...",
                "..#....#....",
                "..........#.",
                "        ...#....",
                "        .....#..",
                "        .#......",
                "        ......#.",
                "",
                "10R5L5R10L4R5L5"
            )
            val expected = 6032
            ru.timakden.aoc.year2022.Day22.part1(input) shouldBe expected
        }

        context("Day 23: Unstable Diffusion") {
            val input = listOf(
                "..............",
                "..............",
                ".......#......",
                ".....###.#....",
                "...#...#.#....",
                "....#...##....",
                "...#.###......",
                "...##.#.##....",
                "....#..#......",
                "..............",
                "..............",
                ".............."
            )
            should("Part One") {
                val expected = 110
                ru.timakden.aoc.year2022.Day23.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 20
                ru.timakden.aoc.year2022.Day23.part2(input) shouldBe expected
            }
        }

        context("Day 24: Blizzard Basin") {
            val input = listOf(
                "#.######",
                "#>>.<^<#",
                "#.<..<<#",
                "#>v.><>#",
                "#<^v^^>#",
                "######.#"
            )
            should("Part One") {
                val expected = 18
                ru.timakden.aoc.year2022.Day24.part1(input) shouldBe expected
            }
            should("Part Two") {
                val expected = 54
                ru.timakden.aoc.year2022.Day24.part2(input) shouldBe expected
            }
        }

        should("Day 25: Full of Hot Air") {
            val input = listOf(
                "1=-0-2",
                "12111",
                "2=0=",
                "21",
                "2=01",
                "111",
                "20012",
                "112",
                "1=-1=",
                "1-12",
                "12",
                "1=",
                "122"
            )
            val expected = "2=-1=0"
            ru.timakden.aoc.year2022.Day25.part1(input) shouldBe expected
        }
    }

    context("Utils") {
        should("String.md5() generates MD5 hash as a hex string") {
            val expected = "35454b055cc325ea1af2126e27707052"
            "ILoveJava".md5() shouldBe expected
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
