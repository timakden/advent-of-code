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

fun <T> List<T>.powerSet(): List<List<T>> {
    val lists = mutableListOf<List<T>>()
    if (isEmpty()) {
        lists.add(listOf<T>())
        return lists
    }

    val head = get(0)
    val rest = mutableListOf<T>()
    subList(1, size).forEach { rest.add(it) }

    rest.powerSet().forEach {
        val newList = mutableListOf<T>()
        newList.add(head)
        newList.addAll(it)
        lists.add(newList)
        lists.add(it)
    }

    return lists
}
