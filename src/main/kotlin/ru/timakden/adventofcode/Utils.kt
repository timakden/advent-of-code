package ru.timakden.adventofcode

import java.security.MessageDigest
import java.util.*
import javax.xml.bind.DatatypeConverter
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
    }).toLowerCase()

/** Returns `true` if this string matches the regular expression `\d+(?:\.\d+)?`. */
fun String.isNumber() = this.matches("\\d+(?:\\.\\d+)?".toRegex())

/** Returns `true` if this string matches the regular expression `[a-zA-Z]+`. */
fun String.isLetter() = this.matches("[a-zA-Z]+".toRegex())

object Permutations {
    /**
     * Creates a [Sequence] of all possible permutations of the given collection of [items].
     *
     * Original Java implementation can be found [here][https://gist.github.com/jaycobbcruz/cbabc1eb49f51bfe2ed1db10a06a2b26].
     */
    fun <T> of(items: Collection<T>) = (0 until factorial(items.size)).asSequence()
        .map { permutation(it, items).asSequence() }

    private fun <T> permutation(count: Int, items: Collection<T>) = permutationHelper(count, LinkedList(items))

    private fun factorial(num: Int) = generateSequence(1) { it.inc() }.take(num).fold(1) { v1, v2 -> v1 * v2 }

    private tailrec fun <T> permutationHelper(
        count: Int,
        input: LinkedList<T>,
        output: MutableList<T> = mutableListOf()
    ): List<T> = if (input.isEmpty()) {
        output
    } else {
        val factorial = factorial(input.size - 1)
        output.add(input.removeAt(count / factorial))
        permutationHelper(count % factorial, input, output)
    }
}

/**
 * Executes the given [block] and prints elapsed time in milliseconds.
 */
@ExperimentalTime
fun measure(block: () -> Unit) {
    val duration = measureTime(block)
    println("Elapsed time: $duration")
}

object PowerSet {
    /**
     * Creates the set of all possible subsets of this set. For example,
     * `PowerSet.of(setOf(1, 2))` returns the set `{{}, {1}, {2}, {1, 2}}`.
     */
    fun <T> of(set: Set<T>): Sequence<Set<T>> {
        return sequence {
            val list = set.toList()
            val numberOfSubsets = (1L shl list.size) - 1
            var currentSubsetIndex = 0L
            while (currentSubsetIndex <= numberOfSubsets) {
                val subset = HashSet<T>()
                val bitSet = BitSet.valueOf(longArrayOf(currentSubsetIndex))
                // get the index of truth values.
                var bit = bitSet.nextSetBit(0)
                while (bit != -1) {
                    subset.add(list[bit])
                    bit = bitSet.nextSetBit(bit + 1)
                }
                currentSubsetIndex++
                yield(subset)
            }
        }
    }
}

object Constants {
    enum class Part { PART_ONE, PART_TWO }
}
