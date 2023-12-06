package ru.timakden.aoc.year2023

import ru.timakden.aoc.util.measure
import ru.timakden.aoc.util.readInput
import kotlin.math.max
import kotlin.math.min

object Day05 {
    @JvmStatic
    fun main(args: Array<String>) {
        measure {
            val input = readInput("year2023/Day05")
            println("Part One: ${part1(input)}")
            println("Part Two: ${part2(input)}")
        }
    }

    fun part1(input: List<String>): Long {
        val seeds = mutableListOf<Long>()
        val seedToSoilMap = mutableMapOf<Range, Range>()
        val soilToFertilizerMap = mutableMapOf<Range, Range>()
        val fertilizerToWaterMap = mutableMapOf<Range, Range>()
        val waterToLightMap = mutableMapOf<Range, Range>()
        val lightToTemperatureMap = mutableMapOf<Range, Range>()
        val temperatureToHumidityMap = mutableMapOf<Range, Range>()
        val humidityToLocationMap = mutableMapOf<Range, Range>()
        val seedToLocationMap = mutableMapOf<Long, Long>()
        var currentMap = mutableMapOf<Range, Range>()
        val digits = "\\d+".toRegex()

        for (s in input) {
            if (s.startsWith("seeds:")) {
                seeds += s.substringAfter("seeds: ").split(" ").map { it.toLong() }
                continue
            }

            when {
                s.startsWith("seed-to-soil map:") -> {
                    currentMap = seedToSoilMap
                    continue
                }

                s.startsWith("soil-to-fertilizer map:") -> {
                    currentMap = soilToFertilizerMap
                    continue
                }

                s.startsWith("fertilizer-to-water map:") -> {
                    currentMap = fertilizerToWaterMap
                    continue
                }

                s.startsWith("water-to-light map:") -> {
                    currentMap = waterToLightMap
                    continue
                }

                s.startsWith("light-to-temperature map:") -> {
                    currentMap = lightToTemperatureMap
                    continue
                }

                s.startsWith("temperature-to-humidity map:") -> {
                    currentMap = temperatureToHumidityMap
                    continue
                }

                s.startsWith("humidity-to-location map:") -> {
                    currentMap = humidityToLocationMap
                    continue
                }
            }

            if (digits.containsMatchIn(s)) {
                val (destinationStart, sourceStart, length) = s.split(" ").map { it.toLong() }
                val destinationRange = Range(destinationStart, destinationStart + length - 1)
                val sourceRange = Range(sourceStart, sourceStart + length - 1)
                currentMap[sourceRange] = destinationRange
            }
        }

        seeds.forEach { seed ->
            val soil = getDestination(seed, seedToSoilMap)
            val fertilizer = getDestination(soil, soilToFertilizerMap)
            val water = getDestination(fertilizer, fertilizerToWaterMap)
            val light = getDestination(water, waterToLightMap)
            val temperature = getDestination(light, lightToTemperatureMap)
            val humidity = getDestination(temperature, temperatureToHumidityMap)
            val location = getDestination(humidity, humidityToLocationMap)
            seedToLocationMap[seed] = location
        }

        return seedToLocationMap.values.min()
    }

    fun part2(input: List<String>): Long {
        val seedRanges = mutableListOf<Range>()
        val seedToSoilMap = mutableMapOf<Range, Range>()
        val soilToFertilizerMap = mutableMapOf<Range, Range>()
        val fertilizerToWaterMap = mutableMapOf<Range, Range>()
        val waterToLightMap = mutableMapOf<Range, Range>()
        val lightToTemperatureMap = mutableMapOf<Range, Range>()
        val temperatureToHumidityMap = mutableMapOf<Range, Range>()
        val humidityToLocationMap = mutableMapOf<Range, Range>()
        var currentMap = mutableMapOf<Range, Range>()
        val digits = "\\d+".toRegex()

        for (s in input) {
            if (s.startsWith("seeds:")) {
                val seeds = s.substringAfter("seeds: ").split(" ").map { it.toLong() }
                for (i in 0..seeds.lastIndex step 2) {
                    seedRanges += Range(seeds[i], seeds[i] + seeds[i + 1] - 1)
                }
                continue
            }

            when {
                s.startsWith("seed-to-soil map:") -> {
                    currentMap = seedToSoilMap
                    continue
                }

                s.startsWith("soil-to-fertilizer map:") -> {
                    currentMap = soilToFertilizerMap
                    continue
                }

                s.startsWith("fertilizer-to-water map:") -> {
                    currentMap = fertilizerToWaterMap
                    continue
                }

                s.startsWith("water-to-light map:") -> {
                    currentMap = waterToLightMap
                    continue
                }

                s.startsWith("light-to-temperature map:") -> {
                    currentMap = lightToTemperatureMap
                    continue
                }

                s.startsWith("temperature-to-humidity map:") -> {
                    currentMap = temperatureToHumidityMap
                    continue
                }

                s.startsWith("humidity-to-location map:") -> {
                    currentMap = humidityToLocationMap
                    continue
                }
            }

            if (digits.containsMatchIn(s)) {
                val (destinationStart, sourceStart, length) = s.split(" ").map { it.toLong() }
                val destinationRange = Range(destinationStart, destinationStart + length - 1)
                val sourceRange = Range(sourceStart, sourceStart + length - 1)
                currentMap[sourceRange] = destinationRange
            }
        }

        val locations = seedRanges.flatMap { seedRange ->
            val soil = getDestinations(seedRange, seedToSoilMap)
            val fertilizer = soil.flatMap { getDestinations(it, soilToFertilizerMap) }
            val water = fertilizer.flatMap { getDestinations(it, fertilizerToWaterMap) }
            val light = water.flatMap { getDestinations(it, waterToLightMap) }
            val temperature = light.flatMap { getDestinations(it, lightToTemperatureMap) }
            val humidity = temperature.flatMap { getDestinations(it, temperatureToHumidityMap) }
            val location = humidity.flatMap { getDestinations(it, humidityToLocationMap) }

            location
        }

        return locations.minOf { it.first }
    }

    data class Range(val first: Long, val last: Long)

    private fun getDestination(source: Long, map: Map<Range, Range>): Long {
        return map.filterKeys { source >= it.first && source <= it.last }.map { it.key to it.value }.singleOrNull()
            ?.let { (sourceRange, destinationRange) ->
                destinationRange.first + (source - sourceRange.first)
            } ?: source
    }

    private fun getDestinations(source: Range, map: Map<Range, Range>): List<Range> {
        val ranges = sortedMapOf<Range, Range>(Comparator.comparingLong { it.first })
        map.entries.forEach { entry ->
            val first = max(entry.key.first, source.first)
            val last = min(entry.key.last, source.last)

            if (first <= last) {
                val range = Range(
                    first = entry.value.first + (first - entry.key.first),
                    last = entry.value.last - (entry.key.last - last)
                )
                ranges[Range(first, last)] = range
            }
        }

        if (ranges.isEmpty()) return listOf(source)

        var currentRange = source
        val additionalRanges = mutableListOf<Range>()

        ranges.forEach { (sourceRange, _) ->
            if (currentRange.first < sourceRange.first) {
                additionalRanges += Range(currentRange.first, sourceRange.first - 1)
                currentRange = Range(sourceRange.last + 1, currentRange.last)
            }
        }

        if (currentRange.first <= currentRange.last) additionalRanges += currentRange

        additionalRanges.forEach { ranges.putIfAbsent(it, it) }

        return ranges.values.toList()
    }
}
