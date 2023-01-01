package ru.timakden.aoc.year2015

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.time.ExperimentalTime

object Day22 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val character = readInput("year2015/Day22")
                .map { it.substringAfter(": ") }
                .map { it.toInt() }
                .let { Boss(it.first(), it.last()) }

            println("Part One: ${solve(character)}")
            println("Part Two: ${solve(character, true)}")
        }
    }

    fun solve(input: Boss, isPartTwo: Boolean = false): Int {
        var minMana = Int.MAX_VALUE
        val boss = input.copy()
        val wizards = PriorityQueue<Wizard> { a, b -> b.manaSpent.compareTo(a.manaSpent) }
        wizards += Wizard(50, 500, boss)

        while (wizards.size > 0) {
            val currentWizard = wizards.poll()

            if (isPartTwo && --currentWizard.hitpoints <= 0) continue

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
                            wizards += nextWizard
                        }
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
        private var activeEffects = IntArray(3)

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
                } else if (it == 0) armor = 0
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
}
