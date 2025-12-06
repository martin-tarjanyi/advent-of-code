package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    part1()
//    part2()
}

private fun part1() {
    val input = inputLineSequence("2025/day06.txt")
        .map { it.trim().split("\\s+".toRegex()) }
        .toList()

    input.last().mapIndexed { index, op ->
        when (op) {
            "*" -> input.dropLast(1).map { it[index].toLong() }.reduce { a, b -> a * b }
            "+" -> input.dropLast(1).sumOf { it[index].toLong() }
            else -> error("Unexpected operation: $op")
        }
    }.sum()
        .let { println(it) }
}
