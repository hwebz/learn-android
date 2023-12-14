package com.hado.jetpackkotlinfundamentals.core.controlflow

class Animal(val name: String)

class Zoo(val animals: List<Animal>) {
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator()
    }
}

fun main() {
    // For
    val cakes = listOf<String>("carrot", "cheese", "chocolate")
    for (cake in cakes) {
        println("Yummy, it's a $cake cake!")
    }

    // While and do while
    var cakesEaten = 0
    var cakesBaked = 0

    while (cakesEaten < 5) {
        eatACake()
        cakesEaten++
    }

    do {
        bakeACake()
        cakesBaked++
    } while (cakesBaked < cakesEaten)

    val zoo = Zoo(listOf(Animal("Zebra"), Animal("Lion")))

    for (animal in zoo) {
        println("Watch out, it's a ${animal.name}")
    }
}

fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")