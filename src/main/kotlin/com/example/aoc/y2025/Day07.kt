package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    part1()
//    part2()
}

private fun part1() {
    val manifold = inputLineSequence("2025/day07.txt")
        .map { it.toList() }
        .toList()

    val startBeam = (0 to (manifold.first().indexOf('S').also { check(it != -1) }))
        .let { Beam(it.first, it.second) }
    var currentBeams = setOf(startBeam)
    var splits = 0L

    while (currentBeams.isNotEmpty()) {
        val newBeams = mutableSetOf<Beam>()
        currentBeams.toSet().forEach { beam ->
            val cell = manifold.getOrNull(beam.row + 1)?.getOrNull(beam.column)
            when (cell) {
                '^' -> {
                    newBeams.add(Beam(beam.row + 1, beam.column - 1))
                    newBeams.add(Beam(beam.row + 1, beam.column + 1))
                    splits++
                }
                '.' -> newBeams.add(Beam(beam.row + 1, beam.column))
                null -> {}
                else -> error("Unexpected cell: $cell")
            }
        }
        currentBeams = newBeams
    }

    println(splits)
}


private data class Beam(
    val row: Int,
    val column: Int,
)
