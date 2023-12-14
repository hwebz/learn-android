package com.hado.jetpackkotlinfundamentals.core.introduction

fun main() {
    var a: String = "initial"
    println(a)

    // val is immutable
    val b: Int = 1
    val c = 3

    // var is mutable
    var e: Int
    e = 2
    println(e)

    // variable must be initialized before the first read
    val d: Int
    if (true) {
        d = 1
    } else {
        d = 2
    }
    println(d)

    // Data Types:
    // Byte, Short, Int, Long
    // UByte, UShort, UInt, ULong
    // Float, Double
    // Boolean
    // String
    // Char
}