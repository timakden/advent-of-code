package ru.timakden.aoc.util

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Calculates the MD5 digest and returns the value as a 32 character hex string.
 *
 * @return MD5 digest as a hex string
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/** Returns `true` if this string matches the regular expression `\d+(?:\.\d+)?`. */
fun String.isNumber() = this.matches("\\d+(?:\\.\\d+)?".toRegex())

/** Returns `true` if this string matches the regular expression `[a-zA-Z]+`. */
fun String.isLetter() = this.matches("[a-zA-Z]+".toRegex())

/**
 * Executes the given [block] and prints elapsed time in milliseconds.
 */
@ExperimentalTime
fun measure(block: () -> Unit) {
    val duration = measureTime(block)
    println("Elapsed time: $duration")
}

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/kotlin/ru/timakden/aoc", "$name.txt").readLines()

object Constants {
    enum class Part { PART_ONE, PART_TWO }
}
