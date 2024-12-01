package com.example.aoc.y2024

import com.example.aoc.inputLineSequence
import kotlin.math.absoluteValue

fun main() {
    part1()
    part2()
}

private fun part1() {
    val leftList = mutableListOf<Long>()
    val rightList = mutableListOf<Long>()

    inputLineSequence("2024/day01.txt")
        .forEach {
            val (left, right) = it.split("\\s+".toRegex())
            leftList.add(left.toLong())
            rightList.add(right.toLong())
        }

    leftList.sort()
    rightList.sort()

    var sumDistance: Long = 0

    leftList.forEachIndexed { index, left ->
        sumDistance += (left - rightList[index]).absoluteValue
    }

    println(sumDistance)
}

private fun part2() {
    val leftList = mutableListOf<Long>()
    val rightList = mutableListOf<Long>()

    inputLineSequence("2024/day01.txt")
        .forEach {
            val (left, right) = it.split("\\s+".toRegex())
            leftList.add(left.toLong())
            rightList.add(right.toLong())
        }

    val frequencyInRightList = rightList.groupingBy { it }.eachCount()
    val similarityScore = leftList.sumOf { it * frequencyInRightList.getOrDefault(it, 0) }
    println(similarityScore)
}
