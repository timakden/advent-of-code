package ru.timakden.adventofcode.year2015.day22

import ru.timakden.adventofcode.Constants
import ru.timakden.adventofcode.Constants.Part.PART_ONE
import ru.timakden.adventofcode.Constants.Part.PART_TWO
import ru.timakden.adventofcode.measure
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {
    measure {
        println("Part One: ${solve(PART_ONE)}")
        println("Part Two: ${solve(PART_TWO)}")
    }
}

fun solve(part: Constants.Part): Int {
    var minMana = Int.MAX_VALUE
    val boss = Boss(71, 10)
    val wizards = PriorityQueue<Wizard> { a, b -> b.manaSpent.compareTo(a.manaSpent) }
    wizards.add(Wizard(50, 500, boss))

    while (wizards.size > 0) {
        val currentWizard = wizards.poll()

        if (part == PART_TWO && currentWizard.hitpoints-- <= 0) continue

        currentWizard.applyEffect()

        Wizard.spells.forEach { spell ->
            if (currentWizard.canCast(spell)) {
                val nextWizard = currentWizard.clone()
                nextWizard.castSpell(spell)
                nextWizard.applyEffect()

                if (nextWizard.boss.hitpoints <= 0) {
                    minMana = min(minMana, nextWizard.manaSpent)
                    wizards.removeAll { wizard -> wizard.manaSpent > minMana }
                } else {
                    nextWizard.hitpoints -= max(1, nextWizard.boss.damage - nextWizard.armor)
                    if (nextWizard.hitpoints > 0 && nextWizard.mana > 0 && nextWizard.manaSpent < minMana) {
                        wizards.add(nextWizard)
                    }
                }
            }
        }
    }
    return minMana
}

private data class Boss(var hitpoints: Int, var damage: Int) : Cloneable {
    public override fun clone(): Boss {
        return Boss(hitpoints, damage)
    }
}

private data class Wizard(var hitpoints: Int, var mana: Int, var boss: Boss) : Cloneable {
    var armor: Int = 0
    var manaSpent: Int = 0
    var activeEffects = IntArray(3)

    companion object {
        var spells = listOf(
            Spell(53, 0),
            Spell(73, 0),
            Spell(113, 6),
            Spell(173, 6),
            Spell(229, 5)
        )
    }

    fun canCast(spell: Spell): Boolean {
        val spellIndex = spells.indexOf(spell)
        return mana >= spell.cost && (spellIndex < 2 || activeEffects[spellIndex - 2] == 0)
    }

    fun castSpell(spell: Spell) {
        mana -= spell.cost
        manaSpent += spell.cost

        when (val spellIndex = spells.indexOf(spell)) {
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
