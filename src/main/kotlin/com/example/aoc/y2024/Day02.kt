package com.example.aoc.y2024

import com.example.aoc.inputLineSequence
import kotlin.math.absoluteValue

fun main() {
    part1()
    part2()
}

private fun part1() {
    inputLineSequence("2024/day02.txt")
        .count { line ->
            val numbers = line.split("\\s+".toRegex()).map { it.toLong() }
            val diffs = numbers.zipWithNext { a, b -> b - a }

            val dampenedNumbers = numbers
                .indices
                .map { i -> numbers.toMutableList().also { it.removeAt(i) } }

            val dampenedDiffs = dampenedNumbers.map { it.zipWithNext { a, b -> b - a } }

            (listOf(
                diffs,
                diffs.toMutableList().also { it.remove(0L) }
            ) + dampenedDiffs)
                .any { candidate ->
                    candidate.all { it in 1..3 } || candidate.all { it in -3..-1 }
                }

        }
        .also { println(it) }
}

private fun part2() {
}
