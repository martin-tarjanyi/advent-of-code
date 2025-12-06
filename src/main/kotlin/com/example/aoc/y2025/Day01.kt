package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    val dirAndMove = inputLineSequence("2025/day01.txt")
        .map {
            it[0] to it.substring(1).toLong()
        }
        .toList()

    part1(dirAndMove)
}

private fun part1(dirAndMove: List<Pair<Char, Long>>) {
    var numberOfZeroPositions = 0
    var currentPosition: Long = 50

    dirAndMove.forEach { (dir, move) ->
        println("$dir$move")
        when (dir) {
            'R' -> currentPosition += move
            'L' -> currentPosition -= move
            else -> error("Unexpected dir: $dir")
        }

        if (currentPosition % 100 == 0L) {
            numberOfZeroPositions++
        }
    }

    println(numberOfZeroPositions)
}
