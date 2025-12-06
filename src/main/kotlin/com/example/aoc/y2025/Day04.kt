package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
//    part1()
    part2()
}

private fun part1() {
    val grid = inputLineSequence("2025/day04.txt")
        .map { it.toMutableList() }
        .toList()

    grid.mapIndexed { rowIndex, row ->
        row.filterIndexed { cellIndex, cell ->
            if (cell == '@') {
                val adjacentPapers = ((rowIndex - 1)..(rowIndex + 1))
                    .flatMap { i -> ((cellIndex - 1)..(cellIndex + 1)).map { i to it } }
                    .count { (i, j) -> grid.getOrNull(i)?.getOrNull(j) == '@' } - 1 // subtract self
                adjacentPapers < 4
            } else {
                false
            }
        }.count()
    }.sum()
        .let { println(it) }
}


private fun part2() {
    val currentGrid = inputLineSequence("2025/day04.txt")
        .map { it.toMutableList() }
        .toMutableList()

    var removablePaperCount = 0

    while (true) {
        val removablePapers = currentGrid.flatMapIndexed { rowIndex, row ->
            row.mapIndexedNotNull { cellIndex, cell ->
                if (cell == '.') {
                    return@mapIndexedNotNull null
                }

                val adjacentPapers = ((rowIndex - 1)..(rowIndex + 1))
                    .flatMap { i -> ((cellIndex - 1)..(cellIndex + 1)).map { i to it } }
                    .count { (i, j) -> currentGrid.getOrNull(i)?.getOrNull(j) == '@' } - 1 // subtract self

                if (adjacentPapers < 4) {
                    return@mapIndexedNotNull rowIndex to cellIndex
                }

                return@mapIndexedNotNull null
            }
        }

        if (removablePapers.isEmpty()) {
            break
        }

        removablePaperCount += removablePapers.size
        removablePapers.forEach { (i, j) ->
            currentGrid[i][j] = '.'
        }

//        println("Removed papers: ${removablePapers.size}")
//        println("Grid:")
//        currentGrid.forEach { row ->
//            println(row.joinToString(separator = ""))
//        }
//        println()
    }

    println(removablePaperCount)
}
