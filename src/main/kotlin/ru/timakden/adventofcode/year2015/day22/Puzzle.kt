package ru.timakden.adventofcode.year2015.day22

import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        println("Part One: ${solve(false)}")
        println("Part Two: ${solve(true)}")
    }
    println("Elapsed time: $elapsedTime ms")
}

fun solve(partTwo: Boolean): Int {
    var minMana = Int.MAX_VALUE
    val boss = Boss(71, 10)
    val wizards = PriorityQueue<Wizard> { a, b -> Integer.compare(b.manaSpent, a.manaSpent) }
    wizards.add(Wizard(50, 500, boss))

    while (wizards.size > 0) {
        val currentWizard = wizards.poll()

        if (partTwo) {
            if (currentWizard.hitpoints-- <= 0)
                continue
        }

        currentWizard.applyEffect()

        Wizard.spells.forEach {
            if (currentWizard.canCast(it)) {
                val nextWizard = currentWizard.clone()
                nextWizard.castSpell(it)
                nextWizard.applyEffect()

                if (nextWizard.boss.hitpoints <= 0) {
                    minMana = Math.min(minMana, nextWizard.manaSpent)
                    wizards.removeAll { it.manaSpent > minMana }
                } else {
                    nextWizard.hitpoints -= Math.max(1, nextWizard.boss.damage - nextWizard.armor)
                    if (nextWizard.hitpoints > 0 && nextWizard.mana > 0 && nextWizard.manaSpent < minMana)
                        wizards.add(nextWizard)
                }
            }
        }
    }
    return minMana
}

data class Boss(var hitpoints: Int, var damage: Int) : Cloneable {
    public override fun clone(): Boss {
        return Boss(hitpoints, damage)
    }
}

data class Wizard(var hitpoints: Int, var mana: Int, var boss: Boss) : Cloneable {
    var armor: Int = 0
    var manaSpent: Int = 0
    var activeEffects = IntArray(3)

    companion object {
        var spells = listOf(Spell(53, 0), Spell(73, 0), Spell(113, 6), Spell(173, 6), Spell(229, 5))
    }

    fun canCast(spell: Spell): Boolean {
        val spellIndex = spells.indexOf(spell)
        return mana >= spell.cost && (spellIndex < 2 || activeEffects[spellIndex - 2] == 0)
    }

    fun castSpell(spell: Spell) {
        mana -= spell.cost
        manaSpent += spell.cost

        val spellIndex = spells.indexOf(spell)
        when (spellIndex) {
            0 -> boss.hitpoints -= 4
            1 -> {
                hitpoints += 2
                boss.hitpoints -= 2
            }
            else -> activeEffects[spellIndex - 2] = spell.turns
        }
    }

    fun applyEffect() {
        activeEffects.indices.forEach {
            if (activeEffects[it] > 0) {
                activeEffects[it]--
                when (it) {
                    0 -> armor = 7
                    1 -> boss.hitpoints -= 3
                    2 -> mana += 101
                }
            } else if (it == 0)
                armor = 0
        }
    }

    public override fun clone(): Wizard {
        val newWizard = Wizard(hitpoints, mana, boss.clone())
        newWizard.armor = armor
        newWizard.manaSpent = manaSpent
        newWizard.activeEffects = activeEffects.clone()
        return newWizard
    }
}

class Spell(var cost: Int, var turns: Int)
