package com.example.aoc.y2025

import com.example.aoc.inputLineSequence

fun main() {
    inputLineSequence("2025/day03.txt")
        .map {
            val nums = it.map { it.digitToInt() }
            val firstDigit = nums.subList(0, nums.size - 1).max()
            val secondDigit = nums.subList(nums.indexOf(firstDigit) + 1, nums.size).max()
            println("jolt: $firstDigit$secondDigit")
            firstDigit * 10 + secondDigit
        }
        .sum()
        .let { println(it) }
}
