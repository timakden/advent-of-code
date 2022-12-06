package ru.timakden.aoc.util

import jakarta.xml.bind.DatatypeConverter
import java.security.MessageDigest
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Calculates the MD5 digest and returns the value as a 32 character hex string.
 *
 * @param data data to digest
 *
 * @return MD5 digest as a hex string
 */
fun md5Hex(data: String) = DatatypeConverter.printHexBinary(
    MessageDigest.getInstance("MD5").run {
        update(data.toByteArray())
        digest()
    }).lowercase(Locale.getDefault())

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

object Constants {
    enum class Part { PART_ONE, PART_TWO }
}
