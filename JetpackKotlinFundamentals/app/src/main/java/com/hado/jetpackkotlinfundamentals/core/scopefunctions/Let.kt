package com.hado.jetpackkotlinfundamentals.core.scopefunctions

fun main() {
    val empty = "test".let {
        println(it)
        it.isEmpty()
    }
    println(" is empty: $empty")

    fun printNonNull(str: String?) {
        println("Printing \"$str\":")

        str?.let {
            println("\t")
            println(it)
            println()
        }
    }

    fun printIfBothNonNull(strOne: String?, strTwo: String?) {
        strOne?.let {firstString ->
            strTwo?.let {secondString ->
                println("$firstString: $secondString")
                println()
            }
        }
    }

    printNonNull(null)
    printNonNull("my string")
    printIfBothNonNull("First", "Second")
}