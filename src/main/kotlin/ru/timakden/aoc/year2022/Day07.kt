package ru.timakden.aoc.year2022

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import ru.timakden.aoc.year2022.Day07.Node.Directory
import ru.timakden.aoc.year2022.Day07.Node.File
import kotlin.time.ExperimentalTime

object Day07 {
    @JvmStatic
    @ExperimentalTime
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2022/Day07")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Int {
        val directories = mutableListOf<Directory>()
        val directoriesToCheck = ArrayDeque<Directory>().apply { add(buildTree(input)) }
        while (directoriesToCheck.isNotEmpty()) {
            val current = directoriesToCheck.removeFirst()
            if (current.size <= 100000) {
                directories.add(current)
            }
            directoriesToCheck += current.children.filterIsInstance<Directory>()
        }
        return directories.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val totalDiskSpace = 70000000
        val requiredForUpdateSpace = 30000000
        val root = buildTree(input)
        val totalUsedSpace = root.size
        val currentUnusedSpace = totalDiskSpace - totalUsedSpace
        val toBeFreedSpace = requiredForUpdateSpace - currentUnusedSpace
        val directories = mutableListOf<Directory>()

        val directoriesToCheck = ArrayDeque<Directory>().apply { add(root) }
        while (directoriesToCheck.isNotEmpty()) {
            val current = directoriesToCheck.removeFirst()
            if (current.size >= toBeFreedSpace) {
                directories.add(current)
            }
            directoriesToCheck += current.children.filterIsInstance<Directory>()
        }

        return directories.minByOrNull { it.size }?.size ?: 0
    }

    private fun buildTree(input: List<String>): Directory {
        var current = Directory("/", null)

        input.drop(1).forEach { line ->
            if (line.startsWith('$')) {
                if (line.startsWith("$ cd")) {
                    val nextDirectory = line.substringAfter("cd ")
                    current = if (nextDirectory == "..") checkNotNull(current.parent)
                    else current.children
                        .filter { it.name == nextDirectory }
                        .filterIsInstance<Directory>()
                        .firstOrNull() ?: Directory(nextDirectory, current)
                }
            } else if (line.startsWith("dir")) {
                current.children += Directory(line.substringAfter("dir "), current)
            } else if (line.isBlank()) {
                // ignore
            } else {
                val (size, name) = line.split(' ')
                current.children += File(name, size.toInt(), current)
            }
        }
        while (current.parent != null) {
            current = checkNotNull(current.parent)
        }
        return current
    }

    sealed interface Node {
        val size: Int
        val name: String
        val parent: Directory?

        data class File(
            override val name: String,
            override val size: Int,
            override val parent: Directory?
        ) : Node

        data class Directory(
            override val name: String,
            override val parent: Directory?
        ) : Node {
            val children = mutableSetOf<Node>()
            override val size: Int by lazy { children.sumOf { it.size } }
        }
    }
}
