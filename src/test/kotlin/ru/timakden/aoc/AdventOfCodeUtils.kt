package ru.timakden.aoc

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import ru.timakden.aoc.util.*

class AdventOfCodeUtils : FunSpec({
    context("Utils") {
        test("String.md5() generates MD5 hash as a hex string") {
            val expected = "35454b055cc325ea1af2126e27707052"
            "ILoveJava".md5() shouldBe expected
        }

        context("String.isNumber() parses numbers in a string") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    "123" to true,
                    "abc" to false,
                    "123.45" to true
                )
            ) { (input, expected) ->
                input.isNumber() shouldBe expected
            }
        }

        context("String.isLetter() parses letters in a string") {
            withData(
                nameFn = { "input = ${it.first}, expected = ${it.second}" },
                ts = listOf(
                    "abc" to true,
                    "123" to false,
                    "ab12" to false
                )
            ) { (input, expected) ->
                input.isLetter() shouldBe expected
            }
        }

        test("Permutations.of() generates all permutations of a list") {
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

        test("PowerSet.of() returns the set of all possible subsets") {
            val input = setOf(1, 2, 3, 4)
            val powerSet = PowerSet(input)
            val expectedSize = 1 shl input.size
            powerSet.toList().size shouldBe expectedSize
        }
    }
})
