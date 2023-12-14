package com.hado.jetpackkotlinfundamentals.core.collections

import java.lang.Math.abs

fun main() {
    // 1. filter
    val numbers = listOf<Int>(1, -2, 3, -4, 5, -6)
    val positives = numbers.filter { x -> x > 0 }
    val negatives = numbers.filter { it < 0 }

    println(positives)
    println(negatives)

    // 2. map
    val doubled = numbers.map { x -> x * 2 }
    val tripled = numbers.map { it * 3}

    println(doubled)
    println(tripled)

    // 3. any
    val anyNegative = numbers.any { it < 0}
    val anyGT6 = numbers.any { it > 6 }

    println(anyNegative)
    println(anyGT6)

    // 4. all
    val allEven = numbers.all { it % 2 == 0 }
    val allLess6 = numbers.all { it < 6 }

    println(allEven)
    println(allLess6)

    // 5. none
    val allEven2 = numbers.none { it % 2 == 1 }
    val allLess62 = numbers.none { it > 6 }

    println(allEven2)
    println(allLess62)

    // 6. find, findLast
    val words = listOf<String>("Lets", "find", "something", "in", "collection", "somehow")
    val first = words.find { it.startsWith("some") }
    val last = words.findLast { it.startsWith("some") }
    val nothing = words.find { it.contains("nothing") }

    println(first)
    println(last)
    println(nothing)

    // 7. first, last
    val firstItem = numbers.first()
    val lastItem = numbers.last()

    val firstEven = numbers.first { it % 2 == 0}
    val lastOdd = numbers.last { it % 2 != 0 }

    println(firstItem)
    println(lastItem)
    println(firstEven)
    println(lastOdd)

    // 8. firstOrNull, lastOrNull
    val empty = emptyList<String>()

    val firstI = empty.firstOrNull()
    val lastI = empty.lastOrNull()

    val firstS = words.firstOrNull { it.startsWith('s') }
    val firstC = words.firstOrNull { it.startsWith('c') }
    val lastN = words.lastOrNull { it.endsWith('n') }
    val lastK = words.lastOrNull { it.endsWith('k') }

    println(firstI)
    println(lastI)
    println(firstS)
    println(firstC)
    println(lastN)
    println(lastK)

    // 9. count
    val totalCount = numbers.count()
    val evenCount = numbers.count { it % 2 == 0 }

    println(totalCount)
    println(evenCount)

    // 10. associateBy, groupBy
    val people = listOf<Person>(
        Person("John", "Boston", "+1-888-123456"),
        Person("Sarah", "Munich", "+49-777-789123"),
        Person("Svyatoslav", "Saint-Petersburg", "+7-999-456789"),
        Person("Vasilisa", "Saint-Petersburg", "+7-999-123456")
    )

    val phoneBook = people.associateBy { it.phone }
    val cityBook = people.associateBy(Person::phone, Person::city)
    val peopleCities = people.groupBy(Person::city, Person::name)
    val lastPersonCity = people.associateBy(Person::city, Person::name)

    println(phoneBook)
    println(cityBook)
    println(peopleCities)
    println(lastPersonCity)

    // 11. partition
    val evenOdd = numbers.partition { it % 2 == 0 }
    val (p, n) = numbers.partition { it > 0 }

    println(evenOdd)
    println(p)
    println(n)

    // 12. flatMap
    val fruitsBag = listOf<String>("apple", "orange", "banana", "grapes")
    val clothesBag = listOf<String>("shirts", "pants", "jeans")
    val cart = listOf(fruitsBag, clothesBag)
    val mapBag = cart.map { it }
    val flatMapBag = cart.flatMap { it }

    println(cart)
    println(mapBag)
    println(flatMapBag)

    // 12. minOrNull, maxOrNull
    val emptyInt = emptyList<Int>()
    val only = listOf(3)

    println("Numbers: $numbers, min = ${numbers.minOrNull()} max = ${numbers.maxOrNull()}")
    println("Empty: $empty, min = ${empty.minOrNull()}, max = ${empty.maxOrNull()}")
    println("Only: $only, min = ${only.minOrNull()}, max = ${only.maxOrNull()}")

    // 13. sorted
    val shuffled = listOf<Int>(5, 4, 2, 1, 3, -10)
    val natural = shuffled.sorted()
    val inverted = shuffled.sortedBy { -it }
    val descending = shuffled.sortedDescending()
    val descendingBy = shuffled.sortedByDescending { abs(it) }

    println(natural)
    println(inverted)
    println(descending)
    println(descendingBy)

    // 14. Map Element Access
    val map = mapOf<String, Int>("key" to 42)
    val value1 = map["key"]
    val value2 = map["key2"]

    val value3: Int = map.getValue("key")
    // withDefault returns fallback value for the key if that obj[key] doesn't exists
    val mapWithDefault = map.withDefault { k -> k.length }
    // "key2" isn't exists inside mapWithDefault, it returns "key2".length = 4
    val value4 = mapWithDefault.getValue("key2")

    try {
        map.getValue("anotherKey")
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }

    println(value1)
    println(value2)
    println(value3)
    println(mapWithDefault)
    println(value4)

    // 15. zip
    val A = listOf<String>("a", "b", "c")
    val B = listOf<Int>(1, 2, 3, 4)

    // merge 2 collections into a new collection of pairs
    val resultPairs = A zip B
    val resultReduce = A.zip(B) { a, b -> "$a$b"}

    println(resultPairs)
    println(resultReduce)

    // 15. getOrElse
    val list = listOf<Int>(0, 10, 20)
    println(list.getOrElse(1) { 42 })
    println(list.getOrElse(10) { 42 })

    val map2 = mutableMapOf<String, Int?>()
    println(map2.getOrElse("x"){ 1 })

    map2["x"] = 3
    println(map2.getOrElse("x") { 1 })

    map2["x"] = null
    println(map2.getOrElse("x") { 1 })
}

data class Person(val name: String, val city: String, val phone: String)