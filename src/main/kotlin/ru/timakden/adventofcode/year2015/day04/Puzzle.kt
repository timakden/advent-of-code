package ru.timakden.adventofcode.year2015.day04

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(input, 27)}")
        println("Part Two: ${solve(input, 26)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(input: String, length: Int): Long {
    var count = 0L
    var encoded: String
    val messageDigest = MessageDigest.getInstance("MD5")

    while (true) {
        messageDigest.update((input + count.toString()).toByteArray())
        encoded = BigInteger(1, messageDigest.digest()).toString(16)
        if (encoded.length == length) {
            break
        }
        count++
    }

    return count
}
