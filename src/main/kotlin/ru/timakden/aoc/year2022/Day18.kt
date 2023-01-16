package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day18.VoxelWorld.Voxel
import kotlin.time.ExperimentalTime

object Day18 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day18")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val world = generateWorld(input)
        return world.solidVoxels.sumOf { cur -> 6 - cur.neighbors.mapNotNull(world.voxels::get).count { it } }
    }

    fun part2(input: List<String>): Int {
        val world = generateWorld(input)
        val hollowInside = world.hollowGroups().filterNot { it.value }.flatMap { it.key }.toSet()
        val solids = world.solidVoxels.toSet()
        return solids.sumOf { it.neighbors.filterNot(hollowInside::contains).filterNot(solids::contains).count() }
    }

    private fun generateWorld(input: List<String>): VoxelWorld {
        val w = input.map { line ->
            val (x, y, z) = line.split(',').map { it.toInt() }
            Voxel(x, y, z)
        }.associateWith { true }.let { VoxelWorld().apply { voxels.putAll(it) } }
        for (x in w.voxels.keys.minOf(Voxel::x)..w.voxels.keys.maxOf(Voxel::x)) {
            for (y in w.voxels.keys.minOf(Voxel::y)..w.voxels.keys.maxOf(Voxel::y)) {
                for (z in w.voxels.keys.minOf(Voxel::z)..w.voxels.keys.maxOf(Voxel::z)) {
                    if (Voxel(x, y, z) !in w.voxels) w.voxels[Voxel(x, y, z)] = false
                }
            }
        }
        return w
    }

    class VoxelWorld {
        data class Voxel(val x: Int, val y: Int, val z: Int) {
            private val left: Voxel get() = copy(x = x - 1)
            private val right: Voxel get() = copy(x = x + 1)
            private val up: Voxel get() = copy(y = y + 1)
            private val down: Voxel get() = copy(y = y - 1)
            private val front: Voxel get() = copy(z = z + 1)
            private val behind: Voxel get() = copy(z = z - 1)

            val neighbors get() = sequenceOf(left, right, up, down, front, behind)
        }

        val voxels = hashMapOf<Voxel, Boolean>()

        fun hollowGroups(): Map<Set<Voxel>, Boolean> {
            val hollowGroups = hashMapOf<Set<Voxel>, Boolean>()
            val globalQueue = hollowVoxels.toHashSet()
            while (globalQueue.isNotEmpty()) {
                val group = hashSetOf<Voxel>()
                val fst = globalQueue.first()
                globalQueue.remove(fst)
                group.add(fst)
                val localQueue = ArrayDeque(group)
                var outside = false
                while (localQueue.isNotEmpty()) {
                    for (neighbor in localQueue.removeFirst().neighbors) {
                        if (voxels[neighbor] == null) outside = true
                        else if (voxels[neighbor] == false && !group.contains(neighbor)) {
                            group.add(neighbor)
                            globalQueue.remove(neighbor)
                            localQueue.add(neighbor)
                        }
                    }
                }
                hollowGroups[group] = outside
            }
            return hollowGroups
        }

        val solidVoxels
            get() = voxels
                .filter { (_, solid) -> solid }
                .map { (voxel, _) -> voxel }
        private val hollowVoxels
            get() = voxels
                .filter { (_, solid) -> !solid }
                .map { (voxel, _) -> voxel }
    }
}
