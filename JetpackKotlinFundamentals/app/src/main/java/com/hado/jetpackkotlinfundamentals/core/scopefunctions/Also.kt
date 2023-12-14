package com.hado.jetpackkotlinfundamentals.core.scopefunctions

data class Person3(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun writeCreationLog(p: Person3) {
    println("A new person ${p.name} was created.")
}

fun main() {
    val jake = Person3("Jake", 30, "Android Developer")
        .also {
            writeCreationLog(it)
        }
    println(jake)
}