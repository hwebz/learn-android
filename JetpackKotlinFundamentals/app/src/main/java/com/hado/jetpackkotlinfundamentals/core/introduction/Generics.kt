package com.hado.jetpackkotlinfundamentals.core.introduction

class MutableStack<E>(vararg  items: E) {
    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)
    fun peek(): E = elements.last()
    fun pop(): E = elements.removeAt(elements.size - 1)
    fun isEmtpy() = elements.isEmpty()
    fun size() = elements.size
    override fun toString(): String = "MutableStack(${elements.joinToString()})"
}

// Generic Functions
fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

fun main() {
    var ages: MutableStack<Int> = MutableStack(15, 23, 45, 89, 103)

    println(ages)

    ages.push(66)
    println(ages)

    val removeLastAge = ages.pop()
    println(removeLastAge)
    println(ages)

    val lastAge = ages.peek()
    println(lastAge)
    println(ages)

    println(ages.isEmtpy())
    println(ages.size())

    val stack = mutableStackOf(0.62, 3.14, 2.7)
    println(stack)
}