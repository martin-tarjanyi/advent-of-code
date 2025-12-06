package com.example.aoc.y2025

import com.example.aoc.inputLineSequence
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val dirAndMove = inputLineSequence("2025/day01.txt")
        .map {
            it[0] to it.substring(1).toLong()
        }
        .toList()

    part1(dirAndMove)
    part2(dirAndMove)
}

private fun part1(dirAndMove: List<Pair<Char, Long>>) {
    var numberOfZeroPositions = 0
    var currentPosition: Long = 50

    dirAndMove.forEach { (dir, move) ->
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

private fun part2(dirAndMove: List<Pair<Char, Long>>) {
    var numberOfZeroPositions: Long = 0
    var currentPosition: Long = 50

    dirAndMove.forEach { (dir, move) ->
        val previousPosition = currentPosition
        when (dir) {
            'R' -> currentPosition += move
            'L' -> currentPosition -= move
            else -> error("Unexpected dir: $dir")
        }
        val offset = max(abs(currentPosition), abs(previousPosition)) * 100

        val normalizedPrevious = offset + previousPosition
        val normalizedCurrent = offset + currentPosition
        var zeroPositions = abs(normalizedPrevious / 100 - normalizedCurrent / 100)
        if (previousPosition % 100 == 0L && previousPosition > currentPosition) {
            zeroPositions--
        }
        if (currentPosition % 100 == 0L && previousPosition > currentPosition) {
            zeroPositions++
        }
        numberOfZeroPositions += zeroPositions
    }

    println(numberOfZeroPositions)
}
