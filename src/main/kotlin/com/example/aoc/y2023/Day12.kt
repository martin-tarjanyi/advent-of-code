package com.example.aoc.y2023

import com.example.aoc.inputLineSequence

fun main() {
    part1()
//    val results = mutableListOf<List<Int>>()
//    (1..4).toList().combinations(combinationLength = 2, results = results)
//    println(results)
}

private fun part1() {
    inputLineSequence("2023/day12.txt")
        .sumOf { line ->
            val (conditionRecordString, groupSizesString) = line.split(' ')
            val conditionRecords = conditionRecordString.toList()
            val groupSizes = groupSizesString.split(',').map { it.toInt() }

            val unknownIndices = conditionRecords.withIndex().filter { it.value == '?' }.map { it.index }
            val knownDamaged = conditionRecords.count { it == '#' }
            val totalDamaged = groupSizes.sum()
            val unknownDamaged = (totalDamaged - knownDamaged)

            val combinations = mutableListOf<List<Int>>()
            unknownIndices.combinations(combinationLength = unknownDamaged, results = combinations)
//            combinations.forEach { println(it) }
//            println()

            combinations
                .asSequence()
                .map { indices ->
                    conditionRecords.toMutableList().apply {
                        indices.forEach { set(it, '#') }
                        (unknownIndices - indices).forEach { set(it, '.') }
                    }
                }
                .filter { records -> records.doesSatisfy(groupSizes) }
                .count()
        }
        .let { println(it) }
}

private fun List<Char>.doesSatisfy(expectedGroupSizes: List<Int>): Boolean {
    val actualGroupSizes = this.joinToString(separator = "")
        .split("""\.+""".toRegex())
        .map { it.length }
        .filter { it != 0 }

    return actualGroupSizes == expectedGroupSizes
}

private fun <E> List<E>.combinations(
    combinationLength: Int,
    results: MutableList<List<E>>,
    current: MutableList<E> = mutableListOf(),
    startPosition: Int = 0,
    totalLength: Long = combinationLength.toLong(),
) {
    if (combinationLength == 0) {
        results.add(current)
    } else {
        for (i in startPosition..(this.size - combinationLength)) {
            val index = totalLength - combinationLength
            if (index !in current.indices) {
                current.add(this[i])
            } else {
                current[index.toInt()] = this[i]
            }

            this.combinations(
                combinationLength = combinationLength - 1,
                results = results,
                current = current.toMutableList(),
                startPosition = i + 1,
                totalLength = totalLength,
            )
        }
    }
}
