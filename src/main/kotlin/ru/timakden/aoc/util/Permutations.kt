package ru.timakden.aoc.util

import java.util.*

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
