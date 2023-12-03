package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.year2022.*

class AdventOfCode2022Test : FunSpec({
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
            test("Part One") {
                val expected = 24000
                Day01.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 45000
                Day01.part2(input) shouldBe expected
            }
        }

        context("Day 2: Rock Paper Scissors") {
            val input = listOf("A Y", "B X", "C Z")
            test("Part One") {
                val expected = 15
                Day02.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 12
                Day02.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 157
                Day03.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 70
                Day03.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 2
                Day04.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 4
                Day04.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = "CMZ"
                Day05.part1(stacks, input) shouldBe expected
            }
            test("Part Two") {
                val expected = "MCD"
                Day05.part2(stacks, input) shouldBe expected
            }
        }

        context("Day 6: Tuning Trouble") {
            context("Part One") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
                        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
                        "nppdvjthqldpwncqszvftbrmjlhg" to 6,
                        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
                        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11
                    )
                ) { (input, expected) ->
                    Day06.part1(input) shouldBe expected
                }
            }
            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
                        "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
                        "nppdvjthqldpwncqszvftbrmjlhg" to 23,
                        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
                        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26
                    )
                ) { (input, expected) ->
                    Day06.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 95437
                Day07.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 24933642
                Day07.part2(input) shouldBe expected
            }
        }

        context("Day 8: Treetop Tree House") {
            val input = listOf("30373", "25512", "65332", "33549", "35390")
            test("Part One") {
                val expected = 21
                Day08.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 8
                Day08.part2(input) shouldBe expected
            }
        }

        context("Day 9: Rope Bridge") {
            test("Part One") {
                val input = listOf("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2")
                val expected = 13
                Day09.part1(input) shouldBe expected
            }

            context("Part Two") {
                withData(
                    nameFn = { "input = ${it.first}, expected = ${it.second}" },
                    ts = listOf(
                        listOf("R 4", "U 4", "L 3", "D 1", "R 4", "D 1", "L 5", "R 2") to 1,
                        listOf("R 5", "U 8", "L 8", "D 3", "R 17", "D 10", "L 25", "U 20") to 36
                    )

                ) { (input, expected) ->
                    Day09.part2(input) shouldBe expected
                }
            }
        }

        test("Day 10: Cathode-Ray Tube") {
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
            Day10.part1(input) shouldBe expected
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
            test("Part One") {
                val expected = 10605
                Day11.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 2713310158
                Day11.part2(input) shouldBe expected
            }
        }

        context("Day 12: Hill Climbing Algorithm") {
            val input = listOf("Sabqponm", "abcryxxl", "accszExk", "acctuvwj", "abdefghi")
            test("Part One") {
                val expected = 31
                Day12.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 29
                Day12.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 13
                Day13.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 140
                Day13.part2(input) shouldBe expected
            }
        }

        context("Day 14: Regolith Reservoir") {
            val input = listOf(
                "498,4 -> 498,6 -> 496,6",
                "503,4 -> 502,4 -> 502,9 -> 494,9"
            )
            test("Part One") {
                val expected = 24
                Day14.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 93
                Day14.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 26
                Day15.part1(input, 10) shouldBe expected
            }
            test("Part Two") {
                val expected = 56000011
                Day15.part2(input, 20) shouldBe expected
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
            test("Part One") {
                val expected = 1651
                Day16.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 1707
                Day16.part2(input) shouldBe expected
            }
        }

        context("Day 17: Pyroclastic Flow") {
            val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
            test("Part One") {
                val expected = 3068
                Day17.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 1514285714288
                Day17.part2(input, 1000000000000L) shouldBe expected
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
            test("Part One") {
                val expected = 64
                Day18.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 58
                Day18.part2(input) shouldBe expected
            }
        }

        context("Day 19: Not Enough Minerals") {
            val input = listOf(
                "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.",
                "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian."
            )
            test("Part One") {
                val expected = 33
                Day19.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 3472
                Day19.part2(input) shouldBe expected
            }
        }

        context("Day 20: Grove Positioning System") {
            val input = listOf("1", "2", "-3", "3", "-2", "0", "4")
            test("Part One") {
                val expected = 3
                Day20.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 1623178306
                Day20.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 152
                Day21.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 301
                Day21.part2(input) shouldBe expected
            }
        }

        test("Day 22: Monkey Map") {
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
            Day22.part1(input) shouldBe expected
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
            test("Part One") {
                val expected = 110
                Day23.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 20
                Day23.part2(input) shouldBe expected
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
            test("Part One") {
                val expected = 18
                Day24.part1(input) shouldBe expected
            }
            test("Part Two") {
                val expected = 54
                Day24.part2(input) shouldBe expected
            }
        }

        test("Day 25: Full of Hot Air") {
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
            Day25.part1(input) shouldBe expected
        }
    }
})
