package ru.timakden.aoc.util

/**
 * A circular iterator for a list that enables cycling through the elements
 * infinitely in both forward and backward directions.
 *
 * @param T The type of elements in the list.
 * @property list The list over which the iterator operates.
 * @property initialIndex The initial position for the iterator within the list.
 * Defaults to 0. If the list is empty, the initial index is set to -1.
 */
class CircularListIterator<T>(private val list: List<T>, initialIndex: Int = 0) : ListIterator<T> {
    private var currentIndex = if (list.isEmpty()) -1 else (initialIndex % list.size + list.size) % list.size

    override fun hasNext(): Boolean = list.isNotEmpty()

    override fun next(): T {
        if (list.isEmpty()) throw NoSuchElementException()
        val element = list[currentIndex]
        currentIndex = (currentIndex + 1) % list.size
        return element
    }

    override fun hasPrevious(): Boolean = list.isNotEmpty()

    override fun previous(): T {
        if (list.isEmpty()) throw NoSuchElementException()
        currentIndex = (currentIndex - 1 + list.size) % list.size
        return list[currentIndex]
    }

    override fun nextIndex(): Int {
        if (list.isEmpty()) return 0
        return currentIndex
    }

    override fun previousIndex(): Int {
        if (list.isEmpty()) return -1
        return (currentIndex - 1 + list.size) % list.size
    }
}

// Extension function to easily create a circular list iterator
fun <T> List<T>.circularListIterator(initialIndex: Int = 0) = CircularListIterator(this, initialIndex)
