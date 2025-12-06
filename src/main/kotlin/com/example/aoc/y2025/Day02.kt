package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    inputLineSequence("2025/day02.txt")
        .flatMap { it.split(',') }
        .map { it.split('-').let { it[0].toLong() to it[1].toLong() } }
        .flatMap { (start, end) -> (start..end)
            .filter {
//                it.toString().isInvalidPart1()
                it.toString().isInvalidPart2()
            }
        }
        .sum()
        .let { println(it) }
}

private fun String.isInvalidPart1(): Boolean {
    if (length % 2 == 1) {
        return false
    }
    val sequenceLength = length / 2
    return substring(0, sequenceLength) == substring(sequenceLength)
}

private fun String.isInvalidPart2(): Boolean =
    (1..length / 2)
        .filter { length % it == 0 }
        .any { sequenceLength ->
            this == this.substring(0, sequenceLength).repeat(length / sequenceLength)
        }
