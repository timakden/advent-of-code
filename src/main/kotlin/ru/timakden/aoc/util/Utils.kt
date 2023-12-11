package ru.timakden.aoc.util

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs
import kotlin.time.measureTime

/**
 * Generates the MD5 hash of a string.
 *
 * @return the MD5 hash as a hexadecimal string
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Determines whether a string is a number or not.
 *
 * @return `true` if the string is a number, `false` otherwise
 */
fun String.isNumber() = this.matches("\\d+(?:\\.\\d+)?".toRegex())

/**
 * Determines whether a string consists only of letters (a-z or A-Z).
 *
 * @return `true` if the string consists only of letters, `false` otherwise
 */
fun String.isLetter() = this.matches("[a-zA-Z]+".toRegex())

/**
 * Measures the elapsed time taken to execute the given block of code.
 * Prints the elapsed time in milliseconds.
 *
 * @param block the block of code to measure the elapsed time for
 */
fun measure(block: () -> Unit) = println("Elapsed time: ${measureTime(block)}")

/**
 * Reads the contents of a text file.
 *
 * @param name the name of the file to read, excluding the file extension
 * @return a list of strings representing the lines of the file
 */
fun readInput(name: String) = Path("src/main/kotlin/ru/timakden/aoc/$name.txt").readLines()

/**
 * Prints the string representation of the given object followed by a newline to the standard output stream.
 */
fun Any?.println() = println(this)

/**
 * Calculates the greatest common divisor (GCD) of two numbers.
 *
 * @param a The first number.
 * @param b The second number.
 * @return The GCD of the two numbers.
 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

/**
 * Calculates the least common multiple (LCM) of two numbers.
 *
 * @param a The first number.
 * @param b The second number.
 * @return The LCM of the two numbers.
 */
fun lcm(a: Long, b: Long) = a / gcd(a, b) * b

/**
 * Calculates the Manhattan distance between two points.
 *
 * The Manhattan distance is the sum of the absolute differences of the x-coordinates and y-coordinates
 * of two points in a grid-like pattern.
 *
 * @param p1 the coordinates of the first point as a Pair of Ints (x, y)
 * @param p2 the coordinates of the second point as a Pair of Ints (x, y)
 * @return the Manhattan distance between the two points
 */
fun manhattanDistance(p1: Pair<Int, Int>, p2: Pair<Int, Int>) = abs(p1.first - p2.first) + abs(p1.second - p2.second)
