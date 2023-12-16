package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.PowerSet
import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2015.Day21.Item.*

/**
 * [Day 21: RPG Simulator 20XX](https://adventofcode.com/2015/day/21).
 */
object Day21 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val (hitPoints, damage, armor) = readInput("year2015/Day21")
                .map { it.substringAfter(": ") }
                .map { it.toInt() }
            val character = Character(hitPoints, damage, armor)

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

            val validItems = PowerSet(items).filter(::isValid).toMutableList()

            println("Part One: ${part1(validItems, character)}")
            println("Part Two: ${part2(validItems, character)}")
        }
    }

    fun part1(itemSets: MutableList<Set<Item>>, input: Character): Int {
        itemSets.sortBy { it.sumOf(Item::cost) }
        var bossDefeated = false

        itemSets.forEach { items ->
            val boss = input.copy()
            val player = Character(100, items.sumOf(Item::damage), items.sumOf(Item::armor))
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

            if (bossDefeated) return items.sumOf(Item::cost)
        }
        return 0
    }

    fun part2(itemSets: MutableList<Set<Item>>, input: Character): Int {
        itemSets.sortByDescending { it.sumOf(Item::cost) }
        var playerDefeated = false

        itemSets.forEach { items ->
            val boss = input.copy()
            val player = Character(100, items.sumOf(Item::damage), items.sumOf(Item::armor))
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

            if (playerDefeated) return items.sumOf(Item::cost)
        }
        return 0
    }

    private fun isValid(items: Set<Item>) = !(items.isEmpty() ||
            items.size > 4 ||
            items.count { it.type == ItemType.WEAPON } != 1 ||
            items.count { it.type == ItemType.ARMOR } > 1 ||
            items.count { it.type == ItemType.RING } > 2)

    sealed class Item(val type: ItemType) {
        abstract val cost: Int
        abstract val damage: Int
        abstract val armor: Int

        class Weapon(
            override val cost: Int,
            override val damage: Int = 0,
            override val armor: Int = 0
        ) : Item(ItemType.WEAPON)

        class Armor(
            override val cost: Int,
            override val damage: Int = 0,
            override val armor: Int = 0
        ) : Item(ItemType.ARMOR)

        class Ring(
            override val cost: Int,
            override val damage: Int = 0,
            override val armor: Int = 0
        ) : Item(ItemType.RING)
    }

    data class Character(var hitpoints: Int = 0, var damage: Int = 0, var armor: Int = 0) {
        fun attack(other: Character) {
            other.hitpoints -= if (damage - other.armor < 1) 1 else damage - other.armor
        }
    }

    enum class ItemType { WEAPON, ARMOR, RING }
}
