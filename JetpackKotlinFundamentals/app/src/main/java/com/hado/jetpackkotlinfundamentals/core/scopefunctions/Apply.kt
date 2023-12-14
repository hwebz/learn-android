package com.hado.jetpackkotlinfundamentals.core.scopefunctions

data class Person2(var name: String, var age: Int, var about: String) {
    constructor() : this("", 0, "")
}

fun main() {
    val john = Person2()
    val stringDescription = john.apply {
        name = "Jack Doe"
        age = 30
        about = "Android developer"
    }.toString()

    println(stringDescription)
}