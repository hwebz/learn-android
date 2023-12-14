package com.hado.jetpackkotlinfundamentals.core.introduction

fun main() {
    var  neverNull: String = "This can't be null"
    // neverNull = null // Throws error

    var nullable: String? = "You can keep a null here"
    nullable = null

    var inferredNonNull = "The compiler assumes non-null"
    // inferredNonNull = null

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    strLength(neverNull)
    // strLength(nullable)
}