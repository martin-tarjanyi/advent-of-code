package com.example.aoc.y2025

import com.example.aoc.inputLineSequence
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    part1()
//    part2()
}

private fun part1() {
    val closestPairsToTake = 1000
    val junctionBoxes = inputLineSequence("2025/day08.txt")
        .map { it ->
            val (x, y, z) = it.split(",").map { it.toDouble() }
            JunctionBox(x, y, z)
        }
        .toList()

    val boxPairs = junctionBoxes.flatMapIndexed { index, box ->
        junctionBoxes.subList(index + 1, junctionBoxes.size).map { it to box }
    }

    val circuits = junctionBoxes.map { Circuit(mutableSetOf(it)) }.toMutableList()

    boxPairs.sortedBy { (a, b) -> a.distanceTo(b) }
        .take(closestPairsToTake)
        .forEach { (a, b) ->
            val circuitA = circuits.find { a in it.boxes }
            val circuitB = circuits.find { b in it.boxes }

            if (circuitA == circuitB) {
                // nothing to do
            } else if (circuitA == null && circuitB == null) {
                circuits.add(Circuit(mutableSetOf(a, b)))
            } else if (circuitA != null && circuitB == null) {
                circuitA.boxes.add(b)
            } else if (circuitA == null && circuitB != null) {
                circuitB.boxes.add(a)
            } else if (circuitA != null && circuitB != null) {
                circuitA.boxes.addAll(circuitB.boxes)
                circuitB.boxes.clear()
                circuits.removeIf { it.boxes.isEmpty() }
            } else {
                error("Unexpected sate")
            }
        }

    circuits.sortedByDescending { it.boxes.size }
        .take(3)
        .fold(1) { acc, circuit -> acc * circuit.boxes.size }
        .let { println(it) }
}

private data class JunctionBox(val x: Double, val y: Double, val z: Double) {
    fun distanceTo(other: JunctionBox): Double =
        ((other.x - this.x).pow(2) + (other.y - this.y).pow(2) + (other.z - this.z).pow(2))
            .let { sqrt(it) }
}

private data class Circuit(
    val boxes: MutableSet<JunctionBox>
)
