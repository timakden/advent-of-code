package ru.timakden.adventofcode.year2015.day21

import ru.timakden.adventofcode.powerSet
import ru.timakden.adventofcode.year2015.day21.ItemType.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        // Weapons
        val dagger = Item(type = WEAPON, cost = 8, damage = 4)
        val shortsword = Item(type = WEAPON, cost = 10, damage = 5)
        val warhammer = Item(type = WEAPON, cost = 25, damage = 6)
        val longsword = Item(type = WEAPON, cost = 40, damage = 7)
        val greataxe = Item(type = WEAPON, cost = 74, damage = 8)
        val weapons = listOf(dagger, shortsword, warhammer, longsword, greataxe)

        // Armor
        val leather = Item(type = ARMOR, cost = 13, armor = 1)
        val chainmail = Item(type = ARMOR, cost = 31, armor = 2)
        val splintmail = Item(type = ARMOR, cost = 53, armor = 3)
        val bandedmail = Item(type = ARMOR, cost = 75, armor = 4)
        val platemail = Item(type = ARMOR, cost = 102, armor = 5)
        val armor = listOf(leather, chainmail, splintmail, bandedmail, platemail)

        // Rings
        val damage1 = Item(type = RING, cost = 25, damage = 1)
        val damage2 = Item(type = RING, cost = 50, damage = 2)
        val damage3 = Item(type = RING, cost = 100, damage = 3)
        val defense1 = Item(type = RING, cost = 20, armor = 1)
        val defense2 = Item(type = RING, cost = 40, armor = 2)
        val defense3 = Item(type = RING, cost = 80, armor = 3)
        val rings = listOf(damage1, damage2, damage3, defense1, defense2, defense3)

        val items = mutableListOf<Item>()
        with(items) {
            addAll(weapons)
            addAll(armor)
            addAll(rings)
        }

        val validItems = items.powerSet().filter(::isValid).toMutableList()

        println("Part One: ${solvePartOne(validItems)}")
        println("Part Two: ${solvePartTwo(validItems)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solvePartOne(items: MutableList<List<Item>>): Int {
    items.sortBy { it.sumBy(Item::cost) }
    var bossDefeated = false

    for (i in 1..items.size) {
        val boss = Character(103, 9, 2)
        val player = Character(100, items[i].sumBy(Item::damage), items[i].sumBy(Item::armor))
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

        if (bossDefeated) return items[i].sumBy(Item::cost)
    }
    return 0
}

fun solvePartTwo(items: MutableList<List<Item>>): Int {
    items.sortByDescending { it.sumBy(Item::cost) }
    var playerDefeated = false

    for (i in 1..items.size) {
        val boss = Character(103, 9, 2)
        val player = Character(100, items[i].sumBy(Item::damage), items[i].sumBy(Item::armor))
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

        if (playerDefeated) return items[i].sumBy(Item::cost)
    }
    return 0
}

fun isValid(items: List<Item>) = !(items.isEmpty() || items.size > 4 || items.count { it.type == WEAPON } != 1 ||
        items.count { it.type == ARMOR } > 1 || items.count { it.type == RING } > 2)

data class Item(val type: ItemType, val cost: Int, val damage: Int = 0, val armor: Int = 0)

data class Character(var hitpoints: Int = 0, var damage: Int = 0, var armor: Int = 0) {
    fun attack(other: Character) {
        other.hitpoints -= if (damage - other.armor < 1) 1 else damage - other.armor
    }
}

enum class ItemType { WEAPON, ARMOR, RING }
