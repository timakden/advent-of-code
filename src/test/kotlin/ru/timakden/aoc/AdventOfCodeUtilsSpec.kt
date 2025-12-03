package ru.timakden.aoc

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.tuple
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.util.*

class AdventOfCodeUtilsSpec : FunSpec({
    context("Utils") {
        context("String") {
            context("md5") {
                withData(
                    nameFn = { "${it.a}.md5() = ${it.b}" },
                    ts = listOf(
                        tuple("ILoveJava", "35454b055cc325ea1af2126e27707052"),
                        tuple("HelloWorld", "68e109f0f40ca72a15e05cc22786f8e6"),
                        tuple("KotlinRocks", "5d67a4d16ea2e6f23290a2a5d83e5402"),
                        tuple("Testing123", "ac1c8d64fd23ae5a7eac5b7f7ffee1fa"),
                        tuple("AdventOfCode", "ef160064bbb759c70e4cdc237ccc474b")
                    )
                ) { (input, expected) ->
                    input.md5() shouldBe expected
                }
            }

            context("isNumber") {
                withData(
                    nameFn = { "${it.a}.isNumber() = ${it.b}" },
                    ts = listOf(
                        tuple("123", true),
                        tuple("abc", false),
                        tuple("123.45", true),
                        tuple("1a3", false),
                        tuple("", false)
                    )
                ) { (input, expected) ->
                    input.isNumber() shouldBe expected
                }
            }

            context("isLetter") {
                withData(
                    nameFn = { "${it.a}.isLetter() = ${it.b}" },
                    ts = listOf(
                        tuple("abc", true),
                        tuple("123", false),
                        tuple("ab12", false),
                        tuple("ABCD", true),
                        tuple("abc2", false)
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
                nameFn = { "gcd(${it.a}, ${it.b}) = ${it.c}" },
                ts = listOf(
                    tuple(2L, 4L, 2L),
                    tuple(5L, 10L, 5L),
                    tuple(24L, 36L, 12L),
                    tuple(21L, 42L, 21L),
                    tuple(13L, 17L, 1L),
                    tuple(36L, 48L, 12L),
                    tuple(101L, 103L, 1L)
                )
            ) { (x, y, expected) ->
                gcd(x, y) shouldBe expected
            }
        }

        context("LCM") {
            withData(
                nameFn = { "lcm(${it.a}, ${it.b}) = ${it.c}" },
                ts = listOf(
                    tuple(3L, 4L, 12L),
                    tuple(5L, 15L, 15L),
                    tuple(5L, 7L, 35L),
                    tuple(6L, 21L, 42L),
                    tuple(8L, 9L, 72L),
                    tuple(12L, 15L, 60L),
                    tuple(17L, 19L, 323L)
                )
            ) { (x, y, expected) ->
                lcm(x, y) shouldBe expected
            }
        }

        context("Point") {
            context("p1 + p2") {
                withData(
                    nameFn = { "p1 = ${it.a}, p2 = ${it.b}, expected = ${it.c}" },
                    listOf(
                        tuple(Point(1, 1), Point(2, 2), Point(3, 3)),
                        tuple(Point(3, 4), Point(-1, -2), Point(2, 2)),
                        tuple(Point(5, -5), Point(-5, 5), Point(0, 0)),
                        tuple(Point(6, -7), Point(3, 3), Point(9, -4)),
                        tuple(Point(8, 2), Point(-2, -5), Point(6, -3))
                    )
                ) { (p1, p2, expected) ->
                    p1 + p2 shouldBe expected
                }
            }

            context("p1 - p2") {
                withData(
                    nameFn = { "p1 = ${it.a}, p2 = ${it.b}, expected = ${it.c}" },
                    listOf(
                        tuple(Point(1, 1), Point(2, 2), Point(-1, -1)),
                        tuple(Point(3, 4), Point(-1, -2), Point(4, 6)),
                        tuple(Point(5, -5), Point(-5, 5), Point(10, -10)),
                        tuple(Point(6, -7), Point(3, 3), Point(3, -10)),
                        tuple(Point(8, 2), Point(-2, -5), Point(10, 7))
                    )
                ) { (p1, p2, expected) ->
                    p1 - p2 shouldBe expected
                }
            }

            context("isInPolygon") {
                withData(
                    nameFn = { "point = ${it.a}, polygon = ${it.b}, expected = ${it.c}" },
                    ts = listOf(
                        tuple(Point(1, 1), Polygon(listOf(Point(0, 0), Point(2, 0), Point(1, 2))), true),
                        tuple(Point(3, 1), Polygon(listOf(Point(0, 0), Point(2, 0), Point(1, 2))), false),
                        tuple(Point(1, 1), Polygon(listOf(Point(0, 0), Point(2, 0), Point(2, 2), Point(0, 2))), true),
                        tuple(Point(3, 1), Polygon(listOf(Point(0, 0), Point(2, 0), Point(2, 2), Point(0, 2))), false),
                        tuple(Point(2, 2), Polygon(listOf(Point(1, 1), Point(3, 1), Point(3, 3), Point(1, 3))), true),
                        tuple(Point(0, 0), Polygon(listOf(Point(1, 1), Point(3, 1), Point(3, 3), Point(1, 3))), false)
                    )
                ) { (point, polygon, expected) ->
                    point.isInPolygon(polygon) shouldBe expected
                }
            }

            context("distanceTo") {
                withData(
                    nameFn = { "p1 = ${it.a}, p2 = ${it.b}, expected = ${it.c}" },
                    ts = listOf(
                        tuple(Point(1, 1), Point(2, 2), 2),
                        tuple(Point(3, 4), Point(-1, -2), 10),
                        tuple(Point(5, -5), Point(5, -5), 0),
                        tuple(Point(6, -7), Point(3, 3), 13),
                        tuple(Point(8, 2), Point(-2, -5), 17)
                    )
                ) { (p1, p2, expected) ->
                    p1.distanceTo(p2) shouldBe expected
                }
            }
        }

        context("Polygon") {
            context("area") {
                withData(
                    nameFn = { "${it.a}.area = ${it.b}" },
                    ts = listOf(
                        tuple(
                            Polygon(
                                listOf(
                                    Point(1, 6),
                                    Point(3, 1),
                                    Point(7, 2),
                                    Point(4, 4),
                                    Point(8, 5)
                                )
                            ), 16.5
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(2, 7),
                                    Point(4, 2),
                                    Point(5, 5),
                                    Point(8, 3),
                                    Point(9, 6)
                                )
                            ), 16.5
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(0, 10),
                                    Point(0, 0),
                                    Point(5, -5),
                                    Point(20, 8)
                                )
                            ), 170.0
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(0, 0),
                                    Point(2, 0),
                                    Point(2, 2),
                                    Point(0, 2)
                                )
                            ), 4.0
                        )
                    )
                ) { (polygon, expected) ->
                    polygon.area shouldBe expected
                }
            }

            context("perimeter") {
                withData(
                    nameFn = { "${it.a}.perimeter = ${it.b}" },
                    ts = listOf(
                        tuple(
                            Polygon(
                                listOf(
                                    Point(1, 1),
                                    Point(5, 1),
                                    Point(5, 5),
                                    Point(1, 5)
                                )
                            ), 16
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(0, 0),
                                    Point(3, 0),
                                    Point(3, 3),
                                    Point(0, 3)
                                )
                            ), 12
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(-1, -1),
                                    Point(1, -1),
                                    Point(1, 1),
                                    Point(-1, 1)
                                )
                            ), 8
                        ),
                        tuple(
                            Polygon(
                                listOf(
                                    Point(-2, -2),
                                    Point(2, -2),
                                    Point(2, 2),
                                    Point(-2, 2)
                                )
                            ), 16
                        )
                    )
                ) { (polygon, expected) ->
                    polygon.perimeter shouldBe expected
                }
            }
        }

        context("CircularListIterator") {
            context("empty list") {
                test("iterator.next() throws NoSuchElementException") {
                    shouldThrow<NoSuchElementException> { emptyList<Int>().circularListIterator().next() }
                }
                test("iterator.hasNext() returns false") {
                    emptyList<Int>().circularListIterator().hasNext() shouldBe false
                }
                test("iterator.nextIndex() returns 0") {
                    emptyList<Int>().circularListIterator().nextIndex() shouldBe 0
                }
                test("iterator.previous() throws NoSuchElementException") {
                    shouldThrow<NoSuchElementException> { emptyList<Int>().circularListIterator().previous() }
                }
                test("iterator.hasPrevious() returns false") {
                    emptyList<Int>().circularListIterator().hasPrevious() shouldBe false
                }
                test("iterator.previousIndex() returns -1") {
                    emptyList<Int>().circularListIterator().previousIndex() shouldBe -1
                }
            }

            context("non-empty list") {
                test("iterator.next() returns the next element") {
                    listOf(1, 2, 3).circularListIterator().next() shouldBe 1
                }
                test("iterator.next() iterates over the list") {
                    listOf(1, 2, 3).circularListIterator(initialIndex = 3).next() shouldBe 1
                }
                test("iterator.hasNext() returns true") {
                    listOf(1, 2, 3).circularListIterator().hasNext() shouldBe true
                }
                test("iterator.nextIndex() returns the next index") {
                    listOf(1, 2, 3).circularListIterator().nextIndex() shouldBe 0
                }
                test("iterator.previous() returns the previous element") {
                    listOf(1, 2, 3).circularListIterator(initialIndex = 1).previous() shouldBe 1
                }
                test("iterator.previous() iterates over the list") {
                    listOf(1, 2, 3).circularListIterator().previous() shouldBe 3
                }
                test("iterator.hasPrevious() returns true") {
                    listOf(1, 2, 3).circularListIterator().hasPrevious() shouldBe true
                }
                test("iterator.previousIndex() returns the previous index") {
                    listOf(1, 2, 3).circularListIterator().previousIndex() shouldBe 2
                }
            }
        }
    }
})
