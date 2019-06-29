package ru.timakden.adventofcode.year2015.day21

import ru.timakden.adventofcode.PowerSet
import ru.timakden.adventofcode.measure
import ru.timakden.adventofcode.year2015.day21.ItemType.*

fun main() {
    measure {
        val items = setOf(
            // Weapons
            Weapon(cost = 8, damage = 4),
            Weapon(cost = 10, damage = 5),
            Weapon(cost = 25, damage = 6),
            Weapon(cost = 40, damage = 7),
            Weapon(cost = 74, damage = 8),

            // Armor
            Armor(cost = 13, armor = 1),
            Armor(cost = 31, armor = 2),
            Armor(cost = 53, armor = 3),
            Armor(cost = 75, armor = 4),
            Armor(cost = 102, armor = 5),

            // Rings
            Ring(cost = 25, damage = 1),
            Ring(cost = 50, damage = 2),
            Ring(cost = 100, damage = 3),
            Ring(cost = 20, armor = 1),
            Ring(cost = 40, armor = 2),
            Ring(cost = 80, armor = 3)
        )

        val validItems = PowerSet.of(items).filter(::isValid).toMutableList()

        println("Part One: ${solvePartOne(validItems, input)}")
        println("Part Two: ${solvePartTwo(validItems, input)}")
    }
}

fun solvePartOne(itemSets: MutableList<Set<Item>>, input: Character): Int {
    itemSets.sortBy { it.sumBy(Item::cost) }
    var bossDefeated = false

    itemSets.forEach { items ->
        val boss = input.copy()
        val player = Character(100, items.sumBy(Item::damage), items.sumBy(Item::armor))
        var currentMove = 1
        while (true) {
            if (currentMove % 2 == 0) {
                boss.attack(player)
                if (player.hitpoints <= 0) break
            } else {
                player.attack(boss)
                if (boss.hitpoints <= 0) {
                    bossDefeated = true
                    break
                }
            }
            currentMove++
        }

        if (bossDefeated) return items.sumBy(Item::cost)
    }
    return 0
}

fun solvePartTwo(itemSets: MutableList<Set<Item>>, input: Character): Int {
    itemSets.sortByDescending { it.sumBy(Item::cost) }
    var playerDefeated = false

    itemSets.forEach { items ->
        val boss = input.copy()
        val player = Character(100, items.sumBy(Item::damage), items.sumBy(Item::armor))
        var currentMove = 1
        while (true) {
            if (currentMove % 2 == 0) {
                boss.attack(player)
                if (player.hitpoints <= 0) {
                    playerDefeated = true
                    break
                }
            } else {
                player.attack(boss)
                if (boss.hitpoints <= 0) break
            }
            currentMove++
        }

        if (playerDefeated) return items.sumBy(Item::cost)
    }
    return 0
}

private fun isValid(items: Set<Item>) = !(items.isEmpty() ||
        items.size > 4 ||
        items.count { it.type == WEAPON } != 1 ||
        items.count { it.type == ARMOR } > 1 ||
        items.count { it.type == RING } > 2)

abstract class Item(val type: ItemType) {
    abstract val cost: Int
    abstract val damage: Int
    abstract val armor: Int
}

class Weapon(override val cost: Int, override val damage: Int = 0, override val armor: Int = 0) : Item(WEAPON)

class Armor(override val cost: Int, override val damage: Int = 0, override val armor: Int = 0) : Item(ARMOR)

class Ring(override val cost: Int, override val damage: Int = 0, override val armor: Int = 0) : Item(RING)

data class Character(var hitpoints: Int = 0, var damage: Int = 0, var armor: Int = 0) {
    fun attack(other: Character) {
        other.hitpoints -= if (damage - other.armor < 1) 1 else damage - other.armor
    }
}

enum class ItemType { WEAPON, ARMOR, RING }
