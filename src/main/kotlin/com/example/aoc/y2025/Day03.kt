package com.example.aoc.y2025

import com.example.aoc.inputLineSequence
import kotlin.text.indexOf

fun main() {
//    part1()
    part2()
}

private fun part1() {
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

private fun part2() {
    inputLineSequence("2025/day03.txt")
        .map { largestNumber(it).toLong() }
        .sum()
        .let { println(it) }
}

fun largestNumber(num: String, digits: Int = 12): String {
    if (num.isEmpty() || digits == 0) {
        return ""
    }

    val maxIndex = num.length - digits
    val bestDigit = num.take(maxIndex + 1).map { it.digitToInt() }.max()
    val bestIndex = num.indexOf(bestDigit.digitToChar())

    return bestDigit.digitToChar() + largestNumber(num.drop(bestIndex + 1), digits = digits - 1)
}
