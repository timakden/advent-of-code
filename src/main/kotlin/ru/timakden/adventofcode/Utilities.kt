package ru.timakden.adventofcode

fun <T> listPermutations(list: MutableList<T>): List<List<T>> {
    if (list.size == 0) {
        val result = mutableListOf<List<T>>()
        result.add(mutableListOf<T>())
        return result
    }

    val returnMe = mutableListOf<List<T>>()
    val firstElement = list.removeAt(0)

    val recursiveReturn = listPermutations(list)
    recursiveReturn.forEach { li ->
        (0..li.size).forEach { index ->
            val temp = li.toMutableList()
            temp.add(index, firstElement)
            returnMe.add(temp)
        }
    }

    return returnMe
}
