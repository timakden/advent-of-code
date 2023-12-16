package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.util.*

class AdventOfCodeUtilsSpec : FunSpec({
    context("Utils") {
        context("String") {
            context("md5") {
                withData(
                    nameFn = { "${it.first}.md5() = ${it.second}" },
                    ts = listOf(
                        "ILoveJava" to "35454b055cc325ea1af2126e27707052",
                        "HelloWorld" to "68e109f0f40ca72a15e05cc22786f8e6",
                        "KotlinRocks" to "5d67a4d16ea2e6f23290a2a5d83e5402",
                        "Testing123" to "ac1c8d64fd23ae5a7eac5b7f7ffee1fa",
                        "AdventOfCode" to "ef160064bbb759c70e4cdc237ccc474b"
                    )
                ) { (input, expected) ->
                    input.md5() shouldBe expected
                }
            }

            context("isNumber") {
                withData(
                    nameFn = { "${it.first}.isNumber() = ${it.second}" },
                    ts = listOf(
                        "123" to true,
                        "abc" to false,
                        "123.45" to true,
                        "1a3" to false,
                        "" to false
                    )
                ) { (input, expected) ->
                    input.isNumber() shouldBe expected
                }
            }

            context("isLetter") {
                withData(
                    nameFn = { "${it.first}.isLetter() = ${it.second}" },
                    ts = listOf(
                        "abc" to true,
                        "123" to false,
                        "ab12" to false,
                        "ABCD" to true,
                        "abc2" to false
                    )
                ) { (input, expected) ->
                    input.isLetter() shouldBe expected
                }
            }
        }

        test("Permutations.of() generates all permutations of a list") {
            val input = listOf(1, 2, 3, 4)
            val expected = listOf(
                listOf(1, 2, 3, 4),
                listOf(1, 2, 4, 3),
                listOf(1, 3, 2, 4),
                listOf(1, 3, 4, 2),
                listOf(1, 4, 2, 3),
                listOf(1, 4, 3, 2),
                listOf(2, 1, 3, 4),
                listOf(2, 1, 4, 3),
                listOf(2, 3, 1, 4),
                listOf(2, 3, 4, 1),
                listOf(2, 4, 1, 3),
                listOf(2, 4, 3, 1),
                listOf(3, 1, 2, 4),
                listOf(3, 1, 4, 2),
                listOf(3, 2, 1, 4),
                listOf(3, 2, 4, 1),
                listOf(3, 4, 1, 2),
                listOf(3, 4, 2, 1),
                listOf(4, 1, 2, 3),
                listOf(4, 1, 3, 2),
                listOf(4, 2, 1, 3),
                listOf(4, 2, 3, 1),
                listOf(4, 3, 1, 2),
                listOf(4, 3, 2, 1)
            )
            Permutations.of(input).toList().map { it.toList() } shouldContainExactlyInAnyOrder expected
        }

        test("PowerSet.of() returns the set of all possible subsets") {
            val input = setOf(1, 2, 3, 4, 5)
            val powerSet = PowerSet(input)
            val expectedSize = 1 shl input.size
            powerSet.toList().size shouldBe expectedSize
        }

        context("GCD") {
            withData(
                nameFn = { "gcd(${it.first}, ${it.second}) = ${it.third}" },
                ts = listOf(
                    Triple(2L, 4L, 2L),
                    Triple(5L, 10L, 5L),
                    Triple(24L, 36L, 12L),
                    Triple(21L, 42L, 21L),
                    Triple(13L, 17L, 1L),
                    Triple(36L, 48L, 12L),
                    Triple(101L, 103L, 1L)
                )
            ) { (x, y, expected) ->
                gcd(x, y) shouldBe expected
            }
        }

        context("LCM") {
            withData(
                nameFn = { "lcm(${it.first}, ${it.second}) = ${it.third}" },
                ts = listOf(
                    Triple(3L, 4L, 12L),
                    Triple(5L, 15L, 15L),
                    Triple(5L, 7L, 35L),
                    Triple(6L, 21L, 42L),
                    Triple(8L, 9L, 72L),
                    Triple(12L, 15L, 60L),
                    Triple(17L, 19L, 323L)
                )
            ) { (x, y, expected) ->
                lcm(x, y) shouldBe expected
            }
        }

        context("Point") {
            context("move") {
                withData(
                    nameFn = { "p1 = ${it.first}, p2 = ${it.second}, expected = ${it.third}" },
                    listOf(
                        Triple(Point(1, 1), Point(2, 2), Point(3, 3)),
                        Triple(Point(3, 4), Point(-1, -2), Point(2, 2)),
                        Triple(Point(5, -5), Point(-5, 5), Point(0, 0)),
                        Triple(Point(6, -7), Point(3, 3), Point(9, -4)),
                        Triple(Point(8, 2), Point(-2, -5), Point(6, -3))
                    )
                ) { (p1, p2, expected) ->
                    p1.move(p2) shouldBe expected
                }
            }

            context("isInPolygon") {
                withData(
                    nameFn = { "point = ${it.first}, polygon = ${it.second}, expected = ${it.third}" },
                    ts = listOf(
                        Triple(Point(1, 1), listOf(Point(0, 0), Point(2, 0), Point(1, 2)), true),
                        Triple(Point(3, 1), listOf(Point(0, 0), Point(2, 0), Point(1, 2)), false),
                        Triple(Point(1, 1), listOf(Point(0, 0), Point(2, 0), Point(2, 2), Point(0, 2)), true),
                        Triple(Point(3, 1), listOf(Point(0, 0), Point(2, 0), Point(2, 2), Point(0, 2)), false),
                        Triple(Point(2, 2), listOf(Point(1, 1), Point(3, 1), Point(3, 3), Point(1, 3)), true),
                        Triple(Point(0, 0), listOf(Point(1, 1), Point(3, 1), Point(3, 3), Point(1, 3)), false)
                    )
                ) { (point, polygon, expected) ->
                    point.isInPolygon(polygon) shouldBe expected
                }
            }

            context("manhattanDistanceTo") {
                withData(
                    nameFn = { "p1 = ${it.first}, p2 = ${it.second}, expected = ${it.third}" },
                    ts = listOf(
                        Triple(Point(1, 1), Point(2, 2), 2),
                        Triple(Point(3, 4), Point(-1, -2), 10),
                        Triple(Point(5, -5), Point(5, -5), 0),
                        Triple(Point(6, -7), Point(3, 3), 13),
                        Triple(Point(8, 2), Point(-2, -5), 17)
                    )
                ) { (p1, p2, expected) ->
                    p1.manhattanDistanceTo(p2) shouldBe expected
                }
            }
        }
    }
})
