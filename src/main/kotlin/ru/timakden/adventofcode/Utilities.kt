package ru.timakden.adventofcode

fun <T> MutableList<T>.permutations(): List<List<T>> {
    if (size == 0) return listOf(emptyList<T>())

    val returnMe = mutableListOf<List<T>>()

    val firstElement = removeAt(0)

    val recursiveReturn = permutations()
    recursiveReturn.forEach { list ->
        (0..list.size).forEach {
            returnMe.add(list.toMutableList().apply { add(it, firstElement) })
        }
    }

    return returnMe
}
