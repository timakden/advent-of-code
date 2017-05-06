package ru.timakden.adventofcode.year2015.day04

import org.apache.commons.codec.digest.DigestUtils
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, false)}")
        println("Part Two: ${solve(input, true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: String, partTwo: Boolean): Long {
    var count = 0L
    var encoded: String

    while (true) {
        encoded = DigestUtils.md5Hex(input + count.toString())
        if (encoded.startsWith(if (partTwo) "000000" else "00000")) break
        count++
    }

    return count
}
