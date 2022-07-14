package ru.timakden.aoc.util

/**
 * Creates the set of all possible subsets of this set. For example, `PowerSet(setOf(1, 2))`
 * returns the set `{{}, {1}, {2}, {1, 2}}`.
 *
 * https://www.baeldung.com/java-power-set-of-a-set
 */
class PowerSet<E>(private val set: Set<E>) : AbstractSet<Set<E>>() {
    private val map: MutableMap<E, Int> = mutableMapOf()
    private val reverseMap: MutableList<E> = mutableListOf()

    init {
        initializeMap()
    }

    private abstract class ListIterator<K>(private val size: Int) : Iterator<K> {
        protected var position = 0
        override fun hasNext() = position < size
    }

    private class Subset<E>(
        private val map: Map<E, Int>,
        private val reverseMap: List<E>,
        private val mask: Int
    ) : AbstractSet<E>() {
        override fun iterator(): Iterator<E> {
            return object : Iterator<E> {
                var remainingSetBits = mask

                override fun hasNext() = remainingSetBits != 0

                override fun next(): E {
                    val index = Integer.numberOfTrailingZeros(remainingSetBits)
                    if (index == 32) throw NoSuchElementException()
                    remainingSetBits = remainingSetBits and (1 shl index).inv()
                    return reverseMap[index]
                }
            }
        }

        override val size: Int
            get() = Integer.bitCount(mask)

        override fun contains(element: E): Boolean {
            val index = map[element]
            return index != null && mask and (1 shl index) != 0
        }
    }

    override fun iterator(): Iterator<Set<E>> {
        return object : ListIterator<Set<E>>(size) {
            override fun next() = Subset(map, reverseMap, position++)
        }
    }

    override val size: Int
        get() = 1 shl set.size

    override fun contains(element: Set<E>): Boolean {
        return reverseMap.containsAll(element)
    }

    override fun equals(other: Any?): Boolean {
        if (other is PowerSet<*>) {
            return set == other.set // Set equals check to have the same element regardless of the order of the items
        }
        return super.equals(other)
    }

    private fun initializeMap() {
        for ((mapId, c) in set.withIndex()) {
            map[c] = mapId
            reverseMap.add(c)
        }
    }
}
