package com.hado.jetpackkotlinfundamentals.core.scopefunctions

data class Person(val name: String, val age: Int)

fun main() {
    val john = Person(name = "John Doe", age = 32)

    // Instead off
    println("Person name: ${john.name}")
    println("Person age: ${john.age}")

    // Use with(object)
    with(john) {
        println("Person name: $name")
        println("Person age: $age")
    }
}