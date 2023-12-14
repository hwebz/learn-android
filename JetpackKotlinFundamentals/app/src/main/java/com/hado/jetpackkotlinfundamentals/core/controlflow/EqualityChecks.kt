package com.hado.jetpackkotlinfundamentals.core.controlflow

fun main() {
    val authors = setOf<String>("Shakespeare", "Hemingway", "Twain")
    val writers = setOf<String>("Twain", "Shakespeare", "Hemingway")

    println(authors == writers)
    println(authors === writers)
}