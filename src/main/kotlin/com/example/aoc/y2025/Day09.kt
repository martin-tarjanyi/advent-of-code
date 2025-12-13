package com.example.aoc.y2025

import com.example.aoc.Position
import com.example.aoc.inputLineSequence
import kotlin.math.abs

fun main() {
    part1()
//    part2()
}

private fun part1() {
    val tiles = inputLineSequence("2025/day09.txt")
        .map { it ->
            val (x, y) = it.split(",").map { it.toLong() }
            Position(y, x)
        }
        .toList()

    val oppositeCornerCandidates = tiles.flatMapIndexed { index, pos ->
        tiles.subList(index, tiles.size).map { it to pos }
    }

    val biggestRectangleCorners = oppositeCornerCandidates
        .maxBy { (pos1, pos2) -> rectangleArea(pos1, pos2) }

    println(rectangleArea(biggestRectangleCorners.first, biggestRectangleCorners.second))
}

private fun rectangleArea(pos1: Position, pos2: Position): Long {
    val height = abs(pos1.row - pos2.row) + 1
    val width = abs(pos1.column - pos2.column) + 1
    return height * width
}
