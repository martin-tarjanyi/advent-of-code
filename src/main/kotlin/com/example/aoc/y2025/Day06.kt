package com.example.aoc.y2025

import com.example.aoc.inputLineSequence
import jdk.internal.org.jline.utils.Colors.s
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle
import java.io.File.separator

fun main() {
//    part1()
    part2()
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

private fun part2() {
    val input = inputLineSequence("2025/day06.txt").toList()

    val queue = ArrayDeque(input.last().toMutableList())
    val operations = mutableListOf<String>()

    while (queue.isNotEmpty()) {
        var string = ""
        var currentChar = queue.removeFirstOrNull()
        while (currentChar != null) {
            if (currentChar in setOf('*', '+')) {
                if (string.isNotEmpty()) {
                    operations.add(string)
                }
                string = currentChar.toString()
            } else {
                string += currentChar
            }
            currentChar = queue.removeFirstOrNull()
        }
        if (string.isNotEmpty()) {
            operations.add(string)
        }
    }

    operations.mapIndexed { index, op ->
        val startIndex = if (index == 0) 0 else operations.take(index).sumOf { it.length }
        val endIndex = if (index == operations.size - 1) operations.sumOf { it.length } else startIndex + op.length - 1
        val nums = (startIndex..<endIndex)
            .map { pos ->
                input.dropLast(1)
                    .map { it[pos] }
                    .joinToString(separator = "")
                    .replace(" ", "")
                    .toLong()
            }

        when (op.trim()) {
            "*" -> nums.reduce { a, b -> a * b }
            "+" -> nums.sum()
            else -> error("Unexpected operation: $op")
        }
    }.sum()
        .let { println(it) }
}
