package com.hado.jetpackkotlinfundamentals.core.introduction

fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun multiply(x: Int, y: Int) = x * y

fun main() {
    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    println(sum(1, 2))
    println(multiply(2, 4))

    // Infix functions
//    infix fun Int.times(str: String) = str.repeat(this)
//    println(2 times "Bye ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia

    // Operator Functions
    operator fun Int.times(str: String) = str.repeat(this)
    println(2 * "Bye ")

    operator fun String.get(range: IntRange) = substring(range)
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])

    // Functions with vararg parameters
    printAll("Hello", "Hallo", "Salut", "Hola")
    printAllWithPrefix(
        "Hello", "Hallo", "Salut", "Hola",
        prefix = "Greeting: "
    )
    log("Hello", "Hallo", "Salut", "Hola")
}

// Infix Functions
class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }
}

// Functions with vararg parameters
fun printAll(vararg messages: String) {
    for (m in messages) println(m)
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {
    for (m in messages) println(prefix + m)
}

fun log(vararg entries: String) {
    printAll(*entries)
}