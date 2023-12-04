package ru.timakden.aoc.util

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.time.measureTime

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Returns `true` if this string matches the regular expression `\d+(?:\.\d+)?`.
 */
fun String.isNumber() = this.matches("\\d+(?:\\.\\d+)?".toRegex())

/**
 * Returns `true` if this string matches the regular expression `[a-zA-Z]+`.
 */
fun String.isLetter() = this.matches("[a-zA-Z]+".toRegex())

/**
 * Executes the given [block] and prints elapsed time.
 */
fun measure(block: () -> Unit) = println("Elapsed time: ${measureTime(block)}")

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/main/kotlin/ru/timakden/aoc/$name.txt").readLines()

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
