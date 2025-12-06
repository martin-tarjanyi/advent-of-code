package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
//    part1()
    part2()
}

private fun part1() {
    val input = inputLineSequence("2025/day05.txt").toList()

    val freshRanges =
        input.takeWhile { it.contains("-") }
            .map {
                val (start, end) = it.split("-")
                start.toLong()..end.toLong()
            }

    val ids = input
        .dropWhile { it.contains("-") || it.isBlank() }
        .map { it.toLong() }

    ids.count { id -> freshRanges.any { id in it } }
        .also { println(it) }
}

private fun part2() {
    val input = inputLineSequence("2025/day05.txt").toList()

    val freshRanges =
        input.takeWhile { it.contains("-") }
            .map {
                val (start, end) = it.split("-")
                start.toLong()..end.toLong()
            }
            .sortedBy { it.first }

    val newRanges = mutableListOf<LongRange>().apply {
        add(freshRanges.first())
    }

    freshRanges.drop(1).forEachIndexed { index, range ->
        val lastRange = newRanges.last()
        if (range.first in lastRange) {
            newRanges[newRanges.size - 1] = lastRange.first..maxOf(lastRange.last, range.last)
        } else {
            newRanges.add(range)
        }
    }

    println(newRanges.sumOf { it.last - it.first + 1 })
}
