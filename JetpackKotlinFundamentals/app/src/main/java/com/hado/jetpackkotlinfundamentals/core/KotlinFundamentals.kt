package com.hado.jetpackkotlinfundamentals.core

import kotlin.random.Random

fun main() {
    // 1. Variables

    val popcorn = 5
    val hotdog = 7
    // mutable variable
    var customers = 10
    customers = 15

    // String templates
    println("There are $customers customers, but only has ${popcorn + 1} popcorn and $hotdog hotdog")

    // 2. Basic Types
    // Byte, Short, Int, Long
    // UByte, UShort, UInt, ULong
    // Float, Double
    // Boolean
    // Char
    // String
    customers = customers + 3
    customers += 7
    customers -= 3
    customers *= 2
    customers /= 3
    customers %= 3

    println("Cusomters $customers")

    // Variable declared without initialization
    val dv: Int
    // Variable initialized
    dv = 3

    // Variable explicitly typed and initialized
    val ev: String = "hello"

    // Variables can be read because they have been initialized
    println(dv) // 3
    println(ev) // hello

    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'

    // 3. Collections: Lists, Sets, Maps
    // Read-only list
    val readOnlyShapes = listOf<String>("triangle", "square", "circle")
    println(readOnlyShapes)

    // Mutable list with explicit type declaration
    val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
    println(shapes)
    // locked mutable list by using casting
    val shapesLocked: List<String> = shapes
    println("The first item in the list is: ${readOnlyShapes[0]}")
    println("The first item in the list is: ${readOnlyShapes.first()}")
    println("The last item in the list is: ${readOnlyShapes.last()}")
    println("Total number of items in the list is: ${readOnlyShapes.count()}")

    // Add "pentagon" to the list
    shapes.add("pentagon")
    shapes.add("pentagon")
    println(shapes)

    // Remove the first "pentagon" from the list
    shapes.remove("pentagon")
    // Remove list of items from the list
    shapes.removeAll(listOf("pentagon"))
    println(shapes)

    // Set
    // Read-only set
    val readOnlyFruit = setOf<String>("apple", "banana", "cherry", "cherry")
    // Mutable set with explicit type declaration
    val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "cherry", "cherry")
    // locked mutable set by using casting
    val fruitLocked: Set<String> = fruit

    println(readOnlyShapes)
    println("This set has ${readOnlyFruit.count()} items")
    println("Does banana is in the list? ${"banana" in readOnlyFruit}")

    // Adding to the set
    fruit.add("dragonfruit")
    println(fruit)

    // Remove from the set
    fruit.remove("dragonfruit")
    println(fruit)

    // Map
    // Read-only map
    val readOnlyJuiceMenu = mapOf<String, Int>("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(readOnlyJuiceMenu)

    // Mutable map with explicit type declaration
    val juiceMenu: MutableMap<String, Int> = mutableMapOf<String, Int>("apple" to 100, "kiwi" to 190, "orange" to 100)
    println(juiceMenu)

    // locked mutable map by using casting
    val lockedJuiceMenu: Map<String, Int> = juiceMenu

    println("The price of apple juice is: $${readOnlyJuiceMenu["apple"]}")
    println("Total items in the menu: ${readOnlyJuiceMenu.count()}")

    // Adding to the map
    juiceMenu.put("coconut", 150)
    println(juiceMenu)

    // Remove from the map
    juiceMenu.remove("orange")
    println(juiceMenu)

    // Check key exists?
    println("Does kiwi in the map? ${juiceMenu.containsKey("kiwi")}")

    // Fetching all keys, values from a map
    println("Keys in map: ${readOnlyJuiceMenu.keys}")
    println("Does orange in map? ${"orange" in readOnlyJuiceMenu.keys}")
    println("Values in map: ${readOnlyJuiceMenu.values}")
    println("$190 is in map? ${190 in readOnlyJuiceMenu.values}")

    // 4. Conditional Expressions
    val da: Int
    val check = true

    // If Else
    if (check) {
        da = 1
    } else {
        da = 2
    }
    println(da)
    println(if (check) 1 else 2)

    // When
    val obj = "Hello"
    when (obj) {
        // Checks whether obj equals to "1"
        "1" -> println("One")
        // Checks whether obj equals to "Hello"
        "Hello" -> println("Greeting")
        // Default statement
        else -> println("Unknown")
    }
    val result = when (obj) {
        // Checks whether obj equals to "1"
        "1" -> "One"
        // Checks whether obj equals to "Hello"
        "Hello" -> "Greeting"
        // Default statement
        else -> "Unknown"
    }
    println(result)

    val temperature = 18
    val description = when {
        temperature < 0 -> "very cold"
        temperature < 10 -> "a bit cold"
        temperature < 20 -> "warm"
        else -> "hot"
    }
    println(description)

    // Ranges
    val first = 1..4
    val second = 1..<4
    val third = 4 downTo 1
    val fourth = 1..5 step 2
    val fifth = 'a'..'d'
    val sixth = 'z' downTo 's'
    println("$first, $second, $third, $fourth, $fifth, $sixth")

    // Loops
    // For
    for (number in first) {
        print(number)
    }

    for (shape in readOnlyShapes) {
        println("Wow, it's $shape")
    }

    // While
    var cakesEaten = 0
    while (cakesEaten < 3) {
        println("Eat a cake")
        cakesEaten++
    }

    var cakesBaked = 0
    do {
        println("Bake a cake")
        cakesBaked++
    } while (cakesBaked < cakesEaten)

    // 5. Functions
    hello()
    println(sum(1, 2))
    printMessageWithPrefix("Hello", "Log")
    // Uses named arguments with swapped parameter order
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    printMessageWithPrefix("Hello")

    // Lambda expressions
    println({ string: String -> string.uppercase() }("hello"))
    val upperCaseString = { string: String -> string.uppercase() }
    println(upperCaseString("welcome"))

    val numbers = listOf<Byte>(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 }
    val negatives = numbers.filter { x -> x < 0 }
    val doubled = numbers.map { x -> x * 2 }
    val tripled = numbers.map { x -> x * 3 }
    println(positives)
    println(negatives)
    println(doubled)
    println(tripled)

    val lowerCaseString: (String) -> String = { string -> string.lowercase() }
    println(lowerCaseString("WELCOME"))

    val timesInMinutes = listOf<Int>(2, 10, 15, 1)
    val min2sec = toSeconds("minute")
    val totalTimeInSeconds = timesInMinutes.map(min2sec).sum()
    println("Total time is $totalTimeInSeconds secs")

    // Trailing lambdas
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item }))
    println(listOf(1, 2, 3).fold(0) { x, item -> x + item })

    // 6. Classes
    val contact = Contact(1, "test@gmail.com")
    println(contact.email)
    contact.email = "test2@gmail.com"
    println(contact.email)
    contact.printId()

    // Data classes
    val user = User("Alex", 1)
    val secondUser = User("Alex", 1)
    val thirdUser = User("Max", 2)
    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")
    println("user.copy(\"Max\") = ${user.copy("Max")}")
    println("user.copy(id = 3) = ${user.copy(age = 3)}")

    var userGen = RandomUserGenerator(10, 20)
    println(userGen.generateUser())
    println(userGen.generateUser())
    println(userGen.generateUser())
    userGen.minAge = 20
    userGen.maxAge = 100
    println(userGen.generateUser())

    // 7. Null Safety
    // neverNull has String type and throws error
    var neverNull: String = "This can't be null"
//    neverNull = null

    // nullable has nullable String type
    var nullable: String? = "You can keep a null here"
    nullable = null

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    println(strLength(neverNull))
//    println(strLength(nullable)) // Throws error

    var nullString: String? = null
    println(describeString(nullString))
    println(lengthString(nullString))
    println(nullString?.uppercase())
    // Elvis operator
    println(nullString?.length ?: 0)
}

fun lengthString(maybeString: String?): Int? = maybeString?.length

fun describeString(maybeString: String?): String {
    if (maybeString != null && maybeString.length > 0) {
        return "String of length ${maybeString.length}"
    } else {
        return "Empty or null string"
    }
}

fun hello() {
    return println("Hello, world!")
}

//fun sum(x: Int, y: Int): Int {
//    return x + y
//}
fun sum(x: Int, y: Int) = x + y

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { value -> value * 60 * 60 }
    "minute" -> { value -> value * 60}
    "second" -> { value -> value }
    else -> { value -> value }
}

// val is immutable, can't re-assign
// var is mutable, can re-assign
class Contact(val id: Int, var email: String = "example@gmail.com") {
    val category: String = "work"

    fun printId() {
        println(id)
    }
}

data class User(val name: String, val age: Int)

// final class by default, open for inheritable
open class RandomUserGenerator(var minAge: Int, var maxAge: Int) {
    val names = listOf<String>("John", "Mary", "Ann", "Paul", "Jack", "Elizabeth")

    fun generateUser() = User(names.random(), Random.nextInt(from = minAge, until = maxAge))
}