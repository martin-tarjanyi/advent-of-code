package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    part1()
}

private fun part1() {
    val grid = inputLineSequence("2025/day04.txt")
        .map { it.split("") }
        .toList()

    grid.mapIndexed { rowIndex, row ->
        row.filterIndexed { cellIndex, cell ->
            if (cell == "@") {
                val adjacentPapers = ((rowIndex - 1)..(rowIndex + 1))
                    .flatMap { i -> ((cellIndex - 1)..(cellIndex + 1)).map { i to it } }
                    .count { (i, j) -> grid.getOrNull(i)?.getOrNull(j) == "@" } - 1 // subtract self
                adjacentPapers < 4
            } else {
                false
            }
        }.count()
    }.sum()
        .let { println(it) }
}
