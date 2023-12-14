package com.hado.jetpackkotlinfundamentals.core.scopefunctions

fun getNullableLength(ns: String?): Int {
    println("for \"$ns\":")
    val length = ns?.run {
        println("\tis empty? " + isEmpty())
        println("\tlength = $length")
        length
    }

    return length ?: 0
}



fun main() {
    getNullableLength(null)
    getNullableLength("")
    val b = getNullableLength("some string with Kotlin")
    println(b)
}